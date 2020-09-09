
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        if (args.length == 1)
        {
            List<Process> processes = HelperFunctions.GetListOfProcessesFromFile(args[0]);
            
            if (processes != null)
            {
                FCFS fcfs = new FCFS(processes);
                fcfs.Run();

                System.out.println("Finished");
                
            }
            else
            {
                System.out.println("processes.txt file is corrupted\n EXITING");
                System.exit(-1);
            }
        }
        else
        {
            printUsage();
            System.exit(-1);
        }   
    }

    public static void printUsage()
    {
        System.out.println("USAGE: java -jar exe.jar [PROCESSES FILE]");
        System.out.println("ALGORITHMS USED: \n\t\tFCFS (First Come First Serve) \n\t\tPRIORITY \n\t\tRR (Round Robin)\n");
    }

    public static void printProcesses(List<Process> processes)
    {
        for (Process p : processes)
        {
            System.out.println(p.toString());
        }
    }

}
