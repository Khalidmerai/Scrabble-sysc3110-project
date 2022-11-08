/**
 * Represents the double word square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public class DoubleWordSquare extends Square {
    /**
     * The name of the square
     */
    private final static String name = "Double Word Square";
    /**
     * Points multiplied by word if filled
     */
    private final static int pointMultiplier = 2;

    /**
     * Constructs a new square with the specified name, row and column number.
     * Assigns the square as empty.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public DoubleWordSquare(int rowNum, int columnNum){
        super(name, rowNum, columnNum);
        super.isFilled = false;
    }
}
