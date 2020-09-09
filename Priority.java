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
    private boolean contextSwitch;

    public Priority(List<Process> processes) 
    {
        this.processes = processes;
        queue = new PriorityQueue<Process>((Process p1, Process p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));
        cpuTicker = 0;
        cpuUtilCounter = 0;
        activeProcess = null;
        contextSwitch = false;
    }

    public void Run()
    {
        while (isAnyProcessRunning())
        {
            System.out.println("CPU Clock: " + cpuTicker);

            // Check for arrival of processes
            for (Process process : processes) {
                if (process.getArrivalTime() == cpuTicker)
                {
                    System.out.println("\tProcess " + process.getPID() + " is being added to ready queue\n");
                    queue.add(process);
                }
            }

            // Print if the CPU is IDLE
            if (queue.size() == 0  && activeProcess == null)
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
                    System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n");
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
                    }
                }
            }

            if (activeProcess != null && !contextSwitch)
            {
                cpuUtilCounter++;

                activeProcess.setTickCounter(activeProcess.getTickCounter() + 1);
                activeProcess.setTotalTicksRemaining(activeProcess.getTotalTicksRemaining() - 1);

                System.out.println("\tActive Process: PID " + activeProcess.getPID() + " (" + activeProcess.getPriority() + ")\n\tProcess has ticked " + 
                                    activeProcess.getTickCounter() + " times out of "+ activeProcess.getBurstTime() 
                                    +" this burst\n\tIt has " + activeProcess.getTotalTicksRemaining() +
                                     " total ticks remaining");


                // Check if the process is finished
                if (activeProcess.getTotalTicksRemaining() == 0)
                {
                    System.out.println("\tProcess has finished. The CPU is now free for the next ready process");
                    activeProcess.setIsTerminated(true);
                    activeProcess.setTerminationTime(cpuTicker);
                    activeProcess = null;
                }

                // // Check if the burst limit has been reached
                // if (activeProcess != null && activeProcess.getTickCounter() == activeProcess.getBurstTime())
                // {
                //     System.out.println("\tProcess "+ activeProcess.getPID() +" has reached burst limit. Process is now yielding the CPU to the next ready process");
                //     activeProcess.setTickCounter(0);
                //     queue.add(activeProcess);
                //     activeProcess = null;
                //     contextSwitch();
                // }

            }

            if (contextSwitch) { contextSwitch = false; }

            System.out.println();
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
        System.out.println("\nAll Processes have terminated");
        return false;
    }

    public void contextSwitch()
    {
        contextSwitch = true;
        cpuTicker++;
        System.out.println("\nCPU Clock: " + cpuTicker);
        System.out.println("\tCONTEXT SWITCH");
    }

}
