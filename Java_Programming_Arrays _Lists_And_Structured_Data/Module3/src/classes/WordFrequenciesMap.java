package classes;

import edu.duke.FileResource;
import programming.exercise.WordFrequencies;

import java.util.ArrayList;
import java.util.HashMap;

public class WordFrequenciesMap {

    public static void countWords() {
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int total=0;

        for (String w : fr.words()) {
            w = w.toLowerCase();
            if (map.keySet().contains(w)) {
                map.put(w,map.get(w) + 1);
            } else {
                map.put(w,1);
            }
        }

        for (String w : map.keySet()) {
            int occurrences = map.get(w);
            if (occurrences > 500) {
                System.out.println(occurrences+"\t"+w);
            }
        }
    }

    public static void main(String[] args) {
        countWords();
    }
}
