import java.util.EventObject;

public class ScrabbleEvent extends EventObject {
    int x, y;

    public ScrabbleEvent(ScrabbleModel scrabbleModel, int x, int y) { //Possibly add var to dictate who's turn it is
        super(scrabbleModel);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
