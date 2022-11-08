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

    /**
     * Initalizes the different panels within the frame
     */
    private JPanel gameInfoPanel, gridPanel, rackPanel;

    public ScrabbleFrame(){
        super("Scrabble");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        buttons = new JButton[numRows][numColumns];

        buildPanels();
        buildMenuBar();

        this.setSize(610,650);
        this.setVisible(true);
    }

    /** Incomplete! Will be complete in MileStone 4.
     * This method adds the menu items to the menu bar.
     */
    private void buildMenuBar(){
        ScrabbleModel model = new ScrabbleModel();
        model.addScrabbleView(this);
        ScrabbleController controller = new ScrabbleController(model);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.addActionListener(controller);

        JMenuItem item = new JMenuItem("Undo");
        item.addActionListener(controller);
        menu.add(item);

        item = new JMenuItem("Save");
        item.addActionListener(controller);
        menu.add(item);

        item = new JMenuItem("Load");
        item.addActionListener(controller);
        menu.add(item);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Creates the JPanels needed to showcase the player's game info, grid, and letter rack.
     */
    private void buildPanels(){
        gameInfoPanel = new JPanel();
        gridPanel = new JPanel();
        rackPanel = new JPanel();

        gameInfoPanel.setLayout(new BorderLayout());
        gridPanel.setLayout(new GridLayout(15,15));
        rackPanel.setLayout(new BorderLayout());

        gameInfoPanel.setBounds(10,10,580,30);
        gridPanel.setBounds(10, 50,580,500);
        rackPanel.setBounds(10,560,580,50);

        //The background colours are to signify the
        // location of the panels and will be removed once the info is added
        gameInfoPanel.setBackground(Color.YELLOW);
        buildGuiSquares();
        rackPanel.setBackground(Color.BLUE);

        this.add(gameInfoPanel);
        this.add(gridPanel);
        this.add(rackPanel);
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
                gridPanel.add(b);
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