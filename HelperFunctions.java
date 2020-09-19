
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

/**
 * Class of static helper functions used to prevent code duplication.
 */
public class HelperFunctions {

    /**
     * Helper function that parses the input file and creates a collection
     * of process objects.
     * 
     * @param filename is the input file name from the command line
     * @return a list of process objects derived from the input file
     */
    public static List<Process> GetListOfProcessesFromFile(String filename)
    {
        List<Process> processes = new ArrayList<Process>();
        try 
        {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine())
            {
                String[] lineSplit = scan.nextLine().split(" ");

                if (lineSplit.length == 5)
                {
                    processes.add(new Process(
                                        Integer.parseInt(lineSplit[0]), 
                                        Integer.parseInt(lineSplit[1]),
                                        Integer.parseInt(lineSplit[2]), 
                                        Integer.parseInt(lineSplit[3]), 
                                        Integer.parseInt(lineSplit[4])));
                }
                else
                {
                    return null;
                }
            }
            return processes;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * Function that determines if any of the processes are flagged as terminated or not.
     * 
     * @param processes is the list of processes to check
     * @param debug is whether or not to print debug message
     * @return Whether or not any processes are still running
     */
    public static boolean isAnyProcessRunning(List<Process> processes, boolean debug)
    {
        for (Process process : processes) {
            if (!process.getIsTerminated()) 
            {
                return true;
            }
        }
        if (debug) { System.out.println("\nAll Processes have terminated"); }
        return false;
    }

    /**
     * Function that calculates the CPU utilization.
     * 
     * @param cpuUtilCounter is how many ticks used the CPU
     * @param cpuTicker is the total number of ticks that occured
     * @return
     */
    public static double calculateCpuUtil(int cpuUtilCounter, int cpuTicker)
    {
        return (double) cpuUtilCounter / (double) cpuTicker;
    }

    /**
     * Function that calculates the AVG response time using information stored
     * in each process.
     * 
     * @param processes is the list of processes
     * @return average response time
     */
    public static double calculateAverageResponseTime(List<Process> processes)
    {
        double val = 0; 
        for (Process process : processes) {
            val += ((double) process.getResponseTime() - (double) process.getArrivalTime());
        }
        return val / (double) processes.size();
    }

    /**
     * Function that calculates the AVG turnaround time using information in each
     * process.
     * 
     * @param processes is the list of processes
     * @return average turnaround time
     */
    public static double calculateTurnaroundTime(List<Process> processes)
    {
        double val = 0; 
        for (Process process : processes)
        {
            val += process.getTerminationTime() - process.getArrivalTime();
        }
        return val / (double) processes.size();

    }

}