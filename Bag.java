import java.util.ArrayList;
import java.util.Random;
/*
Khalid merai 101159203
Scharara Islam 101149731
 */
public class Bag {
    public ArrayList<Tile> letter; //  initial words
    public Bag(){
        Tile a = new Tile('A', 1);
        Tile b = new Tile('B', 3);
        Tile c = new Tile('C', 3);
        Tile d = new Tile('D', 2);
        Tile e = new Tile('E', 1);
        Tile f = new Tile('F', 4);
        Tile g = new Tile('G', 2);
        Tile h = new Tile('H', 4);
        Tile i = new Tile('I', 1);
        Tile j = new Tile('J', 8);
        Tile k = new Tile('K', 5);
        Tile l = new Tile('L', 1);
        Tile m = new Tile('M', 3);
        Tile n = new Tile('N', 1);
        Tile o = new Tile('O', 1);
        Tile p = new Tile('P', 3);
        Tile q = new Tile('Q', 10);
        Tile r = new Tile('R', 1);
        Tile s = new Tile('S', 1);
        Tile t = new Tile('T', 1);
        Tile u = new Tile('U', 1);
        Tile v = new Tile('V', 4);
        Tile w = new Tile('W', 4);
        Tile x = new Tile('X', 8);
        Tile y = new Tile('Y', 4);
        Tile z = new Tile('Z', 10);
        Tile blank = new Tile('_', 0);

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
    public Tile getNextTile(){
        return Tile.remove(0);
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
