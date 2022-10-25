import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Scrabble {
    private Player player;
    Scanner scan = new Scanner(System.in);
    private Bag bag;
    private int turn = 0;
    private GameBoard board;
    private Dictionary dictionary;
    private ArrayList<Player> players;
    private int MaxPlayers = 4;


    public Scrabble(){
        bag = new Bag();
        board =new GameBoard();

        players = new ArrayList<Player>();
        // try and catch for initializing the Dictionary
        try{
            this.dictionary = new Dictionary();
        } catch (FileNotFoundException e){
            //we need to initialize it
        }




    }

    /**
     * Adds a player with the specified name to the game.
     *
     * @param name The player's name.
     * @param bag The player's letter bag
     */
    public void addPlayer(String name, Bag bag)
    {
        ArrayList<String> players = new ArrayList<String>();
        for(int i = 1; i <= MaxPlayers; i++) {
            System.out.println("\nPlayer " + i + ", Player Name: ");
            // add to array
            players.add(name);
        }
    }

    //we need to set the board visible, how to do that?
    public void setBoard(GameBoard board) {
        this.board = board;
    }



    public static void main(String[] args){
        Scrabble game = new Scrabble();

    }


}
