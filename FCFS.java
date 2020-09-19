import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Class that encapsulates the First Come First Serve CPU scheduling algorithm.
 */
public class FCFS {

    private List<Process> processes;
    private Queue<Process> queue;
    private Process activeProcess;

    private int cpuTicker;
    private int cpuUtilCounter;
    private boolean debug;

    /**
     * Constructor function for the FCFS class.
     * 
     * @param processes is the list of processes that will be processed
     * @param debug is a boolean flag used for optional logging
     */
    public FCFS(List<Process> processes, boolean debug) 
    {
        this.processes = processes;
        this.queue = new ArrayBlockingQueue<Process>(processes.size());
        this.cpuTicker = 0;
        this.cpuUtilCounter = 0;
        this.activeProcess = null;
        this.debug = debug;
    }

    /**
     * Function that runs the CPU simulation using the FCFS algorithm
     */
    public void Run() 
    {
        while (HelperFunctions.isAnyProcessRunning(processes, debug))
        {
            // Check for arrival of processes
            processes.forEach(p->{
                if (p.getArrivalTime() == cpuTicker)
                {
                    if (debug) { System.out.println("\tProcess " + p.getPID() + " is being added to ready queue\n"); }
                    queue.add(p);
                }
            });

            if (debug) 
            {   
                // Print CPU clock
                System.out.println("CPU Clock: " + cpuTicker);

                // Print if the CPU is IDLE
                if ((queue.size() == 0  && activeProcess == null))
                {
                    System.out.println("\tIDLE");
                }
            }

            // Give queued process the CPU if it is free
            if (queue.size() > 0 && activeProcess == null)
            {
                activeProcess = queue.poll();

                if (activeProcess.getNumOfTimesOnCpu() == 0)
                {
                    activeProcess.setResponseTime(cpuTicker);
                }

                activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimesOnCpu() + 1);
                if (debug) { System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n"); }
            }

            // While active process exists
            if (activeProcess != null)
            {
                cpuUtilCounter++;

                activeProcess.setTickCounter(activeProcess.getTickCounter() + 1);
                activeProcess.setTotalTicksRemaining(activeProcess.getTotalTicksRemaining() - 1);

                if (debug) 
                {
                    System.out.println("\tActive Process: PID " + activeProcess.getPID() + "\n\tProcess has ticked " + 
                                    activeProcess.getTickCounter() + " times\n\tIt has " + activeProcess.getTotalTicksRemaining() +
                                     " total ticks remaining");
                }

                // Check if the process is finished
                if (activeProcess.getTotalTicksRemaining() == 0)
                {
                    if (debug) { System.out.println("\tProcess has finished. The CPU is now free for the next ready process"); }
                    activeProcess.setIsTerminated(true);
                    activeProcess.setTerminationTime(cpuTicker);
                    activeProcess = null;
                }
            }

            if (debug) { System.out.println(); }
            cpuTicker++;
        }
    }

    /**
     * Function that generates the final statistics after the algorithm has been run.
     * 
     * @return string containing all the final statistics
     */
    public String getFinalStatistics()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("FCFS statistics:");
        builder.append("\n\tCPU utilization = " + String.format("%.06f", HelperFunctions.calculateCpuUtil(cpuUtilCounter, cpuTicker)) + " (" + cpuUtilCounter + "/" + cpuTicker + ")");
        builder.append("\n\tAverage response time = " + String.format("%.06f", HelperFunctions.calculateAverageResponseTime(processes)) + " ticks");
        builder.append("\n\tAverage turnaround time = " + String.format("%.06f", HelperFunctions.calculateTurnaroundTime(processes)) + " ticks");
        
        processes.forEach(p->{
            builder.append("\n\tProcess " + p.getPID() 
            + ": entered=" + p.getArrivalTime() 
            + " response=" + p.getResponseTime() 
            + " finished=" + p.getTerminationTime());
        });
        
        builder.append("\n");

        return builder.toString();
    }
}
