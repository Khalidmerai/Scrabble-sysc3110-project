import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Author: Scharara Islam 101149731 and Khalid Merai 101159203
 * This class shows that there are 26 letters in a bag.
 */
public class Bag {
    public ArrayList<Tile> letter; //  initial words

    private Map<Character, Integer> letterToAmountLeft = new TreeMap<Character, Integer>();
    private Map<Character, Integer> letterToPointValue = new TreeMap<Character, Integer>();
    private static final String letterList = "letters.txt";

    /**
     * reads file containing letter values and frequencies
     */
    public Bag() {
        try {
            readFile(letterList);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"File Not Found",
                    "Letters File Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error",
                    "Problem with Letters File", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

    }

    /**
     * helper function for constructor to read File
     */
    private void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {

            String[] list = line.split("/");
            char letter = list[0].toCharArray()[0];
            int pointValue = Integer.parseInt(list[1]);
            int amount = Integer.parseInt(list[2]);

            letterToAmountLeft.put(letter, amount);
            letterToPointValue.put(letter, pointValue);
        }
        reader.close();
    }

    /**
     *
     * @return random letter from the "bag"
     * which is really a map of char --> amount left
     */

    private char drawTile() {
        Random r = new Random();
        int size = this.getTilesLeft();
        if (size < 1) throw new RuntimeException("No Tiles Left");

        int pick = r.nextInt(size);
        char toDraw = (char)(-1);
        for (char c: letterToAmountLeft.keySet()) {
            int amountLeft = letterToAmountLeft.get(c);
            pick -= letterToAmountLeft.get(c);
            if (pick <= 0) {
                toDraw = c;
                letterToAmountLeft.put(c, amountLeft-1);
                break;
            }

        }
        return toDraw;
    }

    /**
     *
     * @param amount of letters to draw
     * @return list of characters of size amount
     * where amount is the minimum of amount param and
     * the amount of letters left
     */

    public List<Character> drawTiles(int amount) {
        int amountLeft = this.getTilesLeft();
        if (amount > amountLeft) {
            amount = amountLeft;
        }

        List<Character> tgt = new ArrayList<Character>();
        for (int i = 0; i < amount; i++) {

            tgt.add(this.drawTile());
        }
        return tgt;
    }

    public int getPointValue(char c) {
        return letterToPointValue.get(c);
    }

    /**
     * @return amount of tiles left in bag
     */

    public int getTilesLeft() {
        int sum = 0;
        for (int i : letterToAmountLeft.values()) {
            sum += i;
        }
        return sum;
    }

    /**
     *
     * @param oldLetters to put back in bag
     * @return same amount of new letters from bag
     */

    public List<Character> swapTiles(List<Character> oldLetters) {
        for (char c: oldLetters) {
            int oldValue = letterToAmountLeft.get(c);
            letterToAmountLeft.put(c, oldValue+1);
        }
        return this.drawTiles(oldLetters.size());

    }

    /**
     * Constructor for bag class
     *
    public Bag(){
        Tile a = new Tile('A');
        Tile b = new Tile('B');
        Tile c = new Tile('C');
        Tile d = new Tile('D');
        Tile e = new Tile('E');
        Tile f = new Tile('F');
        Tile g = new Tile('G');
        Tile h = new Tile('H');
        Tile i = new Tile('I');
        Tile j = new Tile('J');
        Tile k = new Tile('K');
        Tile l = new Tile('L');
        Tile m = new Tile('M');
        Tile n = new Tile('N');
        Tile o = new Tile('O');
        Tile p = new Tile('P');
        Tile q = new Tile('Q');
        Tile r = new Tile('R');
        Tile s = new Tile('S');
        Tile t = new Tile('T');
        Tile u = new Tile('U');
        Tile v = new Tile('V');
        Tile w = new Tile('W');
        Tile x = new Tile('X');
        Tile y = new Tile('Y');
        Tile z = new Tile('Z');
        Tile blank = new Tile('_');

        letter = new ArrayList<>(100);
        letter.add(a);letter.add(b);letter.add(c);letter.add(d);letter.add(e);
        letter.add(f);letter.add(g);letter.add(h);letter.add(i);letter.add(j);
        letter.add(k);letter.add(l);letter.add(m);letter.add(n);letter.add(o);
        letter.add(p);letter.add(q);letter.add(r);letter.add(s);letter.add(t);
        letter.add(u);letter.add(v);letter.add(w);letter.add(x);letter.add(y);
        letter.add(z);letter.add(blank);
    }
*/
    /**
     * Method to show how many letters tiles are in the bag
     * @return the size
     */
    public int size(){
        return letter.size();
    }

    /**
     * Method to get a  letter tile from the bag
     * @param nextTile
     * @return the  letter tiles from the bag
     */
   public ArrayList<Tile> getNextTile(int nextTile){
        ArrayList<Tile> newTile = new ArrayList<Tile>();
        for(int i = 0; i<nextTile; i++){
            newTile.add(letter.get(nextTile));
            letter.remove(0);

        }
        return newTile;

    }

    /**
     * Method to shuffle the bag
     * @return the letter tile that was taken out of the bag
     */
    public Tile drag(){
        int tileSize = letter.size() - 1;
        Random rand = new Random();
        int randomTile =  rand.nextInt(tileSize);
        return letter.remove(randomTile);

    }

    /**
     * To check if the bag is empty
     * @return the remaining number of letter tiles from the bag
     */
    public Object bagEmpty(){
        if (letter.isEmpty()){
            return null;
        } else{
            return letter.size();
        }
    }
}
