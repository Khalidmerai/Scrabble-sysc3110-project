import javax.swing.*;
import java.awt.*;

/**
 * The ScrabbleFrame class initializes the scrabble board by building its different squares
 * and creating buttons for the user to use
 * @author Saad Eid
 */
public class ScrabbleFrame extends JFrame implements ScrabbleView{
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    /** The squares on the board. */
    public JButton[][] buttons;

    public ScrabbleFrame(){
        super("Scrabble");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(numRows,numColumns));
        buttons = new JButton[numRows][numColumns];

        buildGuiSquares();

        this.setSize(500,500);
        this.setVisible(true);
    }

    /**
     * Creates the Jbuttons and places them on the frame
     */
    private void buildGuiSquares(){
        ScrabbleModel model = new ScrabbleModel();
        model.addScrabbleView(this);
        ScrabbleController controller = new ScrabbleController(model);

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numColumns; j++){
                JButton b = new JButton(); //Place icon image there
                b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                b.addActionListener(controller);
                this.add(b);
            }
        }
    }

    @Override
    public void update(ScrabbleEvent scrabbleEvent) {

    }

    public static void main(String[] args) {
        ScrabbleView view = new ScrabbleFrame();
    }
}
