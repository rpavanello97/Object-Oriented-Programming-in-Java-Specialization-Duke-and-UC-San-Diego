import tests.CaesarCipherTest;
import tests.CaesarCrackerTest;
import tests.VigenereBreakerTest;
import tests.VigenereCipherTest;

public class Main {
    public static void main(String[] args) {

        System.out.println("Started main!!!");

        testAll(4);

        System.out.println("Finished main!!!");
    }

    public static void testAll(int action) {
        if (action == 1) {
            /** CaesarCipherTest */
            CaesarCipherTest ccTest = new CaesarCipherTest();
            ccTest.testEncrypt();
            ccTest.testDecrypt();

        } else if (action == 2) {
            /** CaesarCrackerTest */
            CaesarCrackerTest ccrTest = new CaesarCrackerTest();
            ccrTest.testDecrypt();

        } else if (action == 3) {
            /** VigenereCipherTest */
            VigenereCipherTest vigTest = new VigenereCipherTest();
            vigTest.testEncrypt();
            vigTest.testDecrypt();

        } else if (action == 4) {
            /** Viginere Test */
            VigenereBreakerTest vbTest = new VigenereBreakerTest();
//            vbTest.testSliceString();
//            vbTest.testTryKeyLength();
            vbTest.testBreakVigenere();
        }
    }
}