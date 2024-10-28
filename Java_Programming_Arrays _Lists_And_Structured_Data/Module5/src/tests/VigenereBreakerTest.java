package tests;

import classes.VigenereBreaker;
import edu.duke.FileResource;

public class VigenereBreakerTest {
    private VigenereBreaker vb;

    public VigenereBreakerTest() {
        vb =  new VigenereBreaker();
    }

    public void testSliceString() {
        String sliced = vb.sliceString("abcdefghijklm", 0, 3);
        if (sliced.equals("adgjm")) {
            System.out.println("The slice is adgjm with input (\"abcdefghijklm\", 0, 3)");
        }

        sliced = vb.sliceString("abcdefghijklm", 1, 3);
        if (sliced.equals("behk")) {
            System.out.println("The slice is behk with input (\"abcdefghijklm\", 1, 3)");
        }

        sliced = vb.sliceString("abcdefghijklm", 2, 3);
        if (sliced.equals("cfil")) {
            System.out.println("The slice is cfil with input (\"abcdefghijklm\", 2, 3)");
        }

        sliced = vb.sliceString("abcdefghijklm", 0, 4);
        if (sliced.equals("aeim")) {
            System.out.println("The slice is aeim with input (\"abcdefghijklm\", 0, 4)");
        }

        sliced = vb.sliceString("abcdefghijklm", 4, 5);
        if (sliced.equals("ej")) {
            System.out.println("The slice is ej with input (\"abcdefghijklm\", 4, 5)");
        }
    }

    public void testTryKeyLength() {
        FileResource fl = new FileResource("resources/athens_keyflute.txt");
        String message = fl.asString();

        //For this moment, we are testing with a know number of keys and know common word.
        int[] keys = vb.tryKeyLength(message, 5, 'e');
        System.out.println(keys);
    }

    public void testBreakVigenere() {
        vb.breakVigenere();
    }
}
