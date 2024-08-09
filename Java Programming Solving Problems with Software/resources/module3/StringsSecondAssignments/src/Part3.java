public class Part3 {
    public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return currIndex;
    }

    public static String findGene(String dna, int index) {

        int startIndex = dna.indexOf("ATG", index);
        if (startIndex == -1)
            return "";

        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int minIndex;

        if(indexTAA == -1 ||(indexTGA != -1 && indexTGA < indexTAA)) {
            minIndex = indexTGA;
        } else {
            minIndex = indexTAA;
        }

        if(minIndex == -1 ||(indexTAG != -1 && indexTAG < minIndex)) {
            minIndex = indexTAG;
        }

        if (minIndex == -1)
            return "";

        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        String currentGene;
        int startIndex = 0;

        while (true) {
            currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                break;
            }

            System.out.println(currentGene);

            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public static int countGenes(String dna) {
        String currentGene;
        int startIndex = 0;
        int count = 0;

        while (true) {
            currentGene = findGene(dna, startIndex);

            if(currentGene.isEmpty()) break;

            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }

    public static void testCountGenes() {
        /** Test 1 */
        int test = 1;
        String dna = "ATGTAAGATGCCCTAGT";

        System.out.println("Starting test "+test);
        int amount = countGenes(dna);
        if (amount != 2) {
            System.out.println("Error on executing test "+test);
        } else {
            System.out.println("Test "+test+" passed");
        }

        /** Test 2 */
        test++;
        dna = "ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGT";

        System.out.println("Starting test "+test);
        amount = countGenes(dna);
        if (amount != 4) {
            System.out.println("Error on executing test "+test);
        } else {
            System.out.println("Test "+test+" passed");
        }

        /** Test 3 */
        test++;
        dna = "zzATGyyycccTAAaaATGyyaaTGAwwwzzTGAaawwATGuuuaaaiiioooTAGddd";

        System.out.println("Starting test "+test);
        amount = countGenes(dna);
        if (amount != 3) {
            System.out.println("Error on executing test "+test+" Result: "+amount);
        } else {
            System.out.println("Test "+test+" passed");
        }
    }


    public static void main(String[] args) {
        Part3 p3 = new Part3();
        p3.testCountGenes();
    }
}
