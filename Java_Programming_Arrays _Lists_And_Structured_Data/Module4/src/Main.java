import classes.LogAnalyzer;
import classes.LogEntry;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        System.out.println("Running main");
        //testLogEntry();

        testLogAnalyzer();

        System.out.println("Main completed");
    }

    /**
     * Class 1 Video - LogEntry
     */
    public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    /**
     * Class 2 Video - LogAnalyzer
     */
    public static void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        logAnalyzer.printAll();
    }
}