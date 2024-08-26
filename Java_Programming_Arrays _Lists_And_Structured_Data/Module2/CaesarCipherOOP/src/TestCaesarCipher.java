import edu.duke.FileResource;

public class TestCaesarCipher {
    private int[] countLetters(String message) {
        //Count each letter from a message.
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(ch);
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
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

    private String breakCaesarCipher(String input) {
        int[] frequencies = countLetters(input);
        int maxIndex = maxIndex(frequencies);

        int key = maxIndex - 4;
        if (maxIndex < 4)
            key = 26 - (4 - key);

        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }

    public void simpleTests() {
        /** Assignment 1: One Key */
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);

        String decryptedFromBreakingCC = breakCaesarCipher(encrypted);
        System.out.println(decryptedFromBreakingCC);
    }

    public void simpleTestsQuiz() {
        /** Quiz - Questions One  */
        String q1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc1 = new CaesarCipher(15);
        String encrypted1 = cc1.encrypt(q1);
        System.out.println(encrypted1);
    }
}
