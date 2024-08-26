import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i = i +2) {
            char currChar = message.charAt(i);
            halfString.append(currChar);
        }
        return halfString.toString();
    }

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

    private String breakCaesarCipher(String input){
        //Get string starting 0.
        String strStarting0 = halfOfString(input, 0);
        //Get string starting 1
        String strStarting1 = halfOfString(input, 1);
        //Calculate the key used to encrypt each half String.
        int key1 = getKey(strStarting0);
        int key2 = getKey(strStarting1);

        System.out.println("key1 is "+key1+", key2 is "+key2+" from breakCaesarCipher");
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26-key1,26-key2);
        return cc2.encrypt(input);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);

        String decryptedWithBreak = breakCaesarCipher(encrypted);
        System.out.println(decryptedWithBreak);
    }

    public void simpleTestsQuiz() {
//        String q2 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
//        CaesarCipherTwo cc2 = new CaesarCipherTwo(21,8);
//        String encrypted = cc2.encrypt(q2);
//        System.out.println(encrypted);

        /** Quiz - Question 8 */
        FileResource fr = new FileResource();
        System.out.println(breakCaesarCipher(fr.asString()));

    }

}
