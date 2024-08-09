public class Part3 {

    private boolean twoOccurrences(String stringA, String stringB) {
        boolean hasTwice = false;

        int indexOfOccurrence = stringB.indexOf(stringA);
        int indexOfSecondOccurrence = 0;

        if (indexOfOccurrence > -1) {
            indexOfSecondOccurrence = stringB.indexOf(stringA, indexOfOccurrence+stringA.length());
        }
        if (indexOfSecondOccurrence > -1)
            hasTwice = true;

        return hasTwice;
    }

    private String lastPart(String stringA, String stringB) {
        int startIndex = stringB.indexOf(stringA);
        if (startIndex > -1) {
            return stringB.substring(startIndex+stringA.length());
        }
        return stringB;
    }

    private void testing() {
        System.out.println("Has (\"by\",\"A story by Abby Long\") two occurrences? "+twoOccurrences("by","A story by Abby Long"));
        System.out.println("Has (\"a\",\"banana\") two occurrences? "+twoOccurrences("a","banana"));
        System.out.println("Has (\"atg\",\"ctgtatgta\") two occurrences? "+twoOccurrences("atg","ctgtatgta"));

        System.out.println();
        var string = "";

        string = lastPart("an","banana");
        System.out.println("The part of the string after an in banana is: "+string);

        string = lastPart("zoo","forest");
        System.out.println("The part of the string after zoo in forest is: "+string);

        string = lastPart("af","rafael");
        System.out.println("The part of the string after af in rafael is: "+string);

        string = lastPart("pa","paralelepipedo");
        System.out.println("The part of the string after pa in paralelepipedo is: "+string);
    }

    public static void main (String[] args) {
        Part3 p3 = new Part3();
        p3.testing();
    }
}