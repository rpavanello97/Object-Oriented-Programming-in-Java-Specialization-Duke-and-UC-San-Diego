package tests;

import classes.CaesarCracker;
import edu.duke.FileResource;

public class CaesarCrackerTest {

    private CaesarCracker cracker;
    private String str;
    private String decrypted;

    public CaesarCrackerTest() {
        cracker = new CaesarCracker('a'); //Most common char used as parameter is "e".
        str = new FileResource("resources/oslusiadas_key17.txt").asString();
    }

    public void testDecrypt() {
        decrypted = cracker.decrypt(str);
        if (!decrypted.equals(str)) {
            System.out.println("testDecrypt worked: "+decrypted);
        }
    }
}
