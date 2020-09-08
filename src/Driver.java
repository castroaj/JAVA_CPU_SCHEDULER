
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        if (args.length == 2)
        {
            List<Process> processes = HelperFunctions.GetListOfProcessesFromFile(args[0]);
            
            if (processes != null)
            {
                switch (args[1])
                {
                    case "FCFS":
                        FCFS fcfs = new FCFS(processes);
                        fcfs.Run();
                        break;

                    case "PRIORITY":

                        break;

                    case "RR":

                        break;

                    default:
                        System.out.println("INVALID ALGORITHM ENTERED\n");
                        printUsage();
                        System.exit(-1);
                }
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
        System.out.println("USAGE: java -jar exe.jar [PROCESSES FILE] [ALGORITHM]");
        System.out.println("ALGORITHM OPTIONS: \n\t\tFCFS (First Come First Serve) \n\t\tPRIORITY \n\t\tRR (Round Robin)\n");
    }

    public static void printProcesses(List<Process> processes)
    {
        for (Process p : processes)
        {
            System.out.println(p.toString());
        }
    }

}
