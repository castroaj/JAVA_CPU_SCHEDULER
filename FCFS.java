import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FCFS {

    private List<Process> processes;
    private Queue<Process> queue;
    private Process activeProcess;

    private int cpuTicker;
    private int cpuUtilCounter;

    public FCFS(List<Process> processes) 
    {
        this.processes = processes;
        queue = new ArrayBlockingQueue<Process>(processes.size());
        cpuTicker = 0;
        cpuUtilCounter = 0;
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


                    if (activeProcess.getNumOfTimersOnCpu() == 0)
                    {
                        activeProcess.setResponseTime(cpuTicker);
                    }

                    activeProcess.setNumOfTimesOnCpu(activeProcess.getNumOfTimersOnCpu() + 1);
                    System.out.println("\tProcess " + activeProcess.getPID() + " is now allowed to use the CPU\n");
                }
            }

            if (activeProcess != null)
            {
                cpuUtilCounter++;

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
                    activeProcess.setTerminationTime(cpuTicker);
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

    public String getFinalStatistics()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("\nFCFS statistics:");
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
}
