import java.util.List;
import java.util.PriorityQueue;

/**
 * Class that encapsulates the Preemptive Priority CPU scheduling algorithm.
 */
public class Priority 
{    
    private List<Process> processes;
    private PriorityQueue<Process> queue;
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
    public Priority(List<Process> processes, boolean debug) 
    {
        this.processes = processes;
        this.queue = new PriorityQueue<Process>((Process p1, Process p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));
        this.cpuTicker = 0;
        this.cpuUtilCounter = 0;
        this.activeProcess = null;
        this.debug = debug;
    }

    /**
     * Function that runs the CPU simulation using the Priority algorithm
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
            if (queue.size() > 0)
            {
                if (activeProcess == null)
                {
                    activeProcess = queue.poll();

                    if (activeProcess.getNumOfTimesOnCpu() == 0)
                    {
                        activeProcess.setResponseTime(cpuTicker);
                    }

                    activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimesOnCpu() + 1);
                    if (debug) { System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n"); }
                }
                else
                {
                    // Check if highest priority process is of higherpriority than active process
                    if (queue.peek().getPriority() < activeProcess.getPriority())
                    {
                        Process p = queue.poll();
                        activeProcess.setTickCounter(0);
                        queue.add(activeProcess);
                        contextSwitch();
                        activeProcess = p;

                        if (activeProcess.getNumOfTimesOnCpu() == 0)
                        {
                            activeProcess.setResponseTime(cpuTicker);
                        }

                        activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimesOnCpu() + 1);
                    }
                }
            }

            if (activeProcess != null)
            {
                cpuUtilCounter++;

                activeProcess.setTickCounter(activeProcess.getTickCounter() + 1);
                activeProcess.setTotalTicksRemaining(activeProcess.getTotalTicksRemaining() - 1);

                if (debug) 
                {
                    System.out.println("\tActive Process: PID " + activeProcess.getPID() + " (" + activeProcess.getPriority() + ")\n\tProcess has ticked " + 
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

        builder.append("Priority statistics:");
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

    /**
     * Helper function that is called to simulate a context switch.
     */
    public void contextSwitch()
    {
        cpuTicker++;
        if (debug) 
        {
            System.out.println("\nCPU Clock: " + cpuTicker);
            System.out.println("\tCONTEXT SWITCH");
        }
    }

}
