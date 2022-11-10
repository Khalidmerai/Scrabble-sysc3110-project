
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Scharara Islam 101149731 and Khalid Merai 101159203
 * This class is to import the list of word that is acceptable in the ScrabbleModel game.
 * The link is provided on the project description and downloaded as a .txt file
 */
public class Dictionary {
    private String letters;
    private ArrayList<String> dictionaryList = new ArrayList<String>();
    public int row;
    public int column;
    private char c ;

    private static final String wordList = "wordlist.10000.txt";

    /**
     * construct the dictionary and import text file
     */
    public Dictionary() {
        Scanner filename = null;
        boolean verified;
        try {
            filename = new Scanner(new File(wordList));
            verified = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            verified = false;
        }
        while (filename.hasNext()) {
            dictionaryList.add(filename.next());
        }
    }
    public int getColumn(){
        return column;
    }

    public int getRow() {
        return row;
    }

    /**
     * Check if the word from the player matches the dictionary list
     * @param wordContain
     * @return  true if the word contains the list that is provided, otherwise false
     */
    public boolean checkWord(String wordContain){
        if (wordContain.length() == 1){
            return true;
        }
        char[] chars = wordContain.toCharArray();
        Arrays.sort(chars);
        String playerword = "";
        for(int index = 0; index<chars.length; index++){
            playerword += chars[index];
        }
        String list;
        list = dictionaryList.get(Integer.parseInt(playerword));

        if(list == null){
            return false;
        }
        if (list.contains(wordContain)){
            return true;
        }
        return false;
    }

    public boolean Horizontal() {
        return c == 'B';
    }
    public boolean Vertical() {
        return c == 'A';
    }

    public int length() {
        return letters.length();
    }
}
