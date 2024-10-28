package tests;

import classes.CaesarCipher;
import edu.duke.FileResource;

public class CaesarCipherTest {
    private int key;
    private String str;
    private FileResource fr;
    private String encrypted;
    private String decrypted;
    private CaesarCipher cc;

    public CaesarCipherTest() {
        key = 10;
        cc = new CaesarCipher(key);
        fr = new FileResource("resources/titus-small.txt");
        str = "Rafael Bertolim Pavanello";
    }

    public void testEncrypt() {
        encrypted = cc.encrypt(str);
        /** Check Result */
        if (!encrypted.equals(str)){
            System.out.println("testEncrypt passed: "+encrypted);
        }
    }

    public void testDecrypt() {
        decrypted = cc.decrypt(encrypted);
        /** Check result */
        if (decrypted.equals(str)){
            System.out.println("testDecrypt passed: "+decrypted);
        }
    }



}
