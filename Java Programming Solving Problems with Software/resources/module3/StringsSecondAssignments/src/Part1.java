public class Part1 {

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

    public static void testFindStopCodon() {
        //Differents stopCodons, some with genes, some with not.
        String dna = "ATGyyyzzzTAA";
        System.out.println("Testing DNA: "+dna);
        int endIndex = findStopCodon(dna, 0, "TAA");
        if (endIndex != 9) System.out.println("Error on executing test 1");

        dna = "ATGyyyzzzTGA";
        System.out.println("Testing DNA: "+dna);
        endIndex = findStopCodon(dna, 0, "TGA");
        if (endIndex != 9) System.out.println("Error on executing test 2");

        dna = "ATGyyyzzz";
        System.out.println("Testing DNA: "+dna);
        endIndex = findStopCodon(dna, 0, "TGA");
        if (endIndex != -1) System.out.println("Error on executing test 3");
    }

    public static void testFindGene() {
        String dna  = "ATGyyyzzzTGAzzzyy"; //With ATG
        System.out.println("Testing DNA: "+dna);
        String gene = findGene(dna,0);
        if(!gene.equals("ATGyyyzzzTGA")) System.out.println("Error on executing test 1");
        System.out.println("");

        dna  = "yyyzzzTGA"; //With ATG
        System.out.println("Testing DNA: "+dna);
        gene = findGene(dna,0);
        if(!gene.equals("")) System.out.println("Error on executing test 2");
        System.out.println("");

        dna  = "ATGyyzzzTGAzzzzyyyTAAww"; //Multiple stop codon
        System.out.println("Testing DNA: "+dna);
        gene = findGene(dna,0);
        if(!gene.equals("ATGyyzzzTGAzzzzyyyTAA")) System.out.println("Error on executing test 3: "+gene);
        System.out.println("");

        dna  = "ATGyyzzzTGAzzzyyTAA"; //No valid stop codons
        System.out.println("Testing DNA: "+dna);
        gene = findGene(dna,0);
        if(!gene.equals("")) System.out.println("Error on executing test 4: "+gene);
        System.out.println("");
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testFindGene();
    }
}
