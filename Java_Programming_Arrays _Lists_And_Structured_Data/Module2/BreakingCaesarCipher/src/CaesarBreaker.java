import edu.duke.FileResource;

public class CaesarBreaker {
    public String decrypt(String encrypted) {
        /**
         * Get how much letters each letter has in the message.
         * Get which index of the letter that has more occurrences.
         * Get decryption key, considering 'e' is the most used letter from english alphabet.
         * Call encrypt method passing encrypted message and decrypt key.
         * */
        CaesarCipher cc = new CaesarCipher();
        int key = getKey(encrypted);
        return cc.encrypt(encrypted,26-key);
    }

    public String decryptTwoKeys(String encrypted) {
        /**
         * It has the same idea than decrypt method but using encrypted with 2 keys from encryptTwoKeys.
         */
        CaesarCipher cc = new CaesarCipher();

        //Get string starting 0.
        String strStarting0 = halfOfString(encrypted, 0);

        //Get string starting 1
        String strStarting1 = halfOfString(encrypted, 1);

        //Calculate the key used to encrypt each half String.
        int key1 = getKey(strStarting0);
        int key2 = getKey(strStarting1);
        System.out.println("key1 is"+key1+", key2 is "+key2);

        return cc.encryptTwoKeys(encrypted,26-key1,26-key2);
    }

    private int[] countLetters(String message) {
        //Count each letter from a message.
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        return counts;
    }

    private String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i = i +2) {
            char currChar = message.charAt(i);
            halfString.append(currChar);
        }
        return halfString.toString();
    }

    private int maxIndex(int[] values) {
        //Return index of the highest count in the array.
        int index = 0;
        int maxValue = 0;
        for (var i=0; i<values.length; i++) {
            if (values[i] > maxValue) {
                index = i;
                maxValue = values[i];
            }
        }
        return index;
    }

    private int getKey(String s) {
        /**
         * It is using statistical letter frequencies of English text.
         * So it will only work with texts that has more "e" in it.
         */
        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);

        int key = maxIndex - 4;
        if (maxIndex < 4)
            key = 26 - (4 - key);

        return key;
    }

    public void testDecrypt() {
        //Test halfString method.
//        String halfStr = halfOfString("Qbkm Zgis",0);
//        System.out.println(halfStr);
//        halfStr = halfOfString("Qbkm Zgis",1);
//        System.out.println(halfStr);

        /** Testing decrypt */
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        CaesarCipher cc = new CaesarCipher();
//        String encrypted = cc.encrypt(message,4);
//        System.out.println(encrypted);
//
//        String decrypted = decrypt(encrypted);
//        System.out.println(decrypted);

        /** Testing decryptTwoKeys */
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encryptTwoKeys(message,4, 5);
        System.out.println(encrypted);

        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        CaesarBreaker breaker = new CaesarBreaker();
        breaker.testDecrypt();
    }
}
