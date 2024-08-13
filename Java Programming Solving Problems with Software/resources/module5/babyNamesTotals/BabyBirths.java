/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNamesGirls = 0;
		int totalNamesBoys = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalNamesBoys++;
			}
			else {
				totalGirls += numBorn;
				totalNamesGirls++;
			}
		}
		System.out.println("Total births = " + totalBirths);
		System.out.println("Female girls = " + totalGirls);
		System.out.println("Female girls names = " + totalNamesGirls);
		System.out.println("Male boys names = " + totalNamesBoys);
		System.out.println("Male boys = " + totalBoys);
	}

	public int getRank(int year, String name, String gender) {
		//Check if it is a short test or full test.
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
		int ranking = 1;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					return ranking;
				}
				ranking++;
			}
		}
		return -1;
	}

	public String getName(int year, int rank, String gender) {
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
		int count = 1;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rank == count) {
					return rec.get(0);
				}
				count ++;
			}
		}
		return "NO NAME";
	}

	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
		int rank = 1;
		int count = 1;
		String newName;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					rank = count;
				}
				count++;
			}
		}

		newName = getName(newYear,rank,gender);
		System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear+".");
	}

	public int yearOfHighestRank(String name, String gender) {
		//Return the year with the highest rank for the name and gender in the earliest year.
		DirectoryResource dr = new DirectoryResource();
		int highestRank = 0;
		int year = -1;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int currentRank = getRankFromFile(name, gender, fr);
			if(currentRank != -1 && (highestRank == 0 || currentRank < highestRank)) {
				year = Integer.parseInt(f.getName().substring(3, 7));
				highestRank = currentRank;
			}
		}
		return year;
	}

	public double getAverageRank (String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int totalFound = 0;
		int totalRank = 0;

		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);

			int rank = getRankFromFile(name, gender,fr);
			if (rank != -1) {
				totalRank += rank;
				totalFound++;
			}
		}
		if (totalRank > 0) {
			return (double) totalRank / totalFound;
		} else {
			return -1;
		}
	}

	public int getRankFromFile(String name, String gender, FileResource fr) {
		int ranking = 1;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					return ranking;
				}
				ranking++;
			}
		}
		return -1;
	}

	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int totalBirths = 0;
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name))
					break;
				totalBirths += Integer.parseInt(rec.get(2));
			}
		}

		return totalBirths;
	}

	private void testTotalBirths () {
		FileResource fr = new FileResource();
		//FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}

	private void testGetRank () {
		int ranking = getRank(1971,"Frank","M");
		System.out.println("The ranking for this name is "+ranking);
	}

	private void testGetName() {
		String name = getName(1982,450,"M");
		System.out.println("Test getName() result: "+name);
	}

	private void testWhatIsNameInYear() {
		whatIsNameInYear("Owen",1974,2014,"M");
	}

	private void testYearOfHighestRank() {
		int year = yearOfHighestRank("Mich","M");
		System.out.println(year);
	}

	private void testGetAverageRank() {
		double avg = getAverageRank("Robert","M");
		System.out.println(avg);
	}

	private void testGetTotalBirthsRankedHigher() {
		int totalBirth = getTotalBirthsRankedHigher(1990,"Drew","M");
		System.out.println(totalBirth);
	}

	public static void main(String[] args) {
		BabyBirths bb = new BabyBirths();
		bb.testGetTotalBirthsRankedHigher();
	}
}
