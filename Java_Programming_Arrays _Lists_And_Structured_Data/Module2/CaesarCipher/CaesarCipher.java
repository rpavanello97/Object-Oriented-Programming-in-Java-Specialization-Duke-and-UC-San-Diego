import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";

        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key)+ alphabetLower.substring(0,key);

        for(int i = 0; i < encrypted.length(); i++) {

            char currChar = encrypted.charAt(i);

            int idx = alphabet.indexOf(currChar);
            int idxLower = alphabetLower.indexOf(currChar);

            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            } else if(idxLower != -1) {
                char newCharLower = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newCharLower);
            }

        }
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testCaesar();
    }
}

