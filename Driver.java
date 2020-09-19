
import java.util.ArrayList;
import java.util.List;


/**
 * This is a Driver class. This will be the entry point of the applicaiton
 * and will dispatch each of the subsequent algorithms to perform the CPU
 * simulation.
 */
public class Driver {
    /**
     * This is the main function. This is the entrypoint of the application.
     * This will parse the arguements given from the command line and then 
     * dispatch each of the three algorithms for simulation.
     * 
     * @param args is the arguments passed from the command line into the 
     *             application
     */
    public static void main(String[] args) {
        
        // Initiate standard mode
        if (args.length == 1)
        {
            startProgram(args[0], false);
        }
        else
        {
            // Initiate Debug Mode
            if (args.length == 2 && 0 == args[0].compareTo("-d"))
            {
                startProgram(args[1], true);
            }
        }
        // Error Handling
        printUsage();
        System.exit(-1);
    }

    /**
     * Helper function that dispatches the three CPU scheduling algorithms.
     * 
     * @param filename is the filename passed into the application from the command 
     *                 line arguements
     * 
     * @param debug is a boolean flag that indicates whether or not debug mode was 
     *              was requested by the user                
     */
    public static void startProgram(String filename, boolean debug)
    {
        // Get the list of processes from the input file
        List<Process> processes = HelperFunctions.GetListOfProcessesFromFile(filename);
            
            // Initiate the three algorithms if processes file was valid
            if (processes != null)
            {
                // First Come First Serve Algorithm
                if (debug) { System.out.println("First Come First Serve Algorithm:\n\n"); }
                FCFS fcfs = new FCFS(copyProcessIntoNewList(processes), debug);
                fcfs.Run();
                if(debug) { System.out.println("/n/n/n"); }
                
                
                // Preemptive Priority Algorithm
                if (debug) { System.out.println("Preemptive Priority Algorithm:\n\n"); }
                Priority priority = new Priority(copyProcessIntoNewList(processes), debug);
                priority.Run();
                if(debug) { System.out.println("/n/n/n"); }
                
                
                // Round Robin Algorithm
                if (debug) { System.out.println("Round Robin Algorithm:\n\n"); }
                RR roundRobin = new RR(copyProcessIntoNewList(processes), debug);
                roundRobin.Run();
                if(debug) { System.out.println("/n/n/n"); }
                
                // Print final statistics for all of the algorithms
                System.out.print(fcfs.getFinalStatistics());
                System.out.print(priority.getFinalStatistics());
                System.out.println(roundRobin.getFinalStatistics());
            }
            else
            {
                System.out.println("processes.txt file is corrupted\n EXITING");
                System.exit(-1);
            }
    }

    /**
     * Helper function to print the correct usage of the program. Used when there is an 
     * input error from the user.
     */
    public static void printUsage()
    {
        System.out.println("USAGE: java -jar exe.jar [OPTIONAL DEBUGGING OUTPUT -d] [PROCESSES FILE]");
        System.out.println("ALGORITHMS USED: \n\t\tFCFS (First Come First Serve) \n\t\tPRIORITY \n\t\tRR (Round Robin)\n");
    }

    /**
     * Helper function that makes and returns a copy of the given list of processes
     * in a new collection. This is used to ensure each algorithm gets the orginal data
     * from the input file. The returned list is instantiated with the Java ArrayList
     * class. 
     * 
     * @param processes Given list of processes
     * @return Copy of given list in a new collection
     */
    public static List<Process> copyProcessIntoNewList(List<Process> processes)
    {
        List<Process> ps = new ArrayList<Process>();
        processes.forEach(p->ps.add(p));
        return ps;
    }

}
