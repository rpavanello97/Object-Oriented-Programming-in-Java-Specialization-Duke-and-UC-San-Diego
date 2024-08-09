import edu.duke.*;

public class Part1 {
    private String findSimpleGene(String dna) {
       String gene = new String();

       int startIndex = dna.indexOf("ATG");
       int finalIndex = dna.indexOf("TAA",startIndex+3);

       if (startIndex == -1 || finalIndex ==-1) {
           return "";
       }

       gene = dna.substring(startIndex, finalIndex+3);

       if (gene.length() % 3 == 0) {
           return gene;
       } else {
           return "";
       }
    }

    public void testSimpleGene() {
        String dna1 = "GAGTTAGTGAATGTGATG";
        System.out.println("Gene is from dna1:"+findSimpleGene(dna1));

        String dna2 = "ATGAGTAGTGGTAGTAGATAATTGATGA"; //valid
        System.out.println("Gene is from dna2:"+findSimpleGene(dna2));

        String dna3 = "ATGAGTAGTA";
        System.out.println("Gene is from dna3:"+findSimpleGene(dna3));

        String dna4 = "AGTAGTAGGTAA";
        System.out.println("Gene is from dna4:"+findSimpleGene(dna4));

        String dna5 = "ATGAGTAGTAGGATAA";
        System.out.println("Gene is from dna5:"+findSimpleGene(dna5));
    }
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
