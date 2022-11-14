import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
/**
 * The ScrabbleFrame class initializes the scrabble board by building its different squares
 * and creating buttons for the user to use
 * @author Saad Eid
 */
public class ScrabbleFrame implements ScrabbleView, Runnable{
    public JFrame frame;
    public Bag letterBag;
    public Player p1, p2, currPlayer;
    public JPanel scoreBoard, tileBenchPanel, gameButtonPanel;
    public JLabel score1, score2, turn;
    public Square selectedLetter;
    public List<Square> squaresToSubmit;
    public ScrabbleModel board, tempBoard;
    public ScrabbleController scrabbleController;

    public ScrabbleFrame(){
        frame = new JFrame("Scrabble");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        letterBag = new Bag();
        scoreBoard = new JPanel();
        tileBenchPanel = new JPanel();
        gameButtonPanel = new JPanel();
        p1 = new Player(getUsername("Player 1"), letterBag.drawTiles(7), true);
        p2 = new Player(getUsername("Player 2"), letterBag.drawTiles(7), false);

        //variables to help with player input
        selectedLetter = new Square(-1, -1);
        squaresToSubmit = new LinkedList<Square>();
    }

    public void buildScorePanel(){
        scoreBoard.setLayout(new GridLayout(1, 3));
        score1 = new JLabel("\t\t\t\t"+ p1.getName() + "'s Score is " + p1.getScore() + " points");
        score2 = new JLabel(p2.getName() + "'s Score is " + p2.getScore() + " points");
        turn = new JLabel("\t\t\t\t\t  It's " + p1.getName() + "'s Turn");

        scoreBoard.add(score1);
        scoreBoard.add(turn);
        scoreBoard.add(score2);
    }

