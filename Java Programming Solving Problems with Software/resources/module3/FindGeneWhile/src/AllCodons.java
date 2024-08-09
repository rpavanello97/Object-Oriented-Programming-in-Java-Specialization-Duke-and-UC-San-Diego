public class AllCodons {

    public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dnaStr.length();
    }

    public static String findGene(String dna) {

        int startIndex = dna.indexOf("ATG");

        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(indexTGA, Math.min(indexTAG,indexTAA));
        if(minIndex == dna.length()) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public static void testAllCodons() {
        String gene = findGene("xxxyyyzzzATGxxxyyyzzzTAAxx");
        if(!gene.equals("ATGxxxyyyzzzTAA")) System.out.println("Error on test 1.");

        gene = findGene("xxxyyyzzzATGxxyyyzzzTAAxx");
        if(!gene.isEmpty()) System.out.println("Error on test 2.");

        gene = findGene("xxxyyyzzzATGxxxyyyzzTAAxxxxTGAxx");
        if(!gene.equals("ATGxxxyyyzzTAAxxxxTGA")) System.out.println("Error on test 3: "+gene);

        gene = findGene("xxxyyyzzzATGxxxyyyzzTAAxxxTGAxxyyzzzTAGxx");
        if(!gene.equals("ATGxxxyyyzzTAAxxxTGAxxyyzzzTAG")) System.out.println("Error on test 4: "+gene);

        System.out.println("Tests completed");
    }

    public static void main(String[] args) {
        AllCodons ac = new AllCodons();
        ac.testAllCodons();
    }
}
