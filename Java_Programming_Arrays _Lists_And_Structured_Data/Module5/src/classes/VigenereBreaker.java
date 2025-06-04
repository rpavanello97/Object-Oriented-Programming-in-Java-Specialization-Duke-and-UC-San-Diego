package classes;

import edu.duke.FileResource;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {
    private int tries = 100;
    private char language = 'e';

    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliced = "";
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            sliced = sliced + message.substring(i,i+1);
        }
        return sliced;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesarCracker = new CaesarCracker(mostCommon);

        for (int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            key[i] = caesarCracker.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fileResourceDict = new FileResource("dictionaries/English");
        String text = new FileResource().asString();
        String decrypted;

        HashSet<String> wordsFromDict = readDictionary(fileResourceDict);
        decrypted = breakForLanguage(text, wordsFromDict);

        System.out.println(decrypted);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<String>();
        for (String line : fr.lines()) {
            words.add(line.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> word) {
        String[] messageWords = message.split("\\W+");
        int count = 0;
        for (int i = 0; i < messageWords.length; i++) {
            if (word.contains(messageWords[i].toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decryption = "";
        int realWordsCount = 0;
        int keyLength = 1;

        for (int i = 1; i < tries; i++) {
            int[] keys = tryKeyLength(encrypted,i,language);
            VigenereCipher vigenC = new VigenereCipher(keys);
            String message = vigenC.decrypt(encrypted);
            int count = countWords(message, dictionary);

            if(count > realWordsCount) {
                realWordsCount = count;
                decryption = message;
                keyLength = i;
            }
        }
        System.out.println("keyLength used: "+keyLength);
        System.out.println("real valid words: "+realWordsCount);
        return decryption;
    }

    public void breakForMultiLanguage(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String language : languages.keySet()) {
            //TODO Kepping creating this method.

        }
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        Character mostCommon = null;
        Integer mostCommonCount = 0;
        HashMap<Character, Integer> letters = new HashMap();

        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (letters.containsKey(ch)) {
                    int count = letters.get(ch);
                    letters.put(ch, count + 1);
                } else {
                    letters.put(ch, 1);
                }
            }
        }

        for (Character ch : letters.keySet()) {
            if(letters.get(ch) > mostCommonCount) {
                mostCommon = ch;
                mostCommonCount = letters.get(ch);
            }
        }

        return mostCommon;
    }


}
