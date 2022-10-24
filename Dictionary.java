//Scharara islam 101149731
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {
    private ArrayList<String> dictionaryList = new ArrayList<String>();

    private static final String wordList = "wordlist.10000.txt";
//construct the dictionary and import text file
    public Dictionary() throws IOException, FileNotFoundException{
        try (Scanner fileScanner = new Scanner(new File(getClass().getResource(wordList).getFile()))) {
            while (fileScanner.hasNext()) {
                dictionaryList.add(fileScanner.next());
            }
        }
    }
//checks if the word contains the list that is provided
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
}
