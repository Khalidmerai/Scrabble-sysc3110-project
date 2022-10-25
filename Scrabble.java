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
        players.add(new Player(name, bag));
    }

    //we need to set the board visible, how to do that?
    public void setBoard(GameBoard board) {
        this.board = board;
    }

    /**TODO: print letters in the players' rack
     * Prints the letters and scores of the players
     */
    public void printPlayerLettersAndScore(){
        for(Player player : players) {
            System.out.println(player.getName());
            System.out.println("Letters: " ); //need to print all letters
            System.out.println("Score: " + player.getScore());
        }
    }

    public void startGame(){
        boolean gameFinished = false;
        int player = 0; //keeps track of which player is playing at the moment
        String letter = "";
        boolean firstTurn = true;
        int columnNumber, rowNumber;

        while(!gameFinished){
            printPlayerLettersAndScore(); //prints player's available tiles and the points he/she has
            board.printGameBoard();
            System.out.print("Which character would you like to place? ");
            letter = scan.next();

            //Switching players when the player enters the word "pass"
            if(letter.equals("pass")){
                if (player == 0){
                    player = 1;
                }else{
                    player = 0;
                }
            }

            letter = letter.toUpperCase();
            System.out.print("Which row would you like to place that letter? ");
            rowNumber = scan.nextInt();
            System.out.print("Which column would you like to place that letter? ");
            columnNumber = scan.nextInt();

            Tile tile = new Tile(letter.charAt(0));
            board.setTileOnSquare(tile,rowNumber-1, columnNumber-1);
            board.printGameBoard();
            /*Scoring will be implemented later
            Square squareType;

            if(player == 0){ //player 1
                players.get(0).addScore(tile.getValue() * squareType);
            }else{
                players.get(1).addScore(tile.getValue() * squareType);
            }
            */
        }
    }

    public static void main(String[] args){
        Scrabble game = new Scrabble();
        game.addPlayer("Player 1", game.bag);
        game.addPlayer("Player 2", game.bag);
        game.printPlayerLettersAndScore(); //prints player's available tiles and the points he/she has
        game.startGame();
    }
}
