import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ScrabbleModelTest {

    @org.junit.Test
    public void addingWordOnBoard1() throws IOException {
        Bag letterBag = new Bag();
        ArrayList<ScrabbleView> views = new ArrayList<>();
        ScrabbleModel board = new ScrabbleModel(views, "wordlist.10000.txt", letterBag);
        Player p1 = new Player("Player 1", letterBag.drawTiles(7), true);
        Player p2 = new Player("Player 2", letterBag.drawTiles(7), false);
        Player currPlayer = (p1.getTurn()) ? p1 : p2;
        assertTrue(p1.getTurn());
        assertFalse(p2.getTurn());
        List<Character> newLetters = new ArrayList<>();
        currPlayer.clear();
        newLetters.add('C');
        newLetters.add('A');
        newLetters.add('R');
        newLetters.add('C');
        newLetters.add('C');
        newLetters.add('A');
        newLetters.add('R');
        currPlayer.addLetters(newLetters);
        Square square1 = new Square(7,7, 'C');
        Square square2 = new Square(7,8, 'A');
        Square square3 = new Square(7,9, 'R');
        List<Square> sq = new ArrayList<>();
        sq.add(square1);
        sq.add(square2);
        sq.add(square3);
        board.addWord(sq, true);
        board.board[7][7].setLetter('C');
        board.board[7][8].setLetter('A');
        board.board[7][9].setLetter('R');
        assertEquals(board.board[7][7].getLetter(), square1.getLetter());
        assertEquals(board.board[7][8].getLetter(), square2.getLetter());
        assertEquals(board.board[7][9].getLetter(), square3.getLetter());
    }

    @org.junit.Test
    public void addingWordOnBoard2() throws IOException {
        Bag letterBag = new Bag();
        ArrayList<ScrabbleView> views = new ArrayList<>();
        ScrabbleModel board = new ScrabbleModel(views, "wordlist.10000.txt", letterBag);
        Player p1 = new Player("Player 1", letterBag.drawTiles(7), true);
        Player p2 = new Player("Player 2", letterBag.drawTiles(7), false);
        Player currPlayer = (p1.getTurn()) ? p1 : p2;
        assertTrue(p1.getTurn());
        assertFalse(p2.getTurn());
        List<Character> newLetters = new ArrayList<>();
        currPlayer.clear();
        newLetters.add('C');
        newLetters.add('A');
        newLetters.add('R');
        newLetters.add('C');
        newLetters.add('C');
        newLetters.add('A');
        newLetters.add('R');
        currPlayer.addLetters(newLetters);
        Square square1 = new Square(7,7, 'C');
        Square square2 = new Square(7,8, 'A');
        Square square3 = new Square(7,9, 'R');
        List<Square> sq = new ArrayList<>();
        sq.add(square1);
        sq.add(square2);
        sq.add(square3);
        board.addWord(sq, true);
        board.board[7][7].setLetter('C');
        board.board[7][8].setLetter('A');
        board.board[7][9].setLetter('R');
        assertEquals(board.board[7][7].getLetter(), square1.getLetter());
        assertEquals(board.board[7][8].getLetter(), square2.getLetter());
        assertEquals(board.board[7][9].getLetter(), square3.getLetter());
        sq.clear();
        square1 = new Square(7,7, 'F');
        square3 = new Square(7,9, 'R');
        sq.add(square1);
        sq.add(square3);
        board.addWord(sq, false);
        board.board[6][7].setLetter('F');
        board.board[8][7].setLetter('R');
        assertEquals(board.board[6][7].getLetter(), square1.getLetter());
        assertEquals(board.board[8][7].getLetter(), square3.getLetter());
    }
}