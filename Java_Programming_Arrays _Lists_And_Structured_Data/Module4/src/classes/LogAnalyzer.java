package classes;

/**
 * Write a description of class LogAnalyzer here.
 *
 * @author rpavanello
 * @version 1.0
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource("resources/WebLogProgram/"+filename);
        for (String line : fr.lines()) {
            LogEntry log = WebLogParser.parseEntry(line);
            records.add(log);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry log : records) {
            String ip = log.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry log : records) {
            if (log.getStatusCode() > num) {
                System.out.println(log.toString());
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        //someday format MMM DD
        /** This method accesses the web logs in records and returns an ArrayList of
         * Strings of unique IP addresses that had access on the given day. */
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry log : records) {
            String ip = log.getIpAddress();
            String dateStr = log.getAccessTime().toString();
            if (dateStr.contains(someday) && !uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high){
        /**
         * This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive.
         * */
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry log : records) {
            String ip = log.getIpAddress();
            if ((log.getStatusCode() >= low && log.getStatusCode() <= high)
                && !uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }


}
