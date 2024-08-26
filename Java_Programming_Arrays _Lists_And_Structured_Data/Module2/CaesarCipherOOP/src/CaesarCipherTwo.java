public class CaesarCipherTwo {
    private String alphabet, shiftedKey1, shiftedKey2;
    private int key1, key2;

    public CaesarCipherTwo(int key1, int key2) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedKey1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        this.shiftedKey2 = alphabet.substring(key2)+ alphabet.substring(0,key2);

        this.key1 = key1;
        this.key2 = key2;
    }

    public String encrypt(String input) {
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

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - this.key1, 26 - this.key2);
        return cc.encrypt(input);
    }
}
