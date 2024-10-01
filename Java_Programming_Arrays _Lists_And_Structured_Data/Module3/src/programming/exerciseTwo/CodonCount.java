package programming.exerciseTwo;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    /** Class to identify the numbers of time each codon appears in a reading frame. */
    private HashMap<String,Integer> dnaCodons;

    public CodonCount() {
        dnaCodons = new HashMap<String,Integer>();
    }

    private void buildCodonMap(int start, String dna) {
        /** This method will build a new map of codons mapped to their counts from the string dna with the reading
         * frame with the position start (a value of 0, 1, or 2). */
        int currStartIndex = start;
        dnaCodons = new HashMap<String,Integer>();

        while (currStartIndex + 3 <= dna.length()) {
            String codon = dna.substring(currStartIndex,currStartIndex+3);
            if (dnaCodons.keySet().contains(codon)) {
                dnaCodons.put(codon, dnaCodons.get(codon) + 1);
            } else {
                dnaCodons.put(codon, 1);
            }
            currStartIndex = currStartIndex + 3;
        }
    }

    private String getMostCommonCodon() {
        /** This method returns a String, the codon in a reading frame that has the largest count. */
        /** This method assumes the HashMap of codons to counts has already been built.. */
        String mostCommonCodon = "";
        Integer mostCommonValue = 0;
        for (String key : dnaCodons.keySet()) {
            if (dnaCodons.get(key) > mostCommonValue) {
                mostCommonValue = dnaCodons.get(key);
                mostCommonCodon = key;
            }
        }
        return mostCommonCodon;
    }

    private void printCodonCounts(int start, int end) {
        /** This method prints all the codons in the HashMap along with their counts if their count is between start
         * and end, inclusive. */
        for (String key : dnaCodons.keySet()) {
            if (dnaCodons.get(key) >= start && dnaCodons.get(key) <= end) {
                System.out.println(key +" "+dnaCodons.get(key));
            }
        }
    }

    public void testCodonCount() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        int[] possibleReadingFrames = {0,1,2};
        int start = 7;
        int end = 7;

        for (int i : possibleReadingFrames) {
            buildCodonMap(i,dna);
            System.out.println("Reading frame starting with "+i+" results in "+dnaCodons.size()+" unique codons.");
            System.out.println("Most common codon is "+getMostCommonCodon()+" with count "+dnaCodons.get(getMostCommonCodon()));
            System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
            printCodonCounts(start,end);
            System.out.println("");
        }
    }
}

