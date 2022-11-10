/**
 * The test class for Tiles

public class TileTest extends junit.framework.TestCase{
    private Tile blank, a, d, b, f, k, j, q;

    /**
     * Default constructor for test class SquareTest

    public TileTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.

    protected void setUp()
    {
        blank = new Tile('_');
        a = new Tile('A');
        d = new Tile('D');
        b = new Tile('B');
        f = new Tile('F');
        k = new Tile('K');
        j = new Tile('J');
        q = new Tile('Q');
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.

    protected void tearDown()
    {
    }

    /* Verify that the tiles have the correct letter

    public void testTileLetters()
    {
        assertEquals('_', blank.getLetter());
        assertEquals('A', a.getLetter());
        assertEquals('D', d.getLetter());
        assertEquals('B', b.getLetter());
        assertEquals('F', f.getLetter());
        assertEquals('K', k.getLetter());
        assertEquals('J', j.getLetter());
        assertEquals('Q', q.getLetter());
    }

    /* Verify that the tiles have the correct letter value

    public void testTileValue()
    {
        assertEquals(0, blank.getValue());
        assertEquals(1, a.getValue());
        assertEquals(2, d.getValue());
        assertEquals(3, b.getValue());
        assertEquals(4, f.getValue());
        assertEquals(5, k.getValue());
        assertEquals(8, j.getValue());
        assertEquals(10, q.getValue());
    }
}
     */