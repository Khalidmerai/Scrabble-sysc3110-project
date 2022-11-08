import java.util.ArrayList;
import java.util.Scanner;

public class ScrabbleModel implements ScrabbleView{
    Scanner scan = new Scanner(System.in);
    Bag bag;
    private GameBoard board;
    private Dictionary dictionary;
    private ArrayList<Player> players;
    private ArrayList<ScrabbleView> views;
    private boolean turn;
    private  boolean firstTurn;

    /**
     * Constructor for ScrabbleModel
     */
    public ScrabbleModel(){
        bag = new Bag();
        board =new GameBoard();
        players = new ArrayList<>();
        dictionary = new Dictionary();
        views = new ArrayList<>();

        addPlayer("Player 1", bag);
        addPlayer("Player 2", bag);

        firstTurn = true;
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

    /**
     * Changes turn between players
     * True for player 1
     * false for player 2
     */
    public void changeTurn() {
        turn = !turn;
    }

    /**
     * Prints the letters and scores of the players
     */
    public void printPlayerLettersAndScore(){
        for(Player player : players) {
            System.out.println(player.getName());
            System.out.println("Letters: " + player.getRack()); //need to print all letters
            System.out.println("Score: " + player.getScore());
        }
    }

    public Player getFirstPlayer(){
        return players.get(0);
    }

    /**
     * Checks if Starting point was filled by player in the first turn
     * @return true if starting point is filled, otherwise false.
     */
    public boolean startingPointFilled(int rowNumber, int columnNumber){
        if (rowNumber == 8 && columnNumber == 8){
            return true;
            /*
            System.out.println("You need to start from the middle of the board.");
            System.out.print("Which character would you like to place? ");
            letter = scan.next();
            letter = letter.toUpperCase();
            System.out.print("Which row would you like to place that letter? ");
            rowNumber = scan.nextInt();
            System.out.print("Which column would you like to place that letter? ");
            columnNumber = scan.nextInt();
            */
        }else{
            return false;
        }
    }

    /**
     * Checks wheter any of the command words was pressed
     */
    public void checkForCommandWords(){

    }


    public void startGame(int rowNumber, int columnNumber){
        boolean gameFinished = false;
        int player = 0; //keeps track of which player is playing at the moment
        String letter = "";
        String wordCheckString = ""; //keeps track of the letters entered by player
        //int columnNumber, rowNumber;
        System.out.println("Commands: ");
        System.out.println("1.\"pass\": to pass your turn");
        System.out.println("2.\"quit\": to quit the game");
        System.out.println("3.\"submit\": after placing all the letters on the board, the game checks if it is valid or not");

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
                wordCheckString = "";
                continue;
            }
            //quiting game
            if(letter.equals("quit")){
                gameFinished = true;
                continue;
            }
            //checks whether the word is valid after the player places their tile
            if(letter.equals("submit")){
                if (!dictionary.checkWord(wordCheckString)){
                    System.out.println("Please enter a valid word.");
                    //Remove all the tiles that were placed by that player during that turn
                }else{
                    if (player == 0){
                        player = 1;
                    }else{
                        player = 0;
                    }
                    continue;
                }
            }

            //check wether or not the letter entered by the player is in the rack
            if (player == 0){
                if(players.get(0).rack.contains(new Tile(letter.charAt(0)))){
                    players.get(0).removeTileFromRack(new Tile(letter.charAt(0)));
                    players.get(0).addTileToRack(bag.drag());
                }else{
                    System.out.println("You need to enter an existing tile from your rack.");
                    System.out.print("Which character would you like to place? ");
                    letter = scan.next();
                }
                letter = letter.toUpperCase();
                System.out.print("Which row would you like to place that letter? ");
                rowNumber = scan.nextInt();
                System.out.print("Which column would you like to place that letter? ");
                columnNumber = scan.nextInt();
            }else{
                if(players.get(1).rack.contains(new Tile(letter.charAt(0)))){
                    players.get(1).removeTileFromRack(new Tile(letter.charAt(0)));
                    players.get(1).addTileToRack(bag.drag());
                }else{
                    System.out.println("You need to enter an existing tile from your rack.");
                    System.out.print("Which character would you like to place? ");
                    letter = scan.next();
                }
                letter = letter.toUpperCase();
                System.out.print("Which row would you like to place that letter? ");
                rowNumber = scan.nextInt();
                System.out.print("Which column would you like to place that letter? ");
                columnNumber = scan.nextInt();
            }

            while (firstTurn){
                checkStartingPoint();
            }

            Tile tile = new Tile(letter.charAt(0));
            if (board.squares[rowNumber-1][columnNumber-1].isFilled()){
                System.out.println("There is already a letter on that square. Please place your tile on a different square");
            }else{
                board.setTileOnSquare(tile,rowNumber-1, columnNumber-1);
            }
            //board.printGameBoard();
            /*Scoring will be implemented later
            Square squareType;

            if(player == 0){ //player 1
                players.get(0).addScore(tile.getValue() * squareType);
            }else{ //player 2
                players.get(1).addScore(tile.getValue() * squareType);
            }
            */
        }
    }

    public static void main(String[] args){
        ScrabbleModel game = new ScrabbleModel();
        game.addPlayer("Player 1", game.bag);
        game.addPlayer("Player 2", game.bag);
        game.startGame();
    }

    @Override
    public void update(ScrabbleEvent scrabbleEvent) {
        for (ScrabbleView v: views){
            //v.update(new ScrabbleEvent(this, rowNumber, ColumnNumber))
        }
    }

    public void addScrabbleView(ScrabbleView view) {
        views.add(view);
    }
}
