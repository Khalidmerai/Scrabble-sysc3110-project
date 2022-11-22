/**
 * Represents the double letter square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public class DoubleLetterSquare extends Square{
    /**
     * The name of the square
     */
    private final static String name = "Double Letter Square";
    /**
     * Points multiplied by letter if filled
     */
    private final static int pointMultiplier = 2;

    /**
     * Constructs a new square with the specified name, row and column number.
     * Assigns the square as empty.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public DoubleLetterSquare(int rowNum, int columnNum){
        super(rowNum, columnNum);
    }

    public DoubleLetterSquare(int rowNum, int columnNum, char letter){
        super(rowNum, columnNum, letter);
    }
    public int getPointMultiplier(){
        return pointMultiplier;
    }

    public String getName(){
        return name;
    }
}
