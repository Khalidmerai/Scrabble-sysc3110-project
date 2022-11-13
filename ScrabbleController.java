import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class ScrabbleController extends MouseAdapter implements ActionListener {

    private ScrabbleModel model;
    private String letterSelected;
    /**
     * The number of rows in the board.
     */
    private static final int numRows = 15;
    /**
     * The number of columns in the board.
     */
    private static final int numColumns = 15;
    private static final int numOfLetters = 26;

    public ScrabbleController(ScrabbleModel model) {
        this.model = model;
    }
    
    /**
     * Method that executes the intended action of each button
     * @param e of type action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] input = e.getActionCommand().split(" ");
        
        if(e.getActionCommand().equals("Quit")){
            var result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            switch (result) {
                case JOptionPane.YES_OPTION:
                    System.exit(1);
                    break;
                case JOptionPane.NO_OPTION:
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.getRootFrame().dispose();
                    break;
            }
        }/*
        else if(e.getActionCommand().equals("Pass")) {
            model.checkForCommandWords("Pass");
        }
        else if(e.getActionCommand().equals("Submit")){
            model.checkForCommandWords("Submit");
        }*/
        else{
            System.out.println("action command " + e.getActionCommand());
            for(char c = 'A'; c <= 'Z'; ++c){
                if(e.getActionCommand().equals(String.valueOf(c))){
                    letterSelected = e.getActionCommand();
                    System.out.println("Letter Selected: " + letterSelected);
                    //model.removeLetterFromPlayerRack(letterSelected);
                }
            }
            for(int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (e.getActionCommand().equals(i + " " + j)) {
                        int x = Integer.parseInt(input[0]);
                        int y = Integer.parseInt(input[1]);
                        //model.play(x, y, letterSelected);
                        letterSelected = " ";
                    }
                }
            }
        }
    }
}
