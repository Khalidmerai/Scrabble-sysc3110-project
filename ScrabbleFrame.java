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
    private JPanel gameInfoPanel, gridPanel, rackPanel, commandWordPanel;
    private ScrabbleModel model;
    private ScrabbleController controller;

    public ScrabbleFrame(){
        super("Scrabble");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        buttons = new JButton[numRows][numColumns];

        model = new ScrabbleModel();
        model.addScrabbleView(this);
        controller = new ScrabbleController(model);

        buildPanels();
        buildMenuBar();
        setUpPlayerRack();
        createCommandButtons();

        this.setSize(750,680);
        this.setVisible(true);
    }


    private void buildMenuBar(){
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
        commandWordPanel = new JPanel();

        gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.Y_AXIS));
        gridPanel.setLayout(new GridLayout(15,15));
        rackPanel.setLayout(new FlowLayout());

        BoxLayout layout = new BoxLayout(commandWordPanel, BoxLayout.Y_AXIS);
        commandWordPanel.setLayout(layout);

        buildGuiSquares();
        //The background colours are to signify the
        // location of the panels and will be removed once the info is added
        gameInfoPanel.setBackground(Color.BLUE);

        this.add(gameInfoPanel, BorderLayout.NORTH);
        this.add(gridPanel,BorderLayout.CENTER);
        this.add(rackPanel, BorderLayout.SOUTH);
        this.add(commandWordPanel, BorderLayout.EAST);
    }

    /**
     * Creates the Jbuttons and places them on the frame
     */
    private void buildGuiSquares(){
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numColumns; j++){
                JButton b = new JButton(); //Place icon image there
                b.setMargin(new Insets(1,1,1,1));
                b.setActionCommand(i + " " + j);
                buttons[i][j] = b;
                b.addActionListener(controller);
                gridPanel.add(b);
            }
        }
    }

    private void createCommandButtons(){
        JButton button = new JButton("Pass");
        button.setActionCommand("Pass");
        button.setSize(15,30);
        button.addActionListener(controller);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        commandWordPanel.add(button);

        button = new JButton("Submit");
        button.setActionCommand("Submit");
        button.addActionListener(controller);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        commandWordPanel.add(button);

        button = new JButton("Quit");
        button.setActionCommand("Quit");
        button.addActionListener(controller);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        commandWordPanel.add(button);
    }

    private void setUpPlayerRack(){
        Player player = model.getFirstPlayer();
        for(Tile t: player.getRack()){
            JButton button = new JButton(String.valueOf(t.getLetter()).toUpperCase());
            button.setActionCommand(String.valueOf(t.getLetter()).toUpperCase());
            button.addActionListener(controller);
            rackPanel.add(button, BorderLayout.SOUTH);
        }
    }
    @Override
    public void update(ScrabbleEvent e) {
        char label = e.getLetter();
        buttons[e.getX()][e.getY()].setText(String.valueOf(label));
    }

    public static void main(String[] args) {
        ScrabbleView view = new ScrabbleFrame();
    }
}
