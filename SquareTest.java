/**
 * The test class for the Squares
 */

public class SquareTest extends junit.framework.TestCase{

    private Square startingSqaure, regularSquare, doubleLetterSquare, doubleWordSquare, tripleWordSquare, tripleLetterSquare;

    /**
     * Default constructor for test class SquareTest
     */
    public SquareTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        startingSqaure = new StartingSquare();
        regularSquare = new RegularSquare(4,0);
        doubleLetterSquare = new DoubleLetterSquare(7,13);
        doubleWordSquare = new DoubleWordSquare(1,1);
        tripleWordSquare = new TripleWordSquare(1,7);
        tripleLetterSquare = new TripleLetterSquare(0,7);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /* Verify that the starting square of a newly created game board is
     * Square 1.
     */
    public void testSquareNumbers()
    {
        assertEquals(7, startingSqaure.getRowNum());
        assertEquals(7, startingSqaure.getColumnNum());

        assertEquals(4, regularSquare.getRowNum());
        assertEquals(0, regularSquare.getColumnNum());

        assertEquals(7, doubleLetterSquare.getRowNum());
        assertEquals(13, doubleLetterSquare.getColumnNum());

        assertEquals(1, doubleWordSquare.getRowNum());
        assertEquals(1, doubleWordSquare.getColumnNum());

        assertEquals(1, tripleWordSquare.getRowNum());
        assertEquals(7, tripleWordSquare.getColumnNum());

        assertEquals(0, tripleLetterSquare.getRowNum());
        assertEquals(7, tripleLetterSquare.getColumnNum());
    }
}