public class Process {

    private int pid;
    private int priority;
    private int arrivalTime;
    private int burstTime;
    private int totalTicks;

    public Process (int pid, int priority, int arrivalTime, int burstTime, int totalTicks)
    {
        this.pid = pid;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.totalTicks = totalTicks;
    }

    

}