package programming.exercise;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charFreqs;

    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        charFreqs = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = charNames.indexOf(person);
        if (index == -1) {
            charNames.add(person);
            charFreqs.add(1);
        } else {
            int currVal = charFreqs.get(index);
            charFreqs.set(index, currVal+1);
        }
    }

    public void findAllCharacters() {
        FileResource fileResource = new FileResource();

        for (String phrase : fileResource.lines()) {
            //Check period on the line
            int idx = phrase.indexOf(".");

            //Extract possible speaker
            if (idx != -1) {
                String possibleSpeaker = phrase.substring(0, idx);
                update(possibleSpeaker);
                idx = -1;
                possibleSpeaker = "";
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < charNames.size(); i++) {
            if (num1 <= charFreqs.get(i) && charFreqs.get(i) <= num2) {
                System.out.println(charNames.get(i)+" "+charFreqs.get(i));
            }
        }
        //TODO It is necessary to test it.
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < charNames.size(); i++) {
            if (charFreqs.get(i) > 1) {
                System.out.println(charNames.get(i)+" "+charFreqs.get(i));
            }
        }
    }
}
