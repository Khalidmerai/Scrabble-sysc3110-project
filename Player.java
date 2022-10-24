//Marina Latif, 101149148
import java.util.ArrayList;

public class Player  {
    /**
     * Name of player
     */
    private String name;
    /**
     * Score of player
     */
    private int score;

    private ArrayList<Tile> rack;
    /**
     * Player's turn
     */
    private boolean turn;



    /**
     * Contsructor
     */
    public Player (String name,Bag bag){
        this.name = name;
        this.score = 0;
        this.turn = false;
        rack = new ArrayList<Tile>();
        for(int i =0; i< rack.size();i++){
            addTile(bag);
        }
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
     *Sets boolean to true if its the player's turn
     */
    public void setTurn(){
        turn = true;
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
     * Getter method for rack
     * @return rack
     */
    public ArrayList<Tile> getRack() {
        return rack;

    }

    /**
     * Setter method for rack
     *
     */
    public void setRack( ArrayList<Tile> list) {
        rack = list;
    }
    public final boolean addTile(Bag bag){
    boolean dragTile = rack.add(bag.drag());
    return dragTile;}
    
    public void addTileToRack(){
        for(int i =0; i< rack.size();i++){
            rack.add();
        }
    }
}
