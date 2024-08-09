import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord hottestTemperature = null;
        for (CSVRecord record : parser) {
            hottestTemperature = getLargestOfTwoRecords(hottestTemperature, record);
        }
        return hottestTemperature;
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord hottestTemperature = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            hottestTemperature = getLargestOfTwoRecords(hottestTemperature, currentRow);
        }

        return hottestTemperature;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;

        for (CSVRecord record : parser) {
            coldestRecord = getColdestOfTwoRecords(coldestRecord, record);
        }

        return coldestRecord;
    }

    public File fileWithColdestTemperature() {
        //Print the name of the file from selected files that has the coldest temperature
        CSVRecord lowestTemperature = null;
        DirectoryResource dr = new DirectoryResource();
        File fileLowest = null;

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord lowestFromCurrentFile = coldestHourInFile(fr.getCSVParser());
            lowestTemperature = getColdestOfTwoRecords(lowestTemperature, lowestFromCurrentFile);

            if (lowestFromCurrentFile == lowestTemperature) {
                fileLowest = file;
            }
        }
        return fileLowest;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;
        for (CSVRecord record : parser) {
            lowestHumidityRecord = getLowestHumidityOfTwoRecords(lowestHumidityRecord, record);
        }
        return lowestHumidityRecord;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidityRecord = getLowestHumidityOfTwoRecords(lowestHumidityRecord, currentRow);
        }

        return lowestHumidityRecord;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int count = 0;
        double totalTemperature = 0.0;

        for (CSVRecord record : parser) {
            if (!record.get("Humidity").contains("N/A")) {
                double humidity = Double.parseDouble(record.get("Humidity"));
                if (humidity >= value) {
                    count++;
                    totalTemperature = totalTemperature + Double.parseDouble(record.get("TemperatureF"));
                }
            }
        }

        if (count > 0) {
            return totalTemperature / count;
        } else {
            return 0;
        }
    }

    public double averageTemperatureInFile(CSVParser parser) {
        int count = 0;
        double totalTemperature = 0.0;

        for (CSVRecord record : parser) {
            count++;
            totalTemperature = totalTemperature + Double.parseDouble(record.get("TemperatureF"));
        }

        return totalTemperature / count;
    }

    /** Aux methods */
    private CSVRecord getLargestOfTwoRecords(CSVRecord highestRecord, CSVRecord currentRecord ) {
        if (highestRecord == null) {
            return currentRecord;
        } else {
            double currentTemperature = Double.parseDouble(currentRecord.get("TemperatureF"));
            double hottestTemperature = Double.parseDouble(highestRecord.get("TemperatureF"));
            if (currentTemperature > hottestTemperature) {
                return currentRecord;
            } else {
                return highestRecord;
            }
        }
    }

    private CSVRecord getColdestOfTwoRecords(CSVRecord lowestRecord, CSVRecord currentRecord) {
        if (lowestRecord == null) {
            return currentRecord;
        } else {
            double currentTemperature = Double.parseDouble(currentRecord.get("TemperatureF"));
            double hottestTemperature = Double.parseDouble(lowestRecord.get("TemperatureF"));
            // Sometimes there was not a valid reading at a specific hour, so the temperature field says -9999.
            if (currentTemperature != -9999 && currentTemperature < hottestTemperature) {
                return currentRecord;
            } else {
                return lowestRecord;
            }
        }
    }

    private CSVRecord getLowestHumidityOfTwoRecords(CSVRecord lowestHumidityRecord, CSVRecord currentRecord) {
        if (lowestHumidityRecord == null) {
            return currentRecord;
        } else {
            if (currentRecord.get("Humidity").indexOf("N/A") == -1) {
                double currentTemperature = Double.parseDouble(currentRecord.get("Humidity"));
                double lowestHumidityValue = Double.parseDouble(lowestHumidityRecord.get("Humidity"));

                if (currentTemperature < lowestHumidityValue) {
                    return currentRecord;
                }
            }
        }
        return lowestHumidityRecord;
    }

    /** Tests methods */
    public void testHottestInDay () {
        FileResource fr = new FileResource("weather-2012-01-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord hottestHour = hottestHourInFile(parser);
        System.out.println("Hottest hour in a day is "+hottestHour.get("TemperatureF")+" at "+hottestHour.get("TimeEST"));
    }

    public void testHottestInManyDays () {
        CSVRecord hottestHour = hottestInManyDays();
        System.out.println("hottest temperature was " + hottestHour.get("TemperatureF") +
                " at " + hottestHour.get("DateUTC"));
    }

    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("Coldest hour in a day is "+coldestRecord.get("TemperatureF")+" at "+coldestRecord.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        File file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+file.getName());

        FileResource fr = new FileResource(file.getAbsoluteFile());
        CSVRecord line = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+line.get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord r : fr.getCSVParser()) {
            System.out.println(r.get("DateUTC")+" : "+r.get("TemperatureF"));
        }
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if (avg == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is "+avg);
        }
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temp is "+avg);
    }

    public static void main(String[] args) {
        CSVMax csvMax = new CSVMax();
        csvMax.testFileWithColdestTemperature();
    }
}