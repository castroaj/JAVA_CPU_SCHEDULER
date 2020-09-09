
public class Process {

    private int pid;
    private int priority;
    private int arrivalTime;
    private int burstTime;
    private int totalTicks;
    private int totalTicksRemaining;
    private int tickCounter;
    private boolean isTerminated;
    private int numOfTimesOnCpu;

    public Process (Process otherProcess)
    {
        this.pid = otherProcess.pid;
        this.priority = otherProcess.priority;
        this.arrivalTime = otherProcess.arrivalTime;
        this.burstTime = otherProcess.burstTime;
        this.totalTicks = otherProcess.totalTicks;
        this.totalTicksRemaining = otherProcess.totalTicksRemaining;
        this.tickCounter = otherProcess.tickCounter;
        this.isTerminated = otherProcess.isTerminated;
        this.numOfTimesOnCpu = otherProcess.numOfTimesOnCpu;
    }

    public Process (int pid, int priority, int arrivalTime, int burstTime, int totalTicks)
    {
        this.pid = pid;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.totalTicks = totalTicks;
        this.totalTicksRemaining = totalTicks;
        this.tickCounter = 0;
        this.numOfTimesOnCpu = 0;
        this.isTerminated = false;
    }

    public String toString()
    {
        return "\nPID: " + pid + "\nPriority: " + priority+ "\nArrival Time: " + arrivalTime + "\nBurst Time: " + burstTime + "\nTotal Ticks: " + totalTicks + "\n";
    }

    public int getPID()
    {
        return pid;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    public int getBurstTime()
    {
        return burstTime;
    }

    public int getTotalTicks()
    {
        return totalTicks;
    }

    public boolean getIsTerminated()
    {
        return isTerminated;
    }

    public void setIsTerminated(boolean b)
    {
        isTerminated = b;
    }

    public int getTotalTicksRemaining()
    {
        return this.totalTicksRemaining;
    }

    public void setTotalTicksRemaining(int ticks)
    {
        totalTicksRemaining = ticks;
    }

    public int getTickCounter()
    {
        return tickCounter;
    }

    public void setTickCounter(int tickCounter)
    {
        this.tickCounter = tickCounter;
    }

    public void setNumOfTimesOnCpu(int num)
    {
        this.numOfTimesOnCpu = num;
    }

    public int getNumOfTimersOnCpu()
    {
        return numOfTimesOnCpu;
    }

}