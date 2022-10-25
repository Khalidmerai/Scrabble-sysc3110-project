import java.lang.reflect.Array;

/**
 * The GameBoard class initializes the scrabble board by building its different squares
 * and printing them for the user to use
 * @author Saad Eid
 */
public class GameBoard {
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    /** The squares on the board. */
    private Square[][] squares;

    public GameBoard(){
        squares = new Square[numRows][numColumns];
        buildSquares();
    }

    /**
     * Populates the board with squares numbered 1, 2, 3, ..., numRows and numColumns.
     * The square numbered "n" is stored in squares[n-1].
     */
    public void printGameBoard(){
        System.out.println("\tA | B | C | D | E | F | G | H | I | J | K | L | M | N | O");
        System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
        for (int i = 0; i < numRows; i++){
            if (i < 9) {
                System.out.print((i+1) + "  ");
                for (int j = 0; j < numColumns; j++) { //prints value of square
                    System.out.print(squares[i][j] + " ");
                }
                System.out.println();
                System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
            }else{
                System.out.print((i+1) + " ");
                for (int j = 0; j < numColumns; j++) { //prints value of square
                    System.out.print(squares[i][j] + " ");
                }
                System.out.println();
                System.out.println("------|---|---|---|---|---|---|---|---|---|---|---|---|---|---");
            }
        }
        System.out.println();
    }

