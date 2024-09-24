package programming.exerciseTwo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/** Program to determine which words occur in the greatest number of files, and for each word, which files they occur in.  */
public class WordsInFiles {
    /** It will store words and file names the word is in. */
    HashMap<String, String[]> words;

    public WordsInFiles() {
        words =  new HashMap<String, String[]>();
    }

    private void addWordsFromFile(File f) {
        /**  This method should add all the words from f into the map. */
    }

    private void buildWordFileMap() {
        /** This method first clears the map, and then uses a DirectoryResource to select a group of files. */


        //Call method addWordsFromFile
    }

    private int maxNumber() {
        /** This method returns the maximum number of files any word appears in, considering all words from a group of files. */
        return 0;
    }

    private ArrayList wordsInNumFiles(int number) {
        /** This method returns an ArrayList of words that appear in exactly number files. */
        return new ArrayList();
    }

    private void printFilesIn(String word) {
        /** This method prints the names of the files this word appears in, one filename per line. */
    }
}