    public void buildTileBenchPanel(){
        currPlayer = (p1.getTurn()? p1 : p2);
        for (int i = 0; i < currPlayer.getBenchSize(); i++) {
            char c = currPlayer.getLetter(i);
            JButton b = new JButton(Character.toString(c));
            tileBenchPanel.add(b);
            b.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean blank = b.getText().equals("");
                    boolean selected = selectedLetter.hasLetter();
                    if (!blank && !selected) {
                        selectedLetter.setLetter(b.getText().charAt(0));
                        b.setText("");
                    }
                }
            });
        }
    }

    /**
     * Creates a game board for current state and temporary board for pre-submission state
     */
    public void createScrabbleModels(){
        try {
            board = new ScrabbleModel("wordlist.10000.txt", letterBag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            tempBoard = new ScrabbleModel("wordlist.10000.txt", letterBag);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scrabbleController = new ScrabbleController(tempBoard, board);
    }
    public void run() {
        buildScorePanel();
        buildTileBenchPanel();
        createScrabbleModels();

        Square[][] currBoard = tempBoard.getCurrentBoard();
        for (int row = 0; row < currBoard.length; row++) {
            for (int col = 0; col < currBoard[row].length; col++) {
                final Square sq = currBoard[row][col];
                sq.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ((!sq.hasLetter()) && (selectedLetter.hasLetter())) {
                            sq.setLetter(selectedLetter.getLetter());
                            sq.repaint();
                            squaresToSubmit.add(sq);
                            selectedLetter.setLetter((char)-1);
                        }
                    }
                });
            }
        }

        //undo resets the tileRack to the player's bench
        //also resets the game board
        JButton undo = new JButton("Undo");
        undo.setActionCommand("Undo");
        undo.addActionListener(scrabbleController);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(tempBoard, board);
                selectedLetter.setLetter((char)-1);
                tileBenchPanel.removeAll();
                squaresToSubmit.clear();
                Player currPlayer = (p1.getTurn() ? p1 : p2);
                for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                    char c = currPlayer.getLetter(i);
                    final JButton b = new JButton(Character.toString(c));
                    tileBenchPanel.add(b);

                    b.addActionListener( new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!(b.getText().equals("")) &&
                                    (!selectedLetter.hasLetter())) {
                                selectedLetter.setLetter(b.getText().charAt(0));
                                b.setText("");
                            }
                        }
                    });
                }
                frame.getContentPane().validate();
                frame.getContentPane().repaint();

            }
        });

        //player can opt to pass instead of submitting a move
        JButton pass = new JButton("Pass");
        pass.setActionCommand("Pass");
        pass.addActionListener(scrabbleController);/*
        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(tempBoard, board);

                selectedLetter.setLetter((char)(-1));
                Player currPlayer = (p1.getTurn()) ? p2 : p1; //opposite
                p1.setTurn(!p1.getTurn());
                p2.setTurn(!p2.getTurn());
                turn.setText("It's " + currPlayer.getName() + "'s Turn");

                tileBenchPanel.removeAll();
                for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                    char c = currPlayer.getLetter(i);
                    final JButton b = new JButton(Character.toString(c));
                    tileBenchPanel.add(b);

                    b.addActionListener( new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!(b.getText().equals("")) &&
                                    (!selectedLetter.hasLetter())) {
                                selectedLetter.setLetter(b.getText().charAt(0));
                                b.setText("");
                            }
                        }
                    });
                }
                //to repack and paint all changes
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });*/

        //swap tiles, but give up your turn
        JButton swap = new JButton("Swap Tiles");
        swap.setActionCommand("Swap Tiles");
        swap.addActionListener(scrabbleController);
        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(tempBoard, board);
                selectedLetter.setLetter((char)(-1));

                Player currPlayer = (p1.getTurn()) ? p1 : p2;
                List<Character> newLetters = letterBag.swapTiles(currPlayer.getAll());
                currPlayer.clear();
                currPlayer.addLetters(newLetters);

                currPlayer = (p1.getTurn()) ? p2 : p1; //opposite
                turn.setText("It's " + currPlayer.getName() + "'s Turn");
                p1.setTurn(!p1.getTurn());
                p2.setTurn(!p2.getTurn());
                tileBenchPanel.removeAll();
                selectedLetter.setLetter((char)-1);
                for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                    char c = currPlayer.getLetter(i);
                    final JButton b = new JButton(Character.toString(c));
                    tileBenchPanel.add(b);

                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!(b.getText().equals("")) &&
                                    (!selectedLetter.hasLetter())) {
                                selectedLetter.setLetter(b.getText().charAt(0));
                                b.setText("");
                            }
                        }
                    });
                }
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
            }
        });

        //submit button
        JButton submit = new JButton("Submit");
        submit.setActionCommand("Submit");
        submit.addActionListener(scrabbleController);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (squaresToSubmit.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please Make a Move "
                                    + "Before Submitting",
                            "Invalid Move", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean firstTurn = ((p1.getScore()==0 && p2.getScore()==0));
                int pointsScored = board.addWord(squaresToSubmit, firstTurn);

                if (pointsScored > 0) {
                    //update scores and score board
                    Player currPlayer = (p1.getTurn()) ? p1 : p2;

                    currPlayer.addScore(pointsScored);
                    score1.setText("\t" + p1.getName() + "'s Score is " + p1.getScore() + " points");
                    score2.setText(p2.getName() + "'s Score is " + p2.getScore() + " points");

                    List<Character> lettersUsed = new ArrayList<Character>();
                    for (Square s: squaresToSubmit) {
                        lettersUsed.add(s.getLetter());
                    }

                    squaresToSubmit.clear();
                    selectedLetter.setLetter((char)(-1));
                    currPlayer.useLetters(lettersUsed);
                    currPlayer.addLetters(letterBag.drawTiles(lettersUsed.size()));

                    //when the game ends
                    if (currPlayer.getBenchSize()==0) {
                        boolean pOneWinner = (p1.getScore()>p2.getScore());
                        String winner = (pOneWinner) ? p1.getName() : p2.getName();
                        winner = "The winner is " + winner + "!\n";
                        winner += p1.getName() + " had " + p1.getScore() + " points \n"
                                + p2.getName() + " had " + p2.getScore() + " points";

                        JOptionPane.showMessageDialog(null, winner, "Game Over",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.exit(1);
                    }

                    lettersUsed.clear();

                    //change the tile rack to the second player's
                    currPlayer = (p1.getTurn()) ? p2 : p1; //opposite
                    turn.setText("It's " + currPlayer.getName() + "'s Turn");
                    p1.setTurn(!p1.getTurn());
                    p2.setTurn(!p2.getTurn());

                    tileBenchPanel.removeAll();
                    for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                        char c = currPlayer.getLetter(i);
                        final JButton b = new JButton(Character.toString(c));
                        tileBenchPanel.add(b);

                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!(b.getText().equals(""))&&
                                        (!selectedLetter.hasLetter())) {
                                    selectedLetter.setLetter(b.getText().charAt(0));
                                    b.setText("");
                                }
                            }
                        });
                    }

                    //repack and repaints
                    frame.getContentPane().validate();
                    frame.getContentPane().repaint();


                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Move. Try Again",
                            "Invalid Move", JOptionPane.ERROR_MESSAGE);
                    //undoes everything if the move was invalid
                    undo.doClick();
                }
            }
        });


        JButton checkTilesLeft = new JButton("Tiles Left");
        submit.setActionCommand("Tiles Left");
        checkTilesLeft.addActionListener(scrabbleController);
        checkTilesLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"There are " + letterBag.getTilesLeft() +
                                " tiles left in the game",
                        "Tiles Left", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //add all the buttons
        gameButtonPanel.add(undo);
        gameButtonPanel.add(submit);
        gameButtonPanel.add(pass);
        gameButtonPanel.add(swap);
        gameButtonPanel.add(checkTilesLeft);

        //add panels to frame
        frame.add(scoreBoard);
        frame.add(tempBoard);
        frame.add(tileBenchPanel);
        frame.add(gameButtonPanel);

        //top level stuff
        frame.validate();
        frame.setResizable(true);
        frame.setSize(670, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     *
     * @param tempBoard - current state of board
     * @param actualBoard - board to reset to
     */
    void resetBoard(ScrabbleModel tempBoard, ScrabbleModel actualBoard) {
        Square[][] currBoard = tempBoard.getCurrentBoard();
        Square[][] oldBoard = actualBoard.getCurrentBoard();
        for (int row = 0; row < currBoard.length; row++) {
            for (int col = 0; col < currBoard[row].length; col++) {
                Square sq = currBoard[row][col];
                Square oldSq = oldBoard[row][col];
                sq.setLetter(oldSq.getLetter());
                sq.repaint();
            }
        }
    }


    private String getUsername(String player) {
        String tgt = JOptionPane.showInputDialog(null, player + ", Enter Your Name:");
        if (tgt==null) return getUsername(player);
        else return tgt;
    }

    @Override
    public void update(ScrabbleEvent e) {
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new ScrabbleFrame());
    }
}
