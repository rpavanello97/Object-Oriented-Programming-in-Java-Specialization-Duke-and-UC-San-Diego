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

    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedKey1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String shiftedKey2 = alphabet.substring(key2)+ alphabet.substring(0,key2);

        StringBuilder encrypted = new StringBuilder(input);

        for(int i = 0; i < encrypted.length(); i++) {
            boolean isLowerCase = false;
            char currChar = encrypted.charAt(i);

            if (Character.isLowerCase(currChar)) {
                isLowerCase = true;
                currChar = Character.toUpperCase(currChar);
            }

            int index = alphabet.indexOf(currChar);

            if (index != -1) {
                if (i % 2 == 0) {
                    char newChar = shiftedKey1.charAt(index);
                    if (isLowerCase) newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedKey2.charAt(index);
                    if (isLowerCase) newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }


    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);

        System.out.println("Test 2");
        encrypted = encrypt("First Legion",23);
        System.out.println(encrypted);

        System.out.println("Test 3");
        encrypted = encrypt("First Legion",17);
        System.out.println(encrypted);
    }

    public void testEncryptTwoKeys() {
        String encrypted = encryptTwoKeys("First Legion",23,17);
        System.out.println(encrypted);
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testEncryptTwoKeys();
    }
}

