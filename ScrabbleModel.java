import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ScrabbleModel extends JPanel implements ScrabbleView {
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    public enum Status {PLAYER_1_WON, PLAYER_2_WON, UNDECIDED};
    private Status status;
    private ArrayList<ScrabbleView> views;
    private boolean turn, firstTurn, gameFinished;
    private char[][] grid;
    private String wordCheckString = "", letter = "";
    private int rowNumber, columnNumber;
    private Dictionary dict;
    private Bag bagOfTiles;
    public static final int CENTER = 7;
    private Square[][] board = new Square[numRows][numColumns];
    private Square[][] tempBoard = new Square[numRows][numColumns];

    public ScrabbleModel(String dictionaryFile, Bag letterBag) throws IOException {

        if (dictionaryFile == null || letterBag == null) {
            JOptionPane.showMessageDialog(null,"File Not Found",
                    "Dictionary File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //this.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        //initialize board
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                Square sq = new Square(row, column);
                board[row][column] = sq;
                tempBoard[row][column] = new Square(row, column);

                //add to panel
                this.add(sq);
            }
        }

        //initialize dictionary
        try {
            this.dict = new Dictionary(new TokenScanner(new FileReader(dictionaryFile)));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"File Not Found",
                    "Dictionary File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        //initialize letter bag
        bagOfTiles = letterBag;

    }

    @Override
    public void update(ScrabbleEvent scrabbleEvent) {
        for (ScrabbleView v: views){
            v.update(scrabbleEvent);
        }
    }

    public void addScrabbleView(ScrabbleView view) {
        views.add(view);
    }

    /**TODO
     * Updates status of the game
     */
    public void updateStatus(){

    }

    /**
     *
     * @return pointer to the current board
     */
    public Square[][] getCurrentBoard() {
        return board;
    }

    /**
     *
     * @return deep copy of the current board
     */
    public Square[][] getCopyOfBoard() {
        Square[][] copy = new Square[board.length][board[0].length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }

        return copy;
    }

    /**
     *
     * @param row - row of desired square
     * @param column - column of desired square
     * @return - pointer to the desired square
     */
    public Square getSquare(int row, int column) {
        return board[row][column];
    }

    /**
     *
     * @param s Square to add to board
     */

    private void addSquareToBoard(Square s) {
        board[s.getRowNum()][s.getColumnNum()] = s;
    }

    /**
     *
     * @param sqs to add to board
     */
    private void addToBoard(ArrayList<Square> sqs) {
        for (Square sq: sqs) {
            addSquareToBoard(sq);
        }
    }


    /** Public Method for adding a word to the Game board
     * @param sqs - ArrayList<Square> of filled squares to add
     * @param firstTurn - true if first turn of game, false otherwise
     * @return point value of move, -1 if move is invalid for any reason
     */

    public int addWord(ArrayList<Square> sqs, boolean firstTurn) {
        for (Square sq: sqs) {
            int row = sq.getRowNum();
            int col = sq.getColumnNum();
            if (row < 0 || row >= numRows
                    || col < 0 || col >= numColumns) return -1;
        }


        //sort squares to be in word reading order
        Collections.sort(sqs);

        //get first square to match for vertical word or horizontal word
        Square first = sqs.get(0);
        int firstRow = first.getRowNum();
        int firstCol = first.getColumnNum();
        boolean sameRow = false; //dummy

        //collect indices of non-constant coordinate
        Set<Integer> indices = new TreeSet<Integer>();
        for (int i = 1; i < sqs.size(); i++ ) {
            int row = sqs.get(i).getRowNum();
            int col = sqs.get(i).getColumnNum();
            if (board[row][col].hasLetter() ||
                    board[firstRow][firstCol].hasLetter()) {
                return -1; //invalid move, can't overwrite tiles
            }

            //check second tile to determine if vertical or horizontal
            if (i == 1) {
                if (row == firstRow) {
                    indices.add(col);
                    sameRow = true;
                    indices.add(first.getColumnNum());
                } else if (col == firstCol) {
                    indices.add(row);
                    sameRow = false;
                    indices.add(first.getRowNum());

                } else {
                    return -1; //invalid move - squares must share row or col
                }
            } else {

                if (sameRow) {
                    indices.add(col);
                    if (row != firstRow) return -1; //invalid move - squares must share row or col
                } else {
                    indices.add(row);
                    if (col != firstCol) return -1; //invalid move - squares must share row or col
                }
            }

        } //end loop


        //check for one letter input
        //default call it horizontal
        if (sqs.size()==1) {
            sameRow = true;
            indices.add(firstCol);
        }

        //start building word
        String s = "";
        int index = -1;
        int firstIndex = -1;
        Iterator<Integer> iter = indices.iterator();
        int previous = -1;

        //make sure to note every time the letter was already on board
        //so the player does not receive points
        //Set will be passed into next function

        Set<Integer>indicesNoPoints = new TreeSet<Integer>();
        while (iter.hasNext()) {
            index++;
            int a = iter.next();

            if (previous != -1) {
                if (a != previous + 1) {
                    //no points for words off of this index
                    // b/c letter was already there
                    indicesNoPoints.add(previous+1);

                    if (sameRow) {
                        //gap in word invalid move, otherwise letter that fills gap
                        if (!(board[firstRow][previous+1].hasLetter())) return -1;
                        s+= board[firstRow][previous + 1].getLetter();
                    } else {
                        //gap in word invalid move, otherwise letter that fills gap
                        if (!(board[previous+1][firstCol].hasLetter())) return -1;
                        s+= board[previous + 1][firstCol].getLetter();
                    }
                }
            } else firstIndex = a;

            // append to word
            s += sqs.get(index).getLetter();
            previous = a;

        } //end iterator

        //convert to char array for next function
        char [] asChar = s.toCharArray();

        //find start row and start col
        int startRow = (sameRow) ? firstRow : firstIndex;
        int startCol = (!sameRow) ? firstCol : firstIndex;


        //call helper function to do the dirty work
        int result = addWordHelper(asChar, startRow, startCol, (!sameRow), firstTurn, indicesNoPoints);

        //add word to board if the move was valid
        if (result > 0) {
            addToBoard(sqs);
            return result;

        } else return -1;
    }

    /**
     *  Helper Function for public word add function
     * @param word - char [] of word created by input squares and gaps filled in by letters on board
     * @param startRow - starting row of word
     * @param startCol - starting col of word
     * @param vertical - vertical word or horizontal word
     * @param firstTurn - is it the first turn?
     * @param indicesNoPoints - the indices where the word used letters from board,
     * and should not receive points for words jotting out on either side
     * @return points scored, -1 if invalid move
     */
    private int addWordHelper(char[] word, int startRow, int startCol,
                              boolean vertical, boolean firstTurn, Set<Integer> indicesNoPoints) {

        int startIndex = (vertical) ? startRow : startCol;
        int otherIndex = (!vertical) ? startRow : startCol;

        if (firstTurn) {
            //Center of board must be used on first move, otherwise invalid
            if (!(CENTER < startIndex + word.length && CENTER>= startIndex
                    && otherIndex==CENTER)) return -1;
            if (word.length==0) return -1;

        } else {

            //not first turn checks

            for (int i = startIndex; i < startIndex + word.length; i++) {
                //check open spots. If they are filled, they must be the same letter
                //this is the way I wrote it, since the char[] word has the gaps added to them
                if (vertical) {
                    if (board[i][startCol].hasLetter()) {
                        if (word[i-startIndex] != board[i][startCol].getLetter()) return -1;
                    }
                } else {
                    if (board[startRow][i].hasLetter()) {
                        if (word[i-startIndex] != board[startRow][i].getLetter()) {
                            return -1;
                        }
                    }
                }
            }

            //make sure that the word has an anchor on the board already
            boolean foundSquare = false; //false until found true

            //loop through and break once the anchor square is found
            for (int i = startIndex; i <= startIndex + word.length; i++) {

                if (vertical) {

                    if (i==startIndex) {
                        if (i - 1 >= 0) {
                            Square s = board[i-1][startCol];
                            if (s.hasLetter()) {
                                foundSquare = true;
                                break;
                            }
                        }
                    }

                    if (i== startIndex+word.length-1) {
                        if (i + 1 < numRows) {
                            Square s = board[i+1][startCol];
                            if (s.hasLetter()) {
                                foundSquare = true;
                                break;
                            }
                        }
                    }

                    if (startCol != 0) {
                        Square s = board[i][startCol-1];
                        if (s.hasLetter()) {
                            foundSquare = true;
                            break;
                        }
                    }

                    if (startCol != numRows-1) {
                        Square s = board[i][startCol+1];
                        if (s.hasLetter()) {
                            foundSquare = true;
                            break;
                        }
                    }

                    //end vertical check
                } else { //horizontal word
                    if (i==startIndex) {
                        if (i - 1 >= 0) {
                            Square s = board[startRow][i-1];
                            if (s.hasLetter()) {
                                foundSquare = true;
                                break;
                            }
                        }
                    }

                    if (i== startIndex+word.length-1) {
                        if (i + 1 < numRows) {
                            Square s = board[startRow][i+1];
                            if (s.hasLetter()) {
                                foundSquare = true;
                                break;
                            }
                        }
                    }

                    if (startRow != 0) {
                        Square s = board[startRow-1][i];
                        if (s.hasLetter()) {
                            foundSquare = true;
                            break;
                        }
                    }

                    if (startRow != numRows-1) {
                        Square s = board[startRow+1][i];
                        if (s.hasLetter()) {
                            foundSquare = true;
                            break;
                        }
                    }

                } //horizontal word

            } //loop to find anchor square


            if (!(foundSquare)) {
                return -1; //invalid move, no anchor found
            }

        } //end not first turn checks

        //build all words created via helper function
        List<String> wordsToCheck = findWordsToCheck(word, startRow,
                startCol, vertical, indicesNoPoints);

        //check words in dictionary
        if (checkWords(new TreeSet<String>(wordsToCheck))) {
            return getPoints(wordsToCheck); //if valid, return the points scored
        } else {
            return -1; //invalid word found - not valid move
        }
    }


    /**
     * Given that a move is valid placement, find all words made by adding that word to board
     * @param word: word added
     * @param startRow: start Row
     * @param startCol: start Column
     * @param isVertical: vertical word or horizontal word?
     * @param indicesNoPoints: indices in which the user should not get points for
     * words jotting out, because the letters were already there
     * @return List<String> of words to check
     */

    private List<String> findWordsToCheck(char[] word, int startRow,
                                          int startCol, boolean isVertical, Set<Integer> indicesNoPoints) {

        //initialize list of words. List because words can be duplicates
        LinkedList<String> tgt = new LinkedList<String>();

        //vertical word is main word
        if (isVertical) {

            for (int i = startRow; i < startRow + word.length; i++) {
                String horizontal = "" + word[i-startRow];

                //leftwards
                for (int j = startCol-1; j >= 0; j--) {
                    if (indicesNoPoints.contains(i)) break;
                    Square next = board[i][j];
                    if (next.hasLetter()) {
                        horizontal = next.getLetter() + horizontal;
                    } else break;
                }

                //rightwards
                for (int j = startCol+1; j < 15; j++) {
                    if (indicesNoPoints.contains(i)) break;
                    Square next = board[i][j];
                    if (next.hasLetter()) {
                        horizontal += next.getLetter();
                    } else break;
                }

                if (horizontal.length() > 1) {
                    tgt.add(horizontal); //add word if exists
                }
            } //row loop


            //Vertical word prefixes and suffixes
            String vertical = new String(word);
            //upwards
            for (int up = startRow-1; up >= 0; up--) {
                Square next = board[up][startCol];
                if (next.hasLetter()) {
                    vertical = next.getLetter() + vertical;
                } else break;
            }

            //downwards
            for (int up = startRow+word.length; up < 15; up++) {
                Square next = board[up][startCol];
                if (next.hasLetter()) {
                    vertical += next.getLetter();
                } else break;
            }
            tgt.add(vertical); //add word found

            //vertical check
            //start horizontal word
        } else {


            for (int i = startCol; i < startCol + word.length; i++) {
                String vertical = "" + word[i-startCol];

                //upwards
                for (int j = startRow-1; j >= 0; j--) {
                    if (indicesNoPoints.contains(i)) break;
                    Square next = board[j][i];
                    if (next.hasLetter()) {
                        vertical = next.getLetter() + vertical;
                    } else break;
                }

                //downwards
                for (int j = startRow+1; j < 15; j++) {
                    if (indicesNoPoints.contains(i)) break;
                    Square next = board[j][i];
                    if (next.hasLetter()) {
                        vertical += next.getLetter();
                    } else break;
                }

                if (vertical.length() > 1) {
                    tgt.add(vertical); //add word if exists
                }
            } //column loop


            //Horizontal word prefixes and suffixes
            String horizontal = new String(word);

            //leftwards
            for (int side = startCol-1; side >= 0; side--) {
                Square next = board[startRow][side];
                if (next.hasLetter()) {
                    horizontal = next.getLetter() + horizontal;
                } else break;
            }

            //rightwards
            for (int side = startCol+word.length; side < 15; side++) {
                Square next = board[startRow][side];
                if (next.hasLetter()) {

                    horizontal += next.getLetter();
                } else break;
            }

            tgt.add(horizontal); //add word


        } //horizontal word loops

        //finally
        return tgt;

    }

    /** Checks a Set<String> of words given to it against the dictionary
     * @param words: Set of Strings to check
     * @return boolean - true if all words were in dictionary, false otherwise
     *
     */

    private boolean checkWords(Set<String> words) {
        for (String s: words) {
            if (s.length() < 2) continue;
            if (!(dict.isWord(s))) return false;
        }
        return true;
    }

    /** Gets the points for the words using this.bagOfTiles.getPointValue()
     * @param wordsToCheck - List<String>  to find points for
     * @return total points for all words based
     *
     */
    private int getPoints(List<String> wordsToCheck) {
        int total = 0;
        for (String s: wordsToCheck) {
            if (s.length() < 2) continue; //one letters words don't count
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum+= bagOfTiles.getPointValue(s.charAt(i));
            }
            total += sum;
        }

        return total;
    }
}
