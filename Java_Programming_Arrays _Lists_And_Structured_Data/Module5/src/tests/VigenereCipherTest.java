package tests;

import classes.VigenereCipher;
import edu.duke.FileResource;

public class VigenereCipherTest {
    private int[] key;
    private VigenereCipher vigenc;
    private String message;
    private String encrypted;
    private String decrypted;

    public VigenereCipherTest() {
        key = new int[] {17, 14, 12, 4}; //Rome;
        vigenc = new VigenereCipher(key);
        message = new FileResource("resources/titus-small.txt").asString();
    }

    public void testEncrypt() {
        encrypted = vigenc.encrypt(message);
        if (encrypted.contains("Tcmp-pxety mj nikhqv htee mrfhtii tyv")) {
            System.out.println("Encrypted: " + encrypted);
        }
    }

    public void testDecrypt() {
        decrypted = vigenc.decrypt(encrypted);
        if (decrypted.equals(message)) {
            System.out.println("Decrypted: " + decrypted);
        }
    }
}