    /**
     * Populates the board with squares of Scrabble such as the
     * DLS, TLS, DWS, TWS, starting square, and regular squares.
     */
    private void buildSquares() {

        //Triple Word Squares
        for (int i = 0; i < numRows; i++){
            if (i == 0 || i == 14){
                squares[i][0] = new TripleLetterSquare(i, 0);
                squares[i][7] = new TripleLetterSquare(i, 7);
                squares[i][14] = new TripleLetterSquare(i, 14);
            }
            if (i == 7){
                squares[i][0] = new TripleLetterSquare(i, 0);
                squares[i][14] = new TripleLetterSquare(i, 14);
            }
        }

        //Triple Letter Squares
        for (int i = 0; i < numRows; i++){
            if (i == 1 || i == 13){
                squares[i][0] = new TripleLetterSquare(i, 5);
                squares[i][7] = new TripleLetterSquare(i, 9);
                squares[i][5] = new TripleLetterSquare(i, 5);
                squares[i][9] = new TripleLetterSquare(i, 9);
            }
            if (i == 5 || i == 9){
                squares[i][0] = new TripleLetterSquare(i, 1);
                squares[i][14] = new TripleLetterSquare(i, 5);
                squares[i][0] = new TripleLetterSquare(i, 9);
                squares[i][7] = new TripleLetterSquare(i, 13);
                squares[i][1] = new TripleLetterSquare(i, 1);
                squares[i][5] = new TripleLetterSquare(i, 5);
                squares[i][9] = new TripleLetterSquare(i, 9);
                squares[i][13] = new TripleLetterSquare(i, 13);
            }
        }

        //Double Word Squares
        for (int i = 0; i < numRows; i++){
            if (i == 1 || i == 13){
                squares[i][1] = new DoubleWordSquare(i, 1);
                squares[i][13] = new DoubleWordSquare(i, 13);
            }
            if (i == 2 || i == 12){
                squares[i][2] = new DoubleWordSquare(i, 2);
                squares[i][12] = new DoubleWordSquare(i, 12);
            }
            if (i == 3 || i == 11){
                squares[i][3] = new DoubleWordSquare(i, 3);
                squares[i][11] = new DoubleWordSquare(i, 11);
            }
            if (i == 4 || i == 10){
                squares[i][4] = new DoubleWordSquare(i, 4);
                squares[i][10] = new DoubleWordSquare(i, 10);
            }
        }

        //Double Letter Squares
        for (int i = 0; i < numRows; i++){
            if (i == 0 || i == 14){
                squares[i][3] = new DoubleLetterSquare(i, 3);
                squares[i][11] = new DoubleLetterSquare(i, 11);
            }
            if (i == 2 || i == 12){
                squares[i][6] = new DoubleLetterSquare(i, 6);
                squares[i][8] = new DoubleLetterSquare(i, 8);
            }
            if (i == 3 || i == 11){
                squares[i][0] = new DoubleLetterSquare(i, 0);
                squares[i][7] = new DoubleLetterSquare(i, 7);
                squares[i][14] = new DoubleLetterSquare(i, 14);
            }
            if (i == 6 || i == 8){
                squares[i][2] = new DoubleLetterSquare(i, 2);
                squares[i][6] = new DoubleLetterSquare(i, 6);
                squares[i][8] = new DoubleLetterSquare(i, 8);
                squares[i][12] = new DoubleLetterSquare(i, 12);
            }
            if (i == 7){
                squares[i][3] = new DoubleLetterSquare(i, 3);
                squares[i][11] = new DoubleLetterSquare(i, 11);
            }
        }

        //Regular Squares
        for (int i = 0; i < numRows; i++){
            if (i == 0 || i == 14){
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][13] = new RegularSquare(i, 13);
            }
            if (i == 1 || i == 13){
                squares[i][0] = new RegularSquare(i, 0);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][3] = new RegularSquare(i, 3);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][7] = new RegularSquare(i, 7);
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][11] = new RegularSquare(i, 11);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][14] = new RegularSquare(i, 14);
            }
            if (i == 2 || i == 12){
                squares[i][0] = new RegularSquare(i, 0);
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][3] = new RegularSquare(i, 3);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][7] = new RegularSquare(i, 7);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][11] = new RegularSquare(i, 11);
                squares[i][13] = new RegularSquare(i, 13);
                squares[i][14] = new RegularSquare(i, 14);
            }
            if (i == 3 || i == 11){
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][13] = new RegularSquare(i, 13);
            }
            if (i == 4 || i == 10){
                squares[i][0] = new RegularSquare(i, 0);
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][3] = new RegularSquare(i, 3);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][7] = new RegularSquare(i, 7);
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][11] = new RegularSquare(i, 11);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][13] = new RegularSquare(i, 13);
                squares[i][14] = new RegularSquare(i, 14);
            }
            if (i == 5 || i == 9){
                squares[i][0] = new RegularSquare(i, 0);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][3] = new RegularSquare(i, 3);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][7] = new RegularSquare(i, 7);
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][11] = new RegularSquare(i, 11);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][14] = new RegularSquare(i, 14);
            }
            if (i == 6 || i == 8){
                squares[i][0] = new RegularSquare(i, 0);
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][3] = new RegularSquare(i, 3);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][7] = new RegularSquare(i, 7);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][11] = new RegularSquare(i, 11);
                squares[i][13] = new RegularSquare(i, 13);
                squares[i][14] = new RegularSquare(i, 14);
            }
            if (i == 7){
                squares[i][1] = new RegularSquare(i, 1);
                squares[i][2] = new RegularSquare(i, 2);
                squares[i][4] = new RegularSquare(i, 4);
                squares[i][5] = new RegularSquare(i, 5);
                squares[i][6] = new RegularSquare(i, 6);
                squares[i][7] = new StartingSquare();
                squares[i][8] = new RegularSquare(i, 8);
                squares[i][9] = new RegularSquare(i, 9);
                squares[i][10] = new RegularSquare(i, 10);
                squares[i][12] = new RegularSquare(i, 12);
                squares[i][13] = new RegularSquare(i, 13);
            }
        }
    }

    /**
     * Places the tile on the square of the gameboard
     */
    public void setTileOnSquare(Tile tile, int rowNumber, int columnNumber) {
        squares[rowNumber][columnNumber].placeTile(tile);
    }
    public static void main(String[] args) {
        GameBoard game = new GameBoard();
        game.printGameBoard();
        Tile tile = new Tile('A');
        game.setTileOnSquare(tile, 7,7);
        game.printGameBoard();
    }
}
