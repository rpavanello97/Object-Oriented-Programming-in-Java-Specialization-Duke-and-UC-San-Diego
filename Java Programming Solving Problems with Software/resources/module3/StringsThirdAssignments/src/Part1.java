import edu.duke.FileResource;
import edu.duke.StorageResource;

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

    public static void printAllGenes(String dna) {
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

    public static StorageResource getAllGenes(String dna) {
        String currentGene;
        int startIndex = 0;
        StorageResource sr = new StorageResource();

        while (true) {
            currentGene = findGene(dna, startIndex);

            if (currentGene.isEmpty()) {
                break;
            }
            sr.add(currentGene);

            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

        return sr;
    }

    public static float cgRatio(String dna) {
        /** returns the ratio of C’s and G’s in dna as a fraction */
        float ratio;
        int currIndex = 0;
        int totalC = 0;
        int totalG = 0;

        //Get all C
        while(true) {
            currIndex = dna.indexOf("C",currIndex == 0 ? 0 : currIndex+1);
            if (currIndex == -1)
                break;
            totalC++;
        }

        currIndex = 0;
        //Get all G
        while(true) {
            currIndex = dna.indexOf("G",currIndex == 0 ? 0 : currIndex+1);
            if (currIndex == -1)
                break;
            totalG++;
        }

        ratio = (float) (totalC + totalG) / dna.length();
        return ratio;
    }

    public static int countCTG(String dna) {
        int currIndex = 0;
        int count = 0;

        while(true) {
            currIndex = dna.indexOf("CTG",currIndex == 0 ? 0 : currIndex+3);
            if (currIndex == -1)
                break;
            count++;
        }

        return count;
    }

    public static void processGenes(StorageResource sr) {

        int longestGeneLength = 0;
        int longerThan9Count = 0;
        int higherRatioCG = 0;

        for (String s : sr.data()) {
            //print all the Strings in sr that are longer than 9 characters and count them.
            if (s.length() > 9) {
                System.out.println("Longer than 9 chars: "+s);
                longerThan9Count++;
            }

            //print the Strings in sr whose C-G-ratio is higher than 0.35 . Use cgRatio().
            if (cgRatio(s) > 0.35) {
                System.out.println("Higher than 0.35: "+s);
                higherRatioCG++;
            }

            if (s.length() > longestGeneLength)
                longestGeneLength = s.length();

        }

        //print the number of Strings in sr that are longer than 9 characters. Count these strings.
        System.out.println("\nTotal strings longer than 9 characters: "+longerThan9Count);
        //print the number of strings in sr whose C-G-ratio is higher than 0.35. Count these strings.
        System.out.println("\nTotal strings C-G-ratio is higher than 0.35: "+higherRatioCG);
        //print the length of the longest gene in sr.
        System.out.println("\nLength of the longest gene: "+longestGeneLength);
    }

    public static void processGenes2(StorageResource sr) {

        int longerThan60 = 0;
        int higherRatioCG = 0;
        StorageResource allGenesStorage = null;

        //Get all genes
        for (String s : sr.data()) {
            allGenesStorage = getAllGenes(s);
        }

        assert allGenesStorage != null;
        for (String gene : allGenesStorage.data()) {
            if(gene.length() > 60) {
                System.out.println("Longer than 60 chars: "+gene);
                longerThan60++;
            }

            //print the Strings in sr whose C-G-ratio is higher than 0.35 . Use cgRatio().
            if (cgRatio(gene) > 0.35) {
                System.out.println("Higher than 0.35: "+gene);
                higherRatioCG++;
            }
        }

        //print the number of Strings in sr that are longer than 9 characters. Count these strings.
        System.out.println("\nTotal strings longer than 60 characters: "+longerThan60);
        //print the number of strings in sr whose C-G-ratio is higher than 0.35. Count these strings.
        System.out.println("\nTotal strings C-G-ratio is higher than 0.35: "+higherRatioCG);
    }

    public static void processStringsInJavaTest(String dna) {
        StorageResource allGenes = getAllGenes(dna);
        int countGenes = 0;
        int longerThan60 = 0;
        int longerRatio = 0;
        int totalCTG = countCTG(dna);
        int lengthLongest = 0;

        for (String g : allGenes.data()) {
            countGenes++;

            if (g.length() > 60) {
                longerThan60++;
            }

            if (cgRatio(g) > 0.35) {
                longerRatio++;
            }

            if (g.length() > lengthLongest) {
                lengthLongest = g.length();
            }
        }

        System.out.println("File has "+countGenes+" genes.");
        System.out.println("File has "+longerThan60+" genes longer than 60 chars.");
        System.out.println("File has "+longerRatio+" with genes that ratio is higher than 0.35.");
        System.out.println("File has "+totalCTG+" appearences of CTG");
        System.out.println("Length of the longest gene is "+lengthLongest);
    }

    public void testFindStopCodon() {
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

    public void testFindGene() {
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

    public void testGetAllGenes() {
        String dna  = "ATGyyyzzzTGAzzzyy"; //With ATG
        System.out.println("Test 1 ------->  Testing DNA: "+dna);
        StorageResource genes = getAllGenes(dna);
        if(genes.size() != 1) {
            System.out.println("Error on executing test 1");
        } else {
            System.out.println("Check all genes from DNA below ...");
            for (String g : genes.data()) {
                System.out.println(g);
            }
        }

        dna  = "ATGyyyzzzTGAzzzyyATGyyyzzzTGAzzzyyATGyyyzzzTGAzzzyy";
        System.out.println("/nTest 1 ------->  Testing DNA: "+dna);
        genes = getAllGenes(dna);
        if(genes.size() != 3) {
            System.out.println("Error on executing test 1");
        } else {
            System.out.println("Check all genes from DNA below ...");
            for (String g : genes.data()) {
                System.out.println(g);
            }
        }
    }

    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        sr.add("ATGyyyzzzaaaTGA"); //Longer than 9
        sr.add("ATGyyyTGA"); //No longer than 9
        sr.add("ATGCGCGCGCGCTGA"); //Radio higher than 0.35
        sr.add("ATGAAATGA"); //Radio lower than 0.35
        processGenes(sr);
    }

    public void testProcessGenes2() {
        FileResource fr = new FileResource("brca1line.fa");
        StorageResource sr = new StorageResource();
        sr.add(fr.asString().toUpperCase());
        processGenes2(sr);
    }

    public void testStringsInJavaTest() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        processStringsInJavaTest(fr.asString());
    }
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testStringsInJavaTest();
    }
}
