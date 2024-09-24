import classes.CountWords;
import classes.WordsWithArrays;
import classesTwo.GladLib;
import programming.exercise.CharactersInPlay;
import programming.exercise.WordFrequencies;
import programming.exerciseTwo.CodonCount;
import programming.exerciseTwo.WordsInFiles;

public class Main {
    public static void main(String[] args) {
        /** Tests from Classes */
//        WordFrequencies wordFreq = new WordFrequencies();
//        wordFreq.tester();

//        WordsWithArrays wordsWithArrays = new WordsWithArrays();
//        wordsWithArrays.tester();
//
//        CountWords cw = new CountWords();
//        cw.tester();
//        cw.randomTester();

        /** Tests from Programming Exercise */
//        WordFrequencies wordFreq = new WordFrequencies();
///       wordFreq.tester();
//
//        CharactersInPlay charsPlay = new CharactersInPlay();
//        charsPlay.tester();

        /** Telling a Random Story - Quiz */
//        WordFrequencies wordFreq = new WordFrequencies();
//        wordFreq.tester();

//        CharactersInPlay charsPlay = new CharactersInPlay();
//        charsPlay.tester();

//        usingGladLibs();

        /** Programming Exercise 2 - Improving GladLibs */
        CodonCount cc = new CodonCount();
        //cc.testCodonCount();
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }

    /** Using GladLibs topic */
    private static void usingGladLibs() {
        GladLib glalib = new GladLib();
        glalib.makeStory();
    }


}