import java.util.EventObject;

public class ScrabbleEvent extends EventObject {
    private int x, y;
    private char letter;
    private boolean turn;

    public ScrabbleEvent(ScrabbleModel scrabbleModel, int x, int y, char letter, boolean turn) {
        super(scrabbleModel);
        this.x = x;
        this.y = y;
        this.letter = letter;
        this.turn = turn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean getTurn(){return turn;}

    public char getLetter() {
        return letter;
    }
}
