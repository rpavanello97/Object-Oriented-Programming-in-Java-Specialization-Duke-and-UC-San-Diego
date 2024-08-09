public class AllCodonsAnd {

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

    public static void testAllCodonsAnd() {
        String gene = findGene("xxxyyyzzzATGxxxyyyzzzTAAxx",0);
        if(!gene.equals("ATGxxxyyyzzzTAA")) System.out.println("Error on test 1.");

        gene = findGene("xxxyyyzzzATGxxyyyzzzTAAxx",0);
        if(!gene.isEmpty()) System.out.println("Error on test 2.");

        gene = findGene("xxxyyyzzzATGxxxyyyzzTAAxxxxTGAxx",0);
        if(!gene.equals("ATGxxxyyyzzTAAxxxxTGA")) System.out.println("Error on test 3: "+gene);

        gene = findGene("xxxyyyzzzATGxxxyyyzzTAAxxxTGAxxyyzzzTAGxx",0);
        if(!gene.equals("ATGxxxyyyzzTAAxxxTGAxxyyzzzTAG")) System.out.println("Error on test 4: "+gene);

        System.out.println("Tests completed");
    }

    public void testOn(String dna) {
        System.out.println("Testing on...");
        printAllGenes(dna);
    }

    public void testAllGenes() {
        testOn("ATGATCTAATTATGCTGCAACGGTGAAGA");
        testOn("ATGATCTAATTATGCTGCAACGGTGAAGAATGATCTAATTATGCTGCAACGGTGAAGAATGATCTAATTATGCTGCAACGGTGAAGA");
    }

    public static void main(String[] args) {
        AllCodonsAnd ac = new AllCodonsAnd();
        //ac.testAllCodonsAnd();
        ac.testAllGenes();
    }
}
