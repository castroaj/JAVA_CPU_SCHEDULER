
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        if (args.length == 1)
        {
            startProgram(args[0], false);
        }
        else
        {
            if (args.length == 2 && 0 == args[0].compareTo("-d"))
            {
                startProgram(args[1], true);
            }
            else
            {
                printUsage();
                System.exit(-1);
            }
        }   
    }

    public static void startProgram(String filename, boolean debug)
    {
        List<Process> processes = HelperFunctions.GetListOfProcessesFromFile(filename);
            
            if (processes != null)
            {
                // First Come First Serve Algorithm
                FCFS fcfs = new FCFS(copyProcessIntoNewList(processes), debug);
                fcfs.Run();
                System.out.print(fcfs.getFinalStatistics());
                
                Priority priority = new Priority(copyProcessIntoNewList(processes), debug);
                priority.Run();
                System.out.print(priority.getFinalStatistics());

                RR roundRobin = new RR(copyProcessIntoNewList(processes), debug);
                priority.Run();
                System.out.println(roundRobin.getFinalStatistics());
                
            }
            else
            {
                System.out.println("processes.txt file is corrupted\n EXITING");
                System.exit(-1);
            }
    }

    public static void printUsage()
    {
        System.out.println("USAGE: java -jar exe.jar [OPTIONAL DEBUGGING OUTPUT -d] [PROCESSES FILE]");
        System.out.println("ALGORITHMS USED: \n\t\tFCFS (First Come First Serve) \n\t\tPRIORITY \n\t\tRR (Round Robin)\n");
    }

    public static void printProcesses(List<Process> processes)
    {
        for (Process p : processes)
        {
            System.out.println(p.toString());
        }
    }

    public static ArrayList<Process> copyProcessIntoNewList(List<Process> processes)
    {
        ArrayList<Process> ps = new ArrayList<Process>();
        for (Process process : processes) {
            ps.add(new Process(process));
        }
        return ps;
    }

}
