/*
The tile method describes the number of points for each lettered tile is based on the
board
@author Khalid Merai 101159203
@version Sunday, October 16, 2022
 */

import java.util.*;
import java.util.HashMap;

public class Tile {
    char letter;
    int value;
    static HashMap<Character, Integer> letterVal ;
    public Tile(char letter, int value){
        this.letter = letter;
        this.value = value;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public boolean equals(Object obj) {
        Tile x;
        if(!(obj instanceof Tile)){
            return false;
        }
        else {
            x = (Tile)obj;
        }
        if (this.letter == x.letter){
            return true;
        } else{
            return false;
        }
    }
    public static void main(String[] args){
        letterVal = new HashMap<Character, Integer>();
        letterVal.put('A',1);
        letterVal.put('B',3);
        letterVal.put('C',3);
        letterVal.put('D',2);
        letterVal.put('E',1);
        letterVal.put('F',4);
        letterVal.put('G',2);
        letterVal.put('H',4);
        letterVal.put('I',1);
        letterVal.put('J',8);
        letterVal.put('K',5);
        letterVal.put('L',1);
        letterVal.put('M',3);
        letterVal.put('N',1);
        letterVal.put('O',1);
        letterVal.put('P',1);
        letterVal.put('Q',10);
        letterVal.put('R',1);
        letterVal.put('S',1);
        letterVal.put('T',1);
        letterVal.put('U',1);
        letterVal.put('V',4);
        letterVal.put('W',4);
        letterVal.put('X',8);
        letterVal.put('Y',4);
        letterVal.put('Z',10);
        System.out.println("Value: "+ letterVal);
    }
}
