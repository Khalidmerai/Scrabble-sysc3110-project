import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AI extends Player {//implements FreePlay{
    private Map<Character, Integer> letterToAmountLeft = new TreeMap<Character, Integer>();
    boolean playerturn;
    private Player player;

    private int turn;
    private int numRows;
    private int numColumns;
    //private TurnAwaiter turnAwaiter;
    private Square[][] board = new Square[numRows][numColumns];
    private Dictionary dictionary;
    private int score;

    private ArrayList<Tile> letters ;
    /**
     * Contsructor of player requiring their name and letter bag
     *
     * @param name         The player's name.
     * @param startingRack The player's letter bag
     * @param turn         player's turn
     */
    public AI(String name, List<Character> startingRack, boolean turn) throws IOException {
        super(name, startingRack, turn);
        letters = new ArrayList<Tile>();
        score = 0;
        dictionary = new Dictionary(new TokenScanner(new FileReader("wordlist.10000.txt")));
        startingRack = new ArrayList<>();
        playerturn = false;
        //turnAwaiter = new TurnAwaiter();
        //turnAwaiter.start();
    }
    private char drawTile() {
        Random r = new Random();
        int size = this.getTilesLeft();
        if (size < 1) throw new RuntimeException("No Tiles Left");

        int pick = r.nextInt(size);
        char toDraw = (char)(-1);
        for (char c: letterToAmountLeft.keySet()) {
            int amountLeft = letterToAmountLeft.get(c);
            pick -= letterToAmountLeft.get(c);
            if (pick <= 0) {
                toDraw = c;
                letterToAmountLeft.put(c, amountLeft-1);
                break;
            }

        }
        return toDraw;
    }
    private int contains(char[] word, char letter) {
        for (int i = 0; i < word.length; i++) {
            if (letter == word[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getTilesLeft() {
        return 0;
    }

    public void addLetter(Tile newLetter) {
        letters.add(newLetter);
    }

    public void addScore(int newScore) {
        score += newScore;
    }

    public int getNumberOfLetters() {
        return letters.size();
    }

    public ArrayList<Tile> getLetter() {
        return letters;
    }


    /**
     * This method helps find all the combination from the rack and put it on the board
     **/
    public ArrayList<String> letterCombo(char letter) {

        char[] characters;
        if (letter != ' ') {
            characters = new char[letters.size() + 1];
        } else {
            characters = new char[letters.size()];
        }
        //creating an arraylist to store the combinations of the letter
        ArrayList<String> combo = new ArrayList<String>();

        // create the words from the rack
        for (int i = 0; i < letters.size(); i++) {
            characters[i] = letters.get(i).getLetter();
        }
        if (letter != ' ') {
            characters[letters.size()] = letter;
        }
        //checking if the word matches the list
        for (int i = 0; i < dictionary.getNumWords(); i++) {

            char[] temp = characters.clone();
            char[] word = dictionary.getDictionaryList().get(i).toCharArray();

            // create and return the words
            for (int j = 0; j < word.length; j++) {
                int index = contains(temp, word[j]);
                if (index != -1) {
                    temp[index] = '.';
                } else {
                    break;
                }
                if (j == word.length - 1) {
                    String newWord = new String(word);
                    if (letter != ' ' && newWord.contains("" + letter)){
                        combo.add(newWord);
                    } else if (letter == ' '){
                        combo.add(newWord);
                    }
                }
            }
        }
        Collections.sort(combo);
        return combo;
    }

    //Find score of a word
    public int findScore(String word) {
        int score = 0;
        for (Character c : word.toCharArray()) {
            score += letterToAmountLeft.get(c);
        }
        return score;
    }

    //Finds the highest scoring combo and add to the score
    public String findBestWord(ArrayList<String> wordcombo) {
        String word = "";
        int score = 0;
        for (String s : wordcombo) {
            int temp = findScore(s);
            if (temp > score) {
                score = temp;
                word = s;
            }
        }
        return word;
    }
/*
    @Override
    public void free() {
        endGame();
    }
    public void endGame() {
        turnAwaiter.shouldStop();
        turnAwaiter = null;
    }

    private class TurnAwaiter extends Thread {
        public TurnAwaiter() {
            playerturn = true;
        }
        public void shouldStop() {
            playerturn = false;
        }
        public void run() {
            while (playerturn) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //we cant have a boolean function for this
                //what does it do
                if (player.getTurn() == turn) {
                    takeTurn();
                }
            }
        }

        private boolean getTurn() {
            return  playerturn;
        }

        private void takeTurn() {
        }
    }
*/
}