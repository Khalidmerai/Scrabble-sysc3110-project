import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrabbleController implements ActionListener {

    private ScrabbleModel model;

    public ScrabbleController(ScrabbleModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] input = e.getActionCommand().split(" ");
        //int x = Integer.parseInt(input[0]);
        //int y = Integer.parseInt(input[1]);
        
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

        }
    }
}
