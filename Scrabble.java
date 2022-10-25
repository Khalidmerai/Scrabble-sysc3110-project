import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
        dictionary = new Dictionary();
        bag = new Bag();
        board =new GameBoard();
        players = new ArrayList<Player>();

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

    public static void main(String[] args){
        Scrabble game = new Scrabble();




    }


}
