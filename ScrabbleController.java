import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrabbleController extends MouseAdapter implements ActionListener {

    private ScrabbleModel tempModel, model;
    private String letterSelected;
    private ScrabbleFrame scrabbleFrame;
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    private static final int numOfLetters = 26;

    public ScrabbleController(ScrabbleModel tempModel, ScrabbleModel model, ScrabbleFrame view) {
        this.tempModel = tempModel;
        this.model = model;
        this.scrabbleFrame = view;
    }

    /**
     * Method that executes the intended action of each button
     *
     * @param e of type action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.equals("Undo")) {
            scrabbleFrame.resetBoard(tempModel, model);
            scrabbleFrame.selectedLetter.setLetter((char) -1);
            scrabbleFrame.tileBenchPanel.removeAll();
            scrabbleFrame.squaresToSubmit.clear();
            Player currPlayer = (scrabbleFrame.p1.getTurn() ? scrabbleFrame.p1 : scrabbleFrame.p2);
            for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                char c = currPlayer.getLetter(i);
                final JButton b = new JButton(Character.toString(c));
                scrabbleFrame.tileBenchPanel.add(b);

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(b.getText().equals("")) &&
                                (!scrabbleFrame.selectedLetter.hasLetter())) {
                            scrabbleFrame.selectedLetter.setLetter(b.getText().charAt(0));
                            b.setText("");
                        }
                    }
                });
            }
            scrabbleFrame.frame.getContentPane().validate();
            scrabbleFrame.frame.getContentPane().repaint();

        } else if (input.equals("Pass")) {
            scrabbleFrame.resetBoard(tempModel, model);

            scrabbleFrame.selectedLetter.setLetter((char) (-1));
            Player currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p2 : scrabbleFrame.p1; //opposite
            scrabbleFrame.p1.setTurn(!scrabbleFrame.p1.getTurn());
            scrabbleFrame.p2.setTurn(!scrabbleFrame.p2.getTurn());
            scrabbleFrame.turn.setText("It's " + currPlayer.getName() + "'s Turn");

            scrabbleFrame.tileBenchPanel.removeAll();
            for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                char c = currPlayer.getLetter(i);
                final JButton b = new JButton(Character.toString(c));
                scrabbleFrame.tileBenchPanel.add(b);

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(b.getText().equals("")) &&
                                (!scrabbleFrame.selectedLetter.hasLetter())) {
                            scrabbleFrame.selectedLetter.setLetter(b.getText().charAt(0));
                            b.setText("");
                        }
                    }
                });
            }
            //to repack and paint all changes
            scrabbleFrame.frame.getContentPane().validate();
            scrabbleFrame.frame.getContentPane().repaint();
        } else if (input.equals("Swap Tiles")) {
            scrabbleFrame.resetBoard(tempModel, model);
            scrabbleFrame.selectedLetter.setLetter((char) (-1));

            Player currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p1 : scrabbleFrame.p2;
            List<Character> newLetters = scrabbleFrame.letterBag.swapTiles(currPlayer.getAll());
            currPlayer.clear();
            currPlayer.addLetters(newLetters);

            currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p2 : scrabbleFrame.p1; //opposite
            scrabbleFrame.turn.setText("It's " + currPlayer.getName() + "'s Turn");
            scrabbleFrame.p1.setTurn(!scrabbleFrame.p1.getTurn());
            scrabbleFrame.p2.setTurn(!scrabbleFrame.p2.getTurn());
            scrabbleFrame.tileBenchPanel.removeAll();
            scrabbleFrame.selectedLetter.setLetter((char) -1);
            for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                char c = currPlayer.getLetter(i);
                final JButton b = new JButton(Character.toString(c));
                scrabbleFrame.tileBenchPanel.add(b);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!(b.getText().equals("")) &&
                                (!scrabbleFrame.selectedLetter.hasLetter())) {
                            scrabbleFrame.selectedLetter.setLetter(b.getText().charAt(0));
                            b.setText("");
                        }
                    }
                });
            }
            scrabbleFrame.frame.getContentPane().validate();
            scrabbleFrame.frame.getContentPane().repaint();
        } else if (input.equals("Submit")) {
            if (scrabbleFrame.squaresToSubmit.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Make a Move "
                                + "Before Submitting",
                        "Invalid Move", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean firstTurn = ((scrabbleFrame.p1.getScore() == 0 && scrabbleFrame.p2.getScore() == 0));
            int pointsScored = scrabbleFrame.board.addWord(scrabbleFrame.squaresToSubmit, firstTurn);

            if (pointsScored > 0) {
                //update scores and score board
                Player currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p1 : scrabbleFrame.p2;

                currPlayer.addScore(pointsScored);
                scrabbleFrame.score1.setText("\t" + scrabbleFrame.p1.getName() + "'s Score is " + scrabbleFrame.p1.getScore() + " points");
                scrabbleFrame.score2.setText(scrabbleFrame.p2.getName() + "'s Score is " + scrabbleFrame.p2.getScore() + " points");

                List<Character> lettersUsed = new ArrayList<Character>();
                for (Square s : scrabbleFrame.squaresToSubmit) {
                    //Double check this to see if the letter is saved into board[][]
//                    int row = s.getRowNum();
//                    int col = s.getColumnNum();
//                    model.board[row][col].setLetter(s.getLetter());
                    lettersUsed.add(s.getLetter());
                }

                scrabbleFrame.squaresToSubmit.clear();
                scrabbleFrame.selectedLetter.setLetter((char) (-1));
                currPlayer.useLetters(lettersUsed);
                currPlayer.addLetters(scrabbleFrame.letterBag.drawTiles(lettersUsed.size()));

                //when the game ends
                if (currPlayer.getBenchSize() == 0) {
                    boolean pOneWinner = (scrabbleFrame.p1.getScore() > scrabbleFrame.p2.getScore());
                    String winner = (pOneWinner) ? scrabbleFrame.p1.getName() : scrabbleFrame.p2.getName();
                    winner = "The winner is " + winner + "!\n";
                    winner += scrabbleFrame.p1.getName() + " had " + scrabbleFrame.p1.getScore() + " points \n"
                            + scrabbleFrame.p2.getName() + " had " + scrabbleFrame.p2.getScore() + " points";

                    JOptionPane.showMessageDialog(null, winner, "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }

                lettersUsed.clear();

                //change the tile rack to the second player's
                currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p2 : scrabbleFrame.p1; //opposite
                scrabbleFrame.turn.setText("It's " + currPlayer.getName() + "'s Turn");
                scrabbleFrame.p1.setTurn(!scrabbleFrame.p1.getTurn());
                scrabbleFrame.p2.setTurn(!scrabbleFrame.p2.getTurn());

                scrabbleFrame.tileBenchPanel.removeAll();
                for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                    char c = currPlayer.getLetter(i);
                    final JButton b = new JButton(Character.toString(c));
                    scrabbleFrame.tileBenchPanel.add(b);

                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!(b.getText().equals("")) &&
                                    (!scrabbleFrame.selectedLetter.hasLetter())) {
                                scrabbleFrame.selectedLetter.setLetter(b.getText().charAt(0));
                                b.setText("");
                            }
                        }
                    });
                }

                //repack and repaints
                scrabbleFrame.frame.getContentPane().validate();
                scrabbleFrame.frame.getContentPane().repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Move. Try Again",
                        "Invalid Move", JOptionPane.ERROR_MESSAGE);
                //undoes everything if the move was invalid
                scrabbleFrame.undo.doClick();
            }
        }else if (input.equals("Tiles Left")) {
            JOptionPane.showMessageDialog(null, "There are " + scrabbleFrame.letterBag.getTilesLeft() +
                            " tiles left in the game",
                    "Tiles Left", JOptionPane.INFORMATION_MESSAGE);
        } else if (input.equals("Save game")) {
            JOptionPane.showMessageDialog(null, "Saved");

        }else if (input.equals("Custom Board")){
            try {
                model.readSaxCustomBoard(new File("CustomBoard.xml"));
                model.handleCustomBoard();
                scrabbleFrame.customBoard.setEnabled(false);
                scrabbleFrame.frame.getContentPane().validate();
                scrabbleFrame.frame.getContentPane().repaint();
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}