import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;

    private String alphabetLower;
    private String shiftedAlphabetLower;

    private int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";

        shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        shiftedAlphabetLower = alphabetLower.substring(key)+ alphabetLower.substring(0,key);

        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            int idx = alphabet.indexOf(c);
            int idxLower = alphabetLower.indexOf(c);
            if(idx != -1){
                char nc = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, nc);
            } else if(idxLower != -1) {
                char ncl = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, ncl);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}

