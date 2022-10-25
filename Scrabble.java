import java.util.ArrayList;

public class Scrabble {
    private Player player;
    private Bag bag;
    private int turn = 0;
    private GameBoard board;
    private Dictionary dictionary;
    private ArrayList<Player> players;


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
        players.add(new Player(name, bag));
    }

    public static void main(String[] args){
        Scrabble game = new Scrabble();
    }


}
