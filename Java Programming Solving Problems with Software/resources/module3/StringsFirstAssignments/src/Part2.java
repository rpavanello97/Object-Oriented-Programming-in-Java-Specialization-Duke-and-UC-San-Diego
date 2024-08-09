import edu.duke.*;

public class Part2 {
    private String findSimpleGene(String dna, String startCodon, String stopCondon) {
        String gene = new String();
        int startIndex = 0;
        int finalIndex = 0;

        boolean isUpper = dna.startsWith(dna.toUpperCase());

        if (isUpper) {
            startIndex = dna.indexOf(startCodon.toUpperCase());
        } else {
            startIndex = dna.indexOf(startCodon.toLowerCase());
        }

        if (startIndex == -1) {
            return "";
        }

        if (isUpper) {
            finalIndex = dna.indexOf(stopCondon.toUpperCase(),startIndex+3);
        } else {
            finalIndex = dna.indexOf(stopCondon,startIndex+3);
        }

        gene = dna.substring(startIndex, finalIndex+3);

        if (gene.length() % 3 == 0) {
            return gene;
        } else {
            return "";
        }
    }

    public void testSimpleGene() {
        String dna1 = "ATGGGTTAAGTC";
        System.out.println("Gene is from dna1:"+findSimpleGene(dna1,"ATG","TAA"));

        String dna2 = "gatgctataata";
        System.out.println("Gene is from dna2:"+findSimpleGene(dna2,"atg","taa"));


    }
    public static void main (String[] args) {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}
