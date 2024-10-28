package classes;

import edu.duke.FileResource;

import java.io.FileReader;

public class VigenereBreaker {


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
        String text = new FileResource().asString();
        int[] keys;
        VigenereCipher vigenC;
        String decrypted;

        keys = tryKeyLength(text,5,'e');
        vigenC = new VigenereCipher(keys);
        decrypted = vigenC.decrypt(text);

        System.out.println(decrypted);
    }

}
