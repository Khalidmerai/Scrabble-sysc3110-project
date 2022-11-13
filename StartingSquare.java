/**
 * Represents the starting square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public class StartingSquare extends Square{
    /**
     * The name of the square
     */
    private final static String name = "Starting Square";
    /**
     * Points multiplied by letter if filled
     */
    private final static int pointMultiplier = 1;

    /**
     * Represents the only place the starting square will be
     */
    private final static int rowNum = 7;
    private final static int columnNum = 7;

    /**
     * Constructs a new starting square with the specified name, row and column number.
     * Assigns the square as empty.
     */
    public StartingSquare(){
        super(rowNum, columnNum);
    }


}
