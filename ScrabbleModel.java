import java.util.ArrayList;
import java.util.Scanner;

public class ScrabbleModel implements ScrabbleView{
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    public enum Status {PLAYER_1_WON, PLAYER_2_WON, UNDECIDED};
    private Status status;
    private Bag bag;
    private GameBoard board;
    private Dictionary dictionary;
    private ArrayList<Player> players;
    private ArrayList<ScrabbleView> views;
    private boolean turn, firstTurn, gameFinished;
    private char[][] grid;
    private String wordCheckString = "", letter = "";
    private int rowNumber, columnNumber;

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

        //initializing the grid as empty characters
        grid = new char[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                grid[i][j] = ' ';
            }
        }
        firstTurn = true;
        gameFinished = false;
        status = Status.UNDECIDED;
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
     * Returns the status of the game
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Changes turn between players
     * True for player 1
     * false for player 2
     */
    public void changeTurn() {
        turn = !turn;
        wordCheckString = "";
    }

    /**
     * Returns the player's turn
     * True for player 1
     * false for player 2
     */
    public boolean getTurn() {
        return turn;
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
     * @return true if starting point is filled and sets firstTurn to false, otherwise returns false.
     */
    public boolean checkIfStartingPointFilled(int rowNumber, int columnNumber){
        if (rowNumber == 7 && columnNumber == 7){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks wheter any of the command words was pressed
     */
    public void checkForCommandWords(String word){
        //Switching players when the player enters the word "pass"
        if(word.equals("Pass")) {
            changeTurn();
        }
        //quiting game (not sure if this needs to be implemented)
        if(word.equals("Quit")){
            gameFinished = true;
        }
        //checks whether the word is valid after the player places their tile
        if(word.equals("Submit")){
            if (!dictionary.checkWord(wordCheckString)){
                System.out.println("Please enter a valid word.");
                //Remove all the tiles that were placed by that player during that turn
            }else{
                changeTurn();
            }
        }
    }

    /**
     * Checks wether or not the letter entered by the player is in the rack.
     * @param letter entered by player
     */
    public void checkForLetterInPlayerRack(String letter){
        if (getTurn()){ //Player 1
            if(players.get(0).rack.contains(new Tile(letter.charAt(0)))) {
                players.get(0).removeTileFromRack(new Tile(letter.charAt(0)));
                players.get(0).addTileToRack(bag.drag());
            }
        }else {
            if (players.get(1).rack.contains(new Tile(letter.charAt(0)))) {
                players.get(1).removeTileFromRack(new Tile(letter.charAt(0)));
                players.get(1).addTileToRack(bag.drag());
            }
        }
    }

    public void placeLetterOnBoard(int rowNumber, int columnNumber, String letter){
        Tile tile = new Tile(letter.charAt(0));
        if (board.squares[rowNumber][columnNumber].isFilled()){
            System.out.println("There is already a letter on that square. Please place your tile on a different square");
        }else{
            board.setTileOnSquare(tile,rowNumber, columnNumber);
        }
    }

    /**
     * Places the letter specified by player
     * @param rowNumber location of sqaure
     * @param columnNumber location of sqaure
     * @param letter letter to be placed in square
     */
    public void play(int rowNumber, int columnNumber, String letter){
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        checkForCommandWords(letter);
        checkForLetterInPlayerRack(letter);
        System.out.println("firstTurn " +firstTurn);
        if (firstTurn){
            if (!checkIfStartingPointFilled(rowNumber,columnNumber)){
                grid[rowNumber][columnNumber] = letter.charAt(0);
                wordCheckString += letter.charAt(0);
            }
        }else {
            grid[rowNumber][columnNumber] = letter.charAt(0);
            wordCheckString += letter.charAt(0);
        }
        System.out.println("wordCheckString " + wordCheckString);
        placeLetterOnBoard(rowNumber,columnNumber, letter);
        update(new ScrabbleEvent(this, rowNumber, columnNumber, letter.charAt(0), getTurn(), getStatus()));
    }

    @Override
    public void update(ScrabbleEvent scrabbleEvent) {
        for (ScrabbleView v: views){
            v.update(scrabbleEvent);
        }
    }

    public void addScrabbleView(ScrabbleView view) {
        views.add(view);
    }

    /**TODO
     * Updates status of the game
     */
    public void updateStatus(){

    }
}
