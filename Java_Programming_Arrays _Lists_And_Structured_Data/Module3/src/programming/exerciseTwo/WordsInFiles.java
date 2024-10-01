package programming.exerciseTwo;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/** Program to determine which words occur in the greatest number of files, and for each word, which files they occur in.  */
public class WordsInFiles {
    /** It will store words and file names the word is in. */
    HashMap<String, ArrayList<String>> words;

    public WordsInFiles() {
            words =  new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        /**  This method should add all the words from f into the map. */
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            //word = word.toLowerCase(); //Questions 15 from the final quiz don't recognize it.
            char lastchar = word.charAt(word.length()-1);
            if (!Character.isLetter(lastchar)){
                word = word.substring(0,word.length()-1);
            }
            if (words.containsKey(word)) {
                //Word already exist.
                if(!words.get(word).contains(f.getName()))
                    words.get(word).add(f.getName());
            } else {
                //Word doesn't exist yet.
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                words.put(word,fileNames);
            }
        }
    }

    private void buildWordFileMap() {
        /** This method first clears the map, and then uses a DirectoryResource to select a group of files. */
        words.clear();

        DirectoryResource directoryResource = new DirectoryResource();
        for (File f : directoryResource. selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        /** This method returns the maximum number of files any word appears in, considering all words from a group of files. */
        int maxNumber = 0;

        for (String k : words.keySet()) {
            if (words.get(k).size() > maxNumber) {
                maxNumber = words.get(k).size();
            }
        }
        return maxNumber;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        /** This method returns an ArrayList of words that appear in exactly number files. */
        ArrayList<String> wordInFiles = new ArrayList<String>();
        for (String k : words.keySet()) {
            if (words.get(k).size() == number) {
                wordInFiles.add(k);
            }
        }

        return wordInFiles;
    }

    public void tester () {
        /** This method prints the names of the files this word appears in, one filename per line. */
        buildWordFileMap();
//        ArrayList<String> wordInFiles = wordsInNumFiles(maxNumber());
//        ArrayList<String> wordInFiles = wordsInNumFiles(4);
//        System.out.println("Number of words apperars in 4 files: "+wordInFiles.size());
//        for (int i = 0; i < wordInFiles.size(); i++) {
//            System.out.println(wordInFiles.get(i));
//        }

        // Print the HashMap
//        System.out.println("Entire HashMap --------------------------------------- ");
//        for (HashMap.Entry<String, ArrayList<String>> entry : words.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

//        /** Using and Improving GladLibs QUIZ - Question 4. */
//        ArrayList<String> wordForQuiz = wordsInNumFiles(5);
//        System.out.println("How many words are there that occur in five files?: "+wordForQuiz.size());

//        /** Using and Improving GladLibs QUIZ - Question 5. */
//        ArrayList<String> wordForQuiz = wordsInNumFiles(4);
//        System.out.println("How many words are there that each appear in four of the five files?"+wordForQuiz.size());

//        /** Using and Improving GladLibs QUIZ - Question 6. */
//        for (HashMap.Entry<String, ArrayList<String>> entry : words.entrySet()) {
//            if(entry.getKey().equals("sad")) {
//                System.out.println(entry.getValue());
//            }
//        }
//
//        /** Using and Improving GladLibs QUIZ - Question 7. */
//        for (HashMap.Entry<String, ArrayList<String>> entry : words.entrySet()) {
//            if(entry.getKey().equals("red")) {
//                System.out.println("In which files does the word “red” appear? :"+entry.getValue());
//            }
//        }

//        /** Using and Improving GladLibs FInal QUIZ - Question 14. */
//        for (HashMap.Entry<String, ArrayList<String>> entry : words.entrySet()) {
//            if(entry.getKey().equals("laid")) {
//                System.out.println("In which files does the word “laid” appear? :"+entry.getValue());
//            }
//        }

        /** Using and Improving GladLibs FInal QUIZ - Question 15. */
        for (HashMap.Entry<String, ArrayList<String>> entry : words.entrySet()) {
            if(entry.getKey().equals("tree")) {
                System.out.println("In which files does the word “tree” appear? :"+entry.getValue());
                System.out.println("(Consider only the exact lowercase string \"tree\". \"TREE\" or \"tree.\" would be different words.)");
            }
        }

    }
}
