import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public void countryInfo(CSVParser parser, String countryToFind) {
        boolean foundCountry = false;
        for (CSVRecord record : parser) {
            String countryFromCSV = record.get("Country");

            if(countryFromCSV.equals(countryToFind)) {
                foundCountry = true;
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");

                //Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
                System.out.println(countryFromCSV+" : "+exports+" : "+value);
                break;
            }
        }
        if (!foundCountry) {
            System.out.println("NOT FOUND");
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        //Country that exports two items from input.
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        //This method returns the number of countries that export exportItem.
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String amountFromCSV = record.get("Value (dollars)");

            if (amountFromCSV.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country+" : "+amountFromCSV);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
//        countryInfo(parser, "Nauru");

//        System.out.println("\n>>>>>>>>>> Test 2");
//        parser = fr.getCSVParser();
//        listExportersTwoProducts(parser, "cotton", "flowers");

//        System.out.println("\n>>>>>>>>>> Test 3");
//        parser = fr.getCSVParser();
//        int total = numberOfExporters(parser, "cocoa");
//        System.out.println("Number of countries exports cocoa: "+total);

        System.out.println("\n>>>>>>>>>> Test 4");
        parser = fr.getCSVParser();
        bigExporters(parser, " $999,999,999,999");
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.tester();
    }
}
