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
        HashMap<String,Integer> counts = countVisitsPerIP();
        return counts.size();
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

    public HashMap<String,Integer> countVisitsPerIP() {
        /** This method count all visits per IP in a single file.*/
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            if (!counts.containsKey(le.getIpAddress())) {
                counts.put(le.getIpAddress(),1);
            } else {
                Integer t = counts.get(le.getIpAddress());
                counts.put(le.getIpAddress(),t+1);
            }
        }
        return counts;
    }

    public Integer mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        /** This method returns the maximum number of visits to this website by a single IP address.
         * For example, the call mostNumberVisitsByIP on a HashMap formed using the file weblog3-short_log returns 3.*/

        Integer visits = 0;
        for (String k : counts.keySet()) {
            if (counts.get(k) > visits) {
                visits = counts.get(k);
            }
        }
        return visits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        /** This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website. */
        ArrayList<String> iPs = new ArrayList<String>();
        Integer mostVisits = mostNumberVisitsByIP(counts);
        for (String k : counts.keySet()) {
            if (counts.get(k) == mostVisits) {
                iPs.add(k);
            }
        }
        return iPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        /** This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList
         * of IP addresses that occurred on that day (including repeated IP addresses).*/
        HashMap<String, ArrayList<String>> iPsByDay = new HashMap<String, ArrayList<String>>();

        for (LogEntry log : records) {
            String day = log.getAccessDay();
            if (!iPsByDay.containsKey(day)) {
                ArrayList<String> ips = new ArrayList<>(Collections.singletonList(log.getIpAddress()));
                iPsByDay.put(day,ips);
            } else {
                ArrayList<String> ips = iPsByDay.get(day);
                ips.add(log.getIpAddress());
            }
        }
        return iPsByDay;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsByDay) {
    /** This method returns the day that has the most IP address visits. If there is a tie, then return any such day.  */
        int dayWithMostIPVisitsCount = 0;
        String dayWithMostIPVisits = "";
        for (String k : iPsByDay.keySet()) {
            if (iPsByDay.get(k).size() > dayWithMostIPVisitsCount) {
                dayWithMostIPVisitsCount = iPsByDay.get(k).size();
                dayWithMostIPVisits = k;
            }
        }
        return dayWithMostIPVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsByDay, String day) {
        /** This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day. */

        HashMap<String,Integer> countIPsFromDay = new HashMap<String,Integer>();


        for (String ip : iPsByDay.get(day)) {
            if (!countIPsFromDay.containsKey(ip)) {
                countIPsFromDay.put(ip,1);
            } else {
                Integer t = countIPsFromDay.get(ip);
                countIPsFromDay.put(ip,t+1);
            }
        }
        return iPsMostVisits(countIPsFromDay);
    }
}
