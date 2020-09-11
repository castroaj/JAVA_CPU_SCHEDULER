
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class HelperFunctions {

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


    public static double calculateCpuUtil(int cpuUtilCounter, int cpuTicker)
    {
        return (double) cpuUtilCounter / (double) cpuTicker;
    }

    public static double calculateAverageResponseTime(List<Process> processes)
    {
        double val = 0; 
        for (Process process : processes) {
            val += ((double) process.getResponseTime() - (double) process.getArrivalTime());
        }
        return val / (double) processes.size();

    }

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