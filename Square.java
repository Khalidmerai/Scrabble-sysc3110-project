/**
 * Square is the superclass of the different types of
 * square on the ScrabbleModel game board.
 *
 * @author Saad Eid
 */
public abstract class Square
{
    /**
     * The tile to be placed in square.
     */
    protected Tile tile;
    /**
     * This tile will act as a placeholder
     */
    private Tile emptyTile = new Tile(' ');
    /**
     * The square's name.
     */
    private String name;

    /**
     * The square's row and column number.
     */
    private int rowNum; //x position
    private int columnNum; //y position

    protected boolean isFilled;
    /**
     * Constructs a new square with the specified name, row and column number.
     *
     * @param name The square's name.
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public Square(String name, int rowNum, int columnNum)
    {
        this.name = name;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.tile = emptyTile;
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
     * Fills the square with tile
     *
     * @return The square's number column.
     */
    public void placeTile(Tile tile){
        this.isFilled = true;
        this.tile = tile;
    }

    /**
     * Removes the tile from square
     *
     * @return The square's number column.
     */
    public void removeTile(){
        this.isFilled = false;
        this.tile = null;
    }

    /**
     * Checks if square is empty
     *
     * @return if square is filled
     */
    public boolean isFilled(){
        return this.isFilled;
    }

    /**
     * Returns the description of this square.
     *
     * @return A string containing the name of this square.
     */
    public String toString(){
        if (isFilled){
            return " " + this.tile.toString() + " ";
        }
        else{
            return " _ ";
        }
    }
}
