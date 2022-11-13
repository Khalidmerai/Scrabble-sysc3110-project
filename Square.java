/**
 * Square is the superclass of the different types of
 * square on the ScrabbleModel game board.
 *
 * Removed the abstract for now
 * @author Saad Eid
 */
public class Square implements Comparable<Square>
{
    /**
     * The tile to be placed in square.
     */
    protected Tile tile;

    private char letter;
    /**
     * The square's name.
     */
    private String name;

    /**
     * The square's row and column number.
     */
    private int rowNum; //x position
    private int columnNum; //y position

    /**
     * Constructs a new square with the specified name, row and column number.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public Square(int rowNum, int columnNum)
    {
        this.name = name;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.letter = (char)-1; //set it to null
    }

    public Square(int rowNum, int columnNum, char letter)
    {
        this.name = name;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.letter = letter;
    }
    /**
     * Gets letter in square
     *
     * @return letter in square
     */
    public char getLetter(){
        return this.tile.getLetter();
    }

    /**
     *
     * @return this.content is not empty
     */
    public boolean hasLetter() {
        return letter!=((char)-1);
    }
    /**
     * @param letter - sets content
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }
    /**
     * Returns this square's number row.
     *
     * @return The square's number row.
     */
    public int getRowNum()
    {
        return rowNum;
    }

    /**
     * Returns this square's number column.
     *
     * @return The square's number column.
     */
    public int getColumnNum()
    {
        return columnNum;
    }

    /**
     * @param o - square to compare with
     * @return 0 if same row and same col
     *
     * Written to implement comparable for sorting
     * purposes in GameBoard.addWord method
     */

    @Override
    public int compareTo(Square o) {
        if (this.rowNum!=o.getRowNum()) {
            if (this.rowNum > o.getRowNum()) return 1;
            return -1;
        } else if (this.columnNum != o.getColumnNum()) {
            if (this.columnNum > o.getColumnNum()) return 1;
            return -1;
        } else { return 0;}
    }
}
