public class Part2in {
    public static int howMany (String stringa, String stringb) {
        int count = 0;
        int currIndex = 0;

        while (true) {
            int index = stringb.indexOf(stringa,currIndex);

            if(index == -1) break;

            count++;
            currIndex = index + stringa.length();
        }
        return count;
    }

    public static void testHowMany() {
        String stringA = "AA", stringB = "ATAAAAA";
        int test = 1;
        System.out.println("Testing "+test);
        int count = howMany(stringA, stringB);
        if (count != 2) System.out.println("Error on executing test "+test+", returned: "+count);

        stringA = "GAA";
        stringB = "ATGAACGAATTGAATC";
        test++;
        System.out.println("Testing "+test);
        count = howMany(stringA, stringB);
        if (count != 3) System.out.println("Error on executing test "+test+", returned: "+count);
    }

    public static void main(String[] args) {
        Part2in p2 = new Part2in();
        p2.testHowMany();
    }
}
