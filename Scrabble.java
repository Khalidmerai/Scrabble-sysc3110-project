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

    /**
     * Constructor for Scrabble
     */
    public Scrabble(){
        bag = new Bag();
        board =new GameBoard();
        players = new ArrayList<Player>();
        dictionary = new Dictionary();

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

    public void printPlayerLettersAndScore(){
        for(Player player : players) {
            System.out.println(player.getName());
            System.out.println("Letters: " ); //need to print all letters
            System.out.println("Score: " + player.getScore());
        }
    }

    public void startGame(){
        boolean gameFinished = false;
        int player = 0;
        String letter = "";
        boolean firstTurn = true;
        int columnNumber, rowNumber;

         while(!gameFinished){
            printPlayerLettersAndScore(); //prints player's available tiles and the points he/she has
            board.printGameBoard();
            System.out.println("Which character would you like to place?");
            letter = scan.next();
            System.out.println("Which row would you like to place that letter?");
            rowNumber = scan.nextInt();
            System.out.println("Which row would you like to place that letter?");
            columnNumber = scan.nextInt();

            board.setTileOnSquare(new Tile(letter.charAt(0)),rowNumber, columnNumber);
         }
    }

    public static void main(String[] args){
        Scrabble game = new Scrabble();
    }


}
