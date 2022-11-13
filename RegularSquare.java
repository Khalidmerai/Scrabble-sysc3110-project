/**
 * Represents the regular square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public class RegularSquare extends Square{
    /**
     * The name of the square
     */
    private final static String name = "Regular Square";
    /**
     * Points multiplied by letter if filled
     */
    private final static int pointMultiplier = 1;

    /**
     * Constructs a new square with the specified name, row and column number.
     * Assigns the square as empty.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public RegularSquare(int rowNum, int columnNum){
        super(rowNum, columnNum);
    }

    public RegularSquare(int rowNum, int columnNum, char letter){
        super(rowNum, columnNum, letter);
    }
}
