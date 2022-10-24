import java.util.ArrayList;
import java.util.Random;
/*
Khalid merai 101159203
Scharara Islam 101149731
 */
public class Bag {
    public ArrayList<Tile> letter; //  initial words
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
    public int size(){
        return letter.size();
    }

   public ArrayList<Tile> getNextTile(int nextTile){
        ArrayList<Tile> newTile = new ArrayList<Tile>();
        for(int i = 0; i<nextTile; i++){
            newTile.add(letter.get(nextTile));
            letter.remove(0);

        }
        return newTile;

    }





    public Tile drag(){
        int tileSize = letter.size() - 1;
        Random rand = new Random();
        int randomTile =  rand.nextInt(tileSize);
        return letter.remove(randomTile);

    }
    public Object bagEmpty(){
        if (letter.isEmpty()){
            return null;
        } else{
            return letter.size();
        }
    }
}
