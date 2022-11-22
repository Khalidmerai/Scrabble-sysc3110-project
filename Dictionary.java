
import java.io.*;
import java.util.*;

/**
 * Author: Scharara Islam 101149731 and Khalid Merai 101159203
 * This class is to import the list of word that is acceptable in the ScrabbleModel game.
 * The link is provided on the project description and downloaded as a .txt file
 */
public class Dictionary {
    public int length;
    private ArrayList<String> dictionaryList = new ArrayList<String>();
    private Set<String> allWords = new TreeSet<String>(); //all words
    private static final String wordList = "wordlist.10000.txt";

    /**
     * Constructs a dictionary from words provided by a TokenScanner.
     * <p>
     * A word is any sequence of letters (see Character.isLetter) or apostrophe
     * characters. All non-word tokens provided by the TokenScanner should be ignored.
     *
     * <p>
     *
     * @param ts sequence of words to use as a dictionary
     * @throws IOException if error while reading
     * @throws IllegalArgumentException if the provided reader is null
     */
    public Dictionary(TokenScanner ts) throws IOException {
        if (ts == null) throw new IllegalArgumentException();
        while(ts.hasNext()) {
            String s = ts.next();
            if (TokenScanner.isWord(s)) {
                allWords.add(s.toLowerCase());
            }
        }
    }

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

    /**
     * Constructs a dictionary from words from a file.

     * @param filename location of file to read words from
     * @throws FileNotFoundException if the file does not exist
     * @throws IOException if error while reading
     */
    public static Dictionary make(String filename) throws IOException {
        Reader r = new FileReader(filename);
        Dictionary d = new Dictionary(new TokenScanner(r));
        r.close();
        return d;
    }

    /**
     * Returns the number words in dictionary
     *
     * @return number of unique words in the dictionary
     */
    public int getNumWords() {
        return allWords.size();
    }

    /**
     * Test whether the input word is present in the Dictionary. If the word
     * is not in the Dictionary the method should return false.
     *
     * @param word a string token to check. Assume any leading or trailing
     *    whitespace has already been removed.
     * @return whether the word is in the dictionary
     */
    public boolean isWord(String word) {
        if (word==null) return false;
        if (TokenScanner.isWord(word)) {
            return allWords.contains(word.toLowerCase());
        } else return false;
    }
}
