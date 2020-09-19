/**
 * Class that encapsulates a single process.
 */
public class Process {

    private int pid;
    private int priority;
    private int arrivalTime;
    private int burstTime;
    private int totalTicks;
    private int totalTicksRemaining;
    private int tickCounter;
    private int responseTime;
    private int numOfTimesOnCpu;
    private int terminationTime;
    private boolean isTerminated;
    

    /**
     * Copy constructor
     * 
     * @param otherProcess is the process that will be copied
     */
    public Process (Process otherProcess)
    {
        this.pid = otherProcess.pid;
        this.priority = otherProcess.priority;
        this.arrivalTime = otherProcess.arrivalTime;
        this.burstTime = otherProcess.burstTime;
        this.totalTicks = otherProcess.totalTicks;
        this.totalTicksRemaining = otherProcess.totalTicksRemaining;
        this.tickCounter = otherProcess.tickCounter;
        this.responseTime = otherProcess.responseTime;
        this.isTerminated = otherProcess.isTerminated;
        this.terminationTime = otherProcess.terminationTime;
        this.numOfTimesOnCpu = otherProcess.numOfTimesOnCpu;
    }

    /**
     * Explicit value constructor
     * 
     * @param pid
     * @param priority
     * @param arrivalTime
     * @param burstTime
     * @param totalTicks
     */
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
        this.responseTime = 0;
        this.isTerminated = false;
        this.terminationTime = -1;
    }

    /**
     * Convert object into a readable string.
     * 
     * @return string version of a Process
     */
    public String toString()
    {
        return "\nPID: " + pid + "\nPriority: " + priority+ "\nArrival Time: " + 
        arrivalTime + "\nBurst Time: " + burstTime + "\nTotal Ticks: " + totalTicks + "\n";
    }

    /**
     * Getter for priority attribute.
     * 
     * @return the priority attribute
     */
    public int getPriority()
    {
        return this.priority;
    }

    /**
     * Getter for PID attribute.
     * 
     * @return the PID attribute
     */
    public int getPID()
    {
        return this.pid;
    }

    /**
     * Getter for ArrivalTime attribute.
     * 
     * @return the ArrivalTime attribute
     */
    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    /**
     * Getter for BurstTime attribute.
     * 
     * @return the BurstTime attribute
     */
    public int getBurstTime()
    {
        return this.burstTime;
    }

    /**
     * Getter for TotalTicks attribute.
     * 
     * @return the TotalTicks attribute
     */
    public int getTotalTicks()
    {
        return this.totalTicks;
    }

    /**
     * Getter for isTerminated attribute
     * 
     * @return the isTerminated attribute
     */
    public boolean getIsTerminated()
    {
        return this.isTerminated;
    }

    /**
     * Getter for the TotalTicksRemaining attribute
     * 
     * @return the TotalTicksRemaining attribute
     */
    public int getTotalTicksRemaining()
    {
        return this.totalTicksRemaining;
    }

    /**
     * Getter for the TickCounter attribute
     * 
     * @return the TickCounter attribute
     */
    public int getTickCounter()
    {
        return this.tickCounter;
    }

    /**
     * Getter for the NumOfTimesOnCpu attribute
     * 
     * @return the NumOfTimesOnCpu attribute
     */
    public int getNumOfTimesOnCpu()
    {
        return this.numOfTimesOnCpu;
    }

    /**
     * Getter for the ResponseTime attribute
     * 
     * @return the ResponseTime attribute
     */
    public int getResponseTime()
    {
        return this.responseTime;
    }

    /**
     * Getter for the TerminationTime attribute
     * 
     * @return the TerminationTime attribute
     */
    public int getTerminationTime()
    {
        return this.terminationTime;
    }

    /**
     * Setter for IsTerminated attribute
     * 
     * @param b is the new IsTerminated attribute
     */
    public void setIsTerminated(boolean b)
    {
        this.isTerminated = b;
    }

    /**
     * Setter for the TotalTicksRemaining attribute
     * 
     * @param ticks is the new TotalTicksRemaining attribute
     */
    public void setTotalTicksRemaining(int ticks)
    {
        this.totalTicksRemaining = ticks;
    }

    /**
     * Setter for the TickCounter attribute
     * 
     * @param tickCounter is the new TickCounter attribute
     */
    public void setTickCounter(int tickCounter)
    {
        this.tickCounter = tickCounter;
    }

    /**
     * Setter for the NumOfTimesOnCpu attribute 
     * 
     * @param num is the new NumOfTimesOnCpu attribute
     */
    public void setNumOfTimesOnCpu(int num)
    {
        this.numOfTimesOnCpu = num;
    }

    /**
     * Setter for the ResponseTime attribute
     * 
     * @param r is the new ResponeTime attribute
     */
    public void setResponseTime(int r)
    {
        this.responseTime = r;
    }

    /**
     * Setter for the TerminationTime attribute
     * 
     * @param t is the new TerminationTime attribute
     */
    public void setTerminationTime(int t)
    {
        this.terminationTime = t;
    }
}