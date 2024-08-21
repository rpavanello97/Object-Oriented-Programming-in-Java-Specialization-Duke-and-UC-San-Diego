import edu.duke.FileResource;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            //Check non-letter as the first or last char.
            int length;
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length()-1);

            if (Character.isLetter(firstChar) && Character.isLetter(lastChar)) {
                length = word.length();
            } else if (Character.isLetter(firstChar) || Character.isLetter(lastChar)) {
                length = word.length() - 1;
            } else {
                length = word.length() - 2;
            }

            counts[length]++;

        }

        //Print Words
        for (var i=1; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(counts[i]+" words of length "+i);
            }
        }

        return counts;
    }

    public int indexOfMax(int[] values) {
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

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];

        int[] values = countWordLengths(fr,counts);
        System.out.println("Most common word length in the file "+indexOfMax(values));
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }

}
