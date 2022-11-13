//Marina Latif, 101149148
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends JComponent {
    /**
     * Name of player
     */
    private String name;
    /**
     * Score of player
     */
    private int score;

    //public ArrayList<Tile> rack;
    private List<Character> rack;
    /**
     * Player's turn
     */
    private boolean turn;

    private static final int rackSize = 7;


    /**
     * Contsructor of player requiring their name and letter bag
     * @param name The player's name.
     * @param startingRack The player's letter bag
     * @param turn player's turn
     */
    public Player (String name,List<Character> startingRack, boolean turn){
        this.name = name;
        this.score = 0;
        this.turn = turn;
        this.rack = new ArrayList<>(startingRack);
        /*
        rack = new ArrayList<Tile>();
        for(int i =0; i< rackSize;i++){
            rack.add(bag.drag());
        }
         */
    }

    /**
     * Getter method for Player's name
     * @return a string of the players name
     */
    public String getName(){
        return name;
    }

    /**
     * This method adds the word score to the players score
     * @param s  an integer of the word score
     */
    public void addScore(int s){
        score+= s;
    }

    /**
     * Getter method for Player's score
     * @return an integer of the player's score
     */
    public int getScore(){
        return score;
    }

    /**
     * Setter method for Player's turn
     *Sets boolean to true if it's the player's turn
     */
    public void setTurn(boolean b){
        turn = b;
    }

    /**
     * Setter method for Player's turn
     *Sets boolean to true if it's the player's turn
     */
    public boolean getTurn(){
        return turn;
    }

    /**
     * This method ends the player's turn
     */
    public void endTurn(){
        if(turn){
            turn = false;
        }
        else{
            turn = true;
        }
    }

    /**
     * @param index
     * @return letter at the specified index
     */
    public char getLetter(int index) {
        return rack.get(index);
    }

    /**
     * @return all current letters
     */
    public List<Character> getAll() {
        return new ArrayList<Character>(rack);
    }

    /**
     * clears player's letter rack
     */
    public void clear() {
        rack.clear();
    }

    /**
     *
     * @return - size of bench
     */
    public int getBenchSize() {
        return rack.size();
    }

    /**
     *
     * @param toUse letters to be "used"
     * and deleted from current letter bench
     */
    public void useLetters(List<Character> toUse) {
        for (char c : toUse) {
            this.useLetter(c);
        }

    }
    /**
     *
     * @param c char to "use"
     * helper function for public method useLetters
     */
    private void useLetter(Character c) {
        rack.remove(c);
    }

    /**
     * @param toAdd letters to add to bench
     */
    public void addLetters(List<Character> toAdd) {
        rack.addAll(toAdd);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("here");
        g.fillRect(0, 0, 100, 100000);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35-(35/2)));
        int index = 0;
        for (char c: rack) {
            g.drawString(Character.toString(c), index*35, (35/2));
        }



    }
}
