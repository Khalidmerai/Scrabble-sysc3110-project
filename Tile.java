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
    public Tile(char letter){
        this.letter = letter;

        switch(letter) {
            // 0 point letters
            case '_':
                this.value = 0;
                break;
            //1 point letters
            case 'A':
            case 'E':
            case 'I':
            case 'L':
            case 'N':
            case 'O':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
                this.value = 1;
                break;

            //2 point letters
            case 'D':
            case 'G':
                this.value = 2;
                break;

            //3 point letters
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                this.value = 3;
                break;

            //4 point letters
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                this.value = 4;
                break;

            //5 point letters
            case 'K':
                this.value = 5;
                break;

            //8 points letters
            case 'J':
            case 'X':
                this.value = 8;
                break;

            //10 points letters
            case 'Q':
            case 'Z':
                this.value = 10;
                break;
        }
    }

    /*
    This method gets the letter from the HashMap letterVal
    @returns the letter
     */
    public char getLetter() {
        return this.letter;
    }
    /*
    This method gets the value of letter from the HashMap letterVal
    @returns the value of the letter
     */
    public int getValue() {
        return this.value;
    }

    /*
    This method returns the tile with the selected letter and value.
    @return the tile with the selected letter and value
     */
    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", value=" + value +
                '}';
    }

    /*
    This method checks whether the object is equal to the tile
    @returns True if both letters are equal to each other
    @returns false if object is instance of the Tile
    @returns false if both letters are not equal to each other
     */
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
        Tile tile = new Tile('A');
        System.out.println("Value: " + tile.getValue());
    }
}
