package programming.exercise;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private  ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();

        /** Get each freq from words */
        for (String word : fr.words()) {
            String wordLower = word.toLowerCase();
            int idx = myWords.indexOf(wordLower);
            if (idx == -1) {
                myWords.add(wordLower);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx,freq+1);
            }
        }
    }

    public int findIndexOfMax() {
        int index = -1;
        int maxValue = 0;

        for (int k = 0; k < myWords.size(); k++) {
            if (myFreqs.get(k) > maxValue) {
                index = k;
                maxValue = myFreqs.get(k);
            }
        }
        return index;
    }

    public void tester() {
        findUnique();

        /** Print all words */
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myWords.get(k)+" : "+myFreqs.get(k));
        }

        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
    }
}
