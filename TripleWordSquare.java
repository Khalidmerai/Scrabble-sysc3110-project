/**
 * Represents the triple word square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public class TripleWordSquare extends Square{
    /**
     * The name of the square
     */
    private final static String name = "Triple Word Square";
    /**
     * Points multiplied by word if filled
     */
    private final static int pointMultiplier = 3;

    /**
     * Constructs a new square with the specified name, row and column number.
     * Assigns the square as empty.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public TripleWordSquare(int rowNum, int columnNum){
        super(name, rowNum, columnNum);
        super.isFilled = false;
    }
}
