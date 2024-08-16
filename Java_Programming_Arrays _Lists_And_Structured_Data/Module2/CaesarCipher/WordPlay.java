public class WordPlay {
    public boolean isVowel(char ch) {
        String vowelsLower = "aeiou";
        String vowelsUpper = "AEIOU";
        if (vowelsUpper.indexOf(ch) != -1 || vowelsLower.indexOf(ch) != -1) {
            return true;
        }
        return false;
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0; i < newPhrase.length(); i++) {
            if (isVowel(newPhrase.charAt(i))) {
                newPhrase.setCharAt(i, ch);
            }
        }
        return newPhrase.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0; i < newPhrase.length(); i++) {
            char currChar = newPhrase.charAt(i);
            if (currChar == ch || Character.toLowerCase(currChar) == ch) {
                int index = newPhrase.indexOf(Character.toString(currChar));
                if ((index + 1) % 2 == 0) {
                    newPhrase.setCharAt(i, '+');
                } else {
                    newPhrase.setCharAt(i, '*');
                }
            }
        }
        return newPhrase.toString();
    }

    private void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }

    private void testReplaceVowels() {
        String replaced = replaceVowels("Hello World",'*');
        System.out.println(replaced);
    }
    private void testEmphasize() {
        String emphasized = emphasize("dna ctgaaactga",'a');
        System.out.println(emphasized);
        emphasized = emphasize("Mary Bella Abracadabra",'a');
        System.out.println(emphasized);
    }

    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
        wp.testEmphasize();
    }
}
