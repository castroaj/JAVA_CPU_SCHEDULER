import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Priority 
{    
    private List<Process> processes;
    private PriorityQueue<Process> queue;
    private Process activeProcess;

    private int cpuTicker;
    private int cpuUtilCounter;
    private boolean debug;

    public Priority(List<Process> processes, boolean debug) 
    {
        this.processes = processes;
        this.queue = new PriorityQueue<Process>((Process p1, Process p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));
        this.cpuTicker = 0;
        this.cpuUtilCounter = 0;
        this.activeProcess = null;
        this.debug = debug;
    }

    public void Run()
    {
        while (isAnyProcessRunning())
        {
            if (debug) { System.out.println("CPU Clock: " + cpuTicker); }

            // Check for arrival of processes
            for (Process process : processes) {
                if (process.getArrivalTime() == cpuTicker)
                {
                    if (debug) { System.out.println("\tProcess " + process.getPID() + " is being added to ready queue\n"); }
                    queue.add(process);
                }
            }

            // Print if the CPU is IDLE
            if ((queue.size() == 0  && activeProcess == null) && debug)
            {
                System.out.println("\tIDLE");
            }

            // Give queued process the CPU if it is free
            if (queue.size() > 0)
            {
                if (activeProcess == null)
                {
                    activeProcess = queue.poll();

                    if (activeProcess.getNumOfTimersOnCpu() == 0)
                    {
                        activeProcess.setResponseTime(cpuTicker);
                    }

                    activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimersOnCpu() + 1);
                    if (debug) { System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n"); }
                }
                else
                {
                    if (queue.peek().getPriority() < activeProcess.getPriority())
                    {
                        Process p = queue.poll();
                        activeProcess.setTickCounter(0);
                        queue.add(activeProcess);
                        contextSwitch();
                        activeProcess = p;

                        if (activeProcess.getNumOfTimersOnCpu() == 0)
                        {
                            activeProcess.setResponseTime(cpuTicker);
                        }

                        activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimersOnCpu() + 1);
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

    public boolean isAnyProcessRunning()
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

    public String getFinalStatistics()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Priority statistics:");
        builder.append("\n\tCPU utilization = " + String.format("%.06f", calculateCpuUtil()) + " (" + cpuUtilCounter + "/" + cpuTicker + ")");
        builder.append("\n\tAverage response time = " + String.format("%.06f", calculateAverageResponseTime()) + " ticks");
        builder.append("\n\tAverage turnaround time = " + String.format("%.06f", calculateTurnaroundTime()) + " ticks");
        
        for (Process process : processes) {
            builder.append("\n\tProcess " + process.getPID() 
            + ": entered=" + process.getArrivalTime() 
            + " response=" + process.getResponseTime() 
            + " finished=" + process.getTerminationTime());
        }
        builder.append("\n");

        return builder.toString();
    }


    public double calculateCpuUtil()
    {
        return (double) this.cpuUtilCounter / (double) this.cpuTicker;
    }

    public double calculateAverageResponseTime()
    {
        double val = 0; 
        for (Process process : processes) {
            val += ((double) process.getResponseTime() - (double) process.getArrivalTime());
        }
        return val / (double) processes.size();

    }

    public double calculateTurnaroundTime()
    {
        double val = 0; 
        for (Process process : processes)
        {
            val += process.getTerminationTime() - process.getArrivalTime();
        }
        return val / (double) processes.size();

    }

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
