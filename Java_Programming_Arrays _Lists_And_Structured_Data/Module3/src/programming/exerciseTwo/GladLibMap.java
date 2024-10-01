package programming.exerciseTwo;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private String[] categories;
    private HashMap<String, ArrayList<String>> myMap;

    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private int totalReplaces;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "resources2/GladLibData/data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        totalReplaces = 0;

    }

    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
        totalReplaces = 0;
    }

    private void initializeFromSource(String source) {
       /** Initialize the HashMap with values to substitute from files. */
        categories = new String[]{"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};

        for (int i = 0; i < categories.length; i++) {
            myMap.put(categories[i],readIt(source+"/"+categories[i]+".txt"));
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        /** Call method randomFrom passing the associate ArrayList from the label. */
        if (!label.equals("number")) {
            return randomFrom(myMap.get(label));
        } else {
            return ""+myRandom.nextInt(50)+5;
        }
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub = getSubstitute(label);

        if (usedWords.contains(sub)) {
            processWord(w);
        } else {
            usedWords.add(sub);
            totalReplaces++;
            if (!usedCategories.contains(label)) {
                //Keep track of used categories.
                usedCategories.add(label);
            }
        }

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private void resetUsedWords() {
        usedWords = new ArrayList<String>();
        totalReplaces = 0;
    }

    private int totalWordsInMap() {
        /** This method returns the total number of words in all the ArrayLists in the HashMap.*/
        int totalWords = 0;
        for (String k : myMap.keySet()) {
            totalWords = totalWords + myMap.get(k).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered() {
        /** This method returns the total number of words in the ArrayLists of the categories that were used for a particular GladLib. */
        int total = 0;
        for (int y = 0; y < usedCategories.size(); y++) {
            total = total + myMap.get(usedCategories.get(y)).size();
        }
        return total;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("resources2/GladLibData/datalong/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words replaced: "+totalReplaces);
        System.out.println("Total number of words available: "+totalWordsInMap());
        System.out.println("Total number of words considered to be used: "+totalWordsConsidered());
        resetUsedWords();
    }
}

