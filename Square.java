import javax.swing.*;
import java.awt.*;

/**
 * Square is the superclass of the different types of
 * square on the ScrabbleModel game board.
 *
 * Removed the abstract for now
 * @author Saad Eid
 */
public class Square extends JComponent implements Comparable<Square>
{
    private char letter;
    /**
     * The square's name.
     */
    private String name;

    /**
     * Points multiplied by letter if filled
     */
    private final static int pointMultiplier = 1;

    /**
     * The square's row and column number.
     */

    private int rowNum; //x position
    private int columnNum; //y position

    /**
     * Constructs a new square with the specified name, row and column number.
     *
     * @param rowNum The square's row number.
     * @param columnNum The square's row number.
     */
    public Square(int rowNum, int columnNum)
    {
        this.name = name;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.letter = (char)-1; //set it to null
    }

    public Square(int rowNum, int columnNum, char letter)
    {
        this.name = name;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.letter = letter;
    }
    /**
     * Gets letter in square
     *
     * @return letter in square
     */
    public char getLetter(){
        return this.letter;
    }

    /**
     *
     * @return this.content is not empty
     */
    public boolean hasLetter() {
        return letter!=((char)-1);
    }
    /**
     * @param letter - sets content
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }
    /**
     * Returns this square's number row.
     *
     * @return The square's number row.
     */
    public int getRowNum()
    {
        return rowNum;
    }

    /**
     * Returns this square's number column.
     *
     * @return The square's number column.
     */
    public int getColumnNum()
    {
        return columnNum;
    }

    /**
     * @param o - square to compare with
     * @return 0 if same row and same col
     *
     * Written to implement comparable for sorting
     * purposes in GameBoard.addWord method
     */

    @Override
    public int compareTo(Square o) {
        if (this.rowNum!=o.getRowNum()) {
            if (this.rowNum > o.getRowNum()) return 1;
            return -1;
        } else if (this.columnNum != o.getColumnNum()) {
            if (this.columnNum > o.getColumnNum()) return 1;
            return -1;
        } else { return 0;}
    }

    /**
     * see super class documentation
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Red: triple word
        //Blue: triple letter
        //Magenta: double word
        //Cyan: double letter
        //Pink: Starting square

        int i;
        for (i = 0; i < 15; ++i) {
            if(rowNum == 0 || rowNum == 14){
                if(columnNum == 0 || columnNum == 7 || columnNum == 14){
                    g.setColor(Color.red);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }

            if(rowNum == 7){
                if(columnNum == 0 || columnNum == 14){
                    g.setColor(Color.red);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
        }

        for (i = 0; i < 15; ++i) {
            if(rowNum == 1 || rowNum == 13){
                if(columnNum == 5 || columnNum == 9){
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }

            if(rowNum == 5 || rowNum == 9){
                if(columnNum == 1 || columnNum == 5 || columnNum == 9 || columnNum == 13){
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
        }

        for (i = 0; i < 15; ++i) {
            if(rowNum == 1 || rowNum == 13){
                if(columnNum == 1 || columnNum == 13){
                    g.setColor(Color.magenta);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 2 || rowNum == 12){
                if(columnNum == 2 || columnNum == 12){
                    g.setColor(Color.magenta);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 3 || rowNum == 11){
                if(columnNum == 3 || columnNum == 11){
                    g.setColor(Color.magenta);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 4 || rowNum == 10){
                if(columnNum == 4 || columnNum == 10){
                    g.setColor(Color.magenta);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
        }

        for (i = 0; i < 15; ++i) {
            if(rowNum == 0 || rowNum == 14){
                if(columnNum == 3 || columnNum == 11){
                    g.setColor(Color.cyan);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 2 || rowNum == 12){
                if(columnNum == 6 || columnNum == 8){
                    g.setColor(Color.cyan);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 3 || rowNum == 11){
                if(columnNum == 0 || columnNum == 7|| columnNum == 14){
                    g.setColor(Color.cyan);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 6 || rowNum == 8){
                if(columnNum == 2 || columnNum == 6|| columnNum == 8|| columnNum == 12){
                    g.setColor(Color.cyan);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
            if(rowNum == 7){
                if(columnNum == 3 || columnNum == 11){
                    g.setColor(Color.cyan);
                    g.fillRect(0, 0, 36, 33);
                    g.drawRect(0, 0, 36, 33);
                }
            }
        }

        //Regular squares
        if(rowNum == 0 || rowNum == 14){
            if(columnNum == 1 ||columnNum == 2 || columnNum == 4 || columnNum == 5 ||columnNum == 6|| columnNum == 8|| columnNum == 9|| columnNum == 10|| columnNum == 12|| columnNum == 13){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }
        if(rowNum == 1 || rowNum == 13){
            if(columnNum == 0 ||columnNum == 2 || columnNum == 3 ||columnNum == 4 ||columnNum == 6|| columnNum == 7 ||columnNum == 8|| columnNum == 10|| columnNum == 11 ||columnNum == 12|| columnNum == 14){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }

        if(rowNum == 2 || rowNum == 12){
            if(columnNum == 0 || columnNum == 1 || columnNum == 3 ||columnNum == 4 || columnNum == 5 || columnNum == 7 ||columnNum == 9 || columnNum == 10|| columnNum == 11 ||columnNum == 13|| columnNum == 14){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }
        if(rowNum == 3 || rowNum == 11){
            if(columnNum == 1 || columnNum == 2 ||columnNum == 4 || columnNum == 5 || columnNum == 6 ||columnNum == 8 ||columnNum == 9 || columnNum == 10|| columnNum == 12 ||columnNum == 13){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }
        if(rowNum == 4 || rowNum == 10){
            if(columnNum == 0 ||columnNum == 1 || columnNum == 2 ||columnNum == 3 || columnNum == 5 || columnNum == 6 ||columnNum == 7 ||columnNum == 8 ||columnNum == 9 || columnNum == 11|| columnNum == 12 ||columnNum == 13||columnNum == 14){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }
        if(rowNum == 5 || rowNum == 9){
            if(columnNum == 0 || columnNum == 2 ||columnNum == 3 || columnNum == 4 || columnNum == 6 ||columnNum == 7 ||columnNum == 8 ||columnNum == 10 || columnNum == 11|| columnNum == 12 ||columnNum == 14){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }
        if(rowNum == 6 || rowNum == 8){
            if(columnNum == 0 ||columnNum == 1 ||columnNum == 3 || columnNum == 4 || columnNum == 5 ||columnNum == 7 ||columnNum == 9 ||columnNum == 10 || columnNum == 11|| columnNum == 13 ||columnNum == 14){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }

        if(rowNum == 7){
            if(columnNum == 1 ||columnNum == 2 || columnNum == 4 || columnNum == 5 ||columnNum == 6 ||columnNum == 8 ||columnNum == 9 ||columnNum == 10 || columnNum == 12|| columnNum == 13){
                g.setColor(Color.black);
                g.drawRect(0, 0, 36, 33);
            }
        }

        if (rowNum==7 && columnNum==7) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, 36, 33);
            g.drawRect(0, 0, 36, 33);
        }
        if (this.hasLetter()) {
            g.setColor(Color.black);
            g.setFont(new Font("Verdana", Font.PLAIN, 25));
            g.drawString(Character.toString(letter), 35/3, 35-(35/3));
        }
    }

    /**
     * see super class documentation
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(35, 35);
    }

    /**
     * see super class documentation
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(35, 35);
    }

}
