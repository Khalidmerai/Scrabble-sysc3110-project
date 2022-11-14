import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

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

    public ScrabbleController(ScrabbleModel tempModel, ScrabbleModel model) {
        this.tempModel = tempModel;
        this.model = model;
        scrabbleFrame = new ScrabbleFrame();
    }
    
    /**
     * Method that executes the intended action of each button
     * @param e of type action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        
        if(input.equals("Undo")){
            scrabbleFrame.resetBoard(tempModel, model);
            scrabbleFrame.selectedLetter.setLetter((char)-1);
            scrabbleFrame.tileBenchPanel.removeAll();
            scrabbleFrame.squaresToSubmit.clear();
            Player currPlayer = (scrabbleFrame.p1.getTurn() ? scrabbleFrame.p1 : scrabbleFrame.p2);
            for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                char c = currPlayer.getLetter(i);
                final JButton b = new JButton(Character.toString(c));
                scrabbleFrame.tileBenchPanel.add(b);

                b.addActionListener( new ActionListener() {
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

        }
        else if(input.equals("Pass")) {
            scrabbleFrame.resetBoard(tempModel, model);

            scrabbleFrame.selectedLetter.setLetter((char)(-1));
            Player currPlayer = (scrabbleFrame.p1.getTurn()) ? scrabbleFrame.p2 : scrabbleFrame.p1; //opposite
            scrabbleFrame.p1.setTurn(!scrabbleFrame.p1.getTurn());
            scrabbleFrame.p2.setTurn(!scrabbleFrame.p2.getTurn());
            scrabbleFrame.turn.setText("It's " + currPlayer.getName() + "'s Turn");

            scrabbleFrame.tileBenchPanel.removeAll();
            for (int i = 0; i < currPlayer.getBenchSize(); i++) {
                char c = currPlayer.getLetter(i);
                final JButton b = new JButton(Character.toString(c));
                scrabbleFrame.tileBenchPanel.add(b);

                b.addActionListener( new ActionListener() {
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
        }
        else if(input.equals("Swap Tiles")){

        } else if(input.equals("Submit")) {

        } else if(input.equals("Tiles left")){

        }
    }
}
