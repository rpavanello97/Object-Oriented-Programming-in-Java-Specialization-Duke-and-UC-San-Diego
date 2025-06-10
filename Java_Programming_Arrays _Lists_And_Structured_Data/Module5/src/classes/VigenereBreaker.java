package classes;

import edu.duke.FileResource;

import java.io.FileReader;
import java.util.*;

public class VigenereBreaker {
    private int tries = 100;
    //private char language = 'e'; //To break all languages, it is necessary to calculate the most common char to each language.

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
        String[] langOptions = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        //String[] langOptions = {"Danish","Dutch","English"};
        String text = new FileResource().asString(); //It will open a window do select a file.
        HashMap<String, HashSet<String>> languages = new HashMap();

        for (int i = 0; i < langOptions.length; i++) {
            FileResource fileResourceDict = new FileResource("dictionaries/"+langOptions[i]);
            languages.put(langOptions[i], readDictionary(fileResourceDict));
            System.out.println(langOptions[i]+" dictionary loaded.");
        }

        breakForAllLangs(text, languages);
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

    public Map.Entry<Integer,String> breakForLanguage(String encrypted, HashSet<String> dictionary) {
        HashMap<Integer,String> decrypted = new HashMap();
        String decryption = "";
        int realWordsCount = 0;
        int keyLength = 1;

        for (int i = 1; i < tries; i++) {
            int[] keys = tryKeyLength(encrypted,i,mostCommonCharIn(dictionary));
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

        decrypted.put(realWordsCount, decryption);
        return new AbstractMap.SimpleEntry<>(realWordsCount,decryption);
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {

        int higherCount = 0;
        String decrypted = "";
        String lang = "";
        for (String language : languages.keySet()) {
            System.out.println("\nStarting decrypting for language: "+language+"...");
            Map.Entry<Integer,String> result  = breakForLanguage(encrypted, languages.get(language));
            if (result.getKey() > higherCount) {
                higherCount = result.getKey();
                decrypted = result.getValue();
                lang = language;
                System.out.println("Decrypted language: "+language);
            }
            System.out.println("Ending decrypting for language: "+language+"...");
        }
        System.out.println("\nDecrypted message: "+decrypted);
        System.out.println("Decrypted message length: "+decrypted.length());
        System.out.println("Language with most valid words: "+lang);

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


/** *
 * Check how to return a response from breakForLanguage to breakForAllLangs.
 * Return the number of valid words?
 * Use countWords in breakForAllLangs instead of breakForLanguage?
 *
 */