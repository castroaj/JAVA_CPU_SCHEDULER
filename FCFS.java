import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FCFS {

    private List<Process> processes;
    private Queue<Process> queue;
    private Process activeProcess;

    private int cpuTicker;

    public FCFS(List<Process> processes) 
    {
        this.processes = processes;
        queue = new ArrayBlockingQueue<Process>(processes.size());
        cpuTicker = 0;
        activeProcess = null;
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
                    activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimersOnCpu() + 1);
                    System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n");
                }
            }


            if (activeProcess != null)
            {
                activeProcess.setTickCounter(activeProcess.getTickCounter() + 1);
                activeProcess.setTotalTicksRemaining(activeProcess.getTotalTicksRemaining() - 1);

                System.out.println("\tActive Process: PID " + activeProcess.getPID() + "\n\tProcess has ticked " + 
                                    activeProcess.getTickCounter() + " times out of "+ activeProcess.getBurstTime() 
                                    +" this burst\n\tIt has " + activeProcess.getTotalTicksRemaining() +
                                     " total ticks remaining");


                // Check if the process is finished
                if (activeProcess.getTotalTicksRemaining() == 0)
                {
                    System.out.println("\tProcess has finished. The CPU is now free for the next ready process");
                    activeProcess.setIsTerminated(true);
                    activeProcess = null;
                }

                // Check if the burst limit has been reached
                if (activeProcess != null && activeProcess.getTickCounter() == activeProcess.getBurstTime())
                {
                    System.out.println("\tProcess "+ activeProcess.getPID() +" has reached burst limit. Process is now yielding the CPU to the next ready process");
                    activeProcess.setTickCounter(0);
                    queue.add(activeProcess);
                    activeProcess = null;
                }

            }

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
    
}
