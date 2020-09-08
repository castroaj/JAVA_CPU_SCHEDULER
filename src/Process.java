
public class Process {

    private int pid;
    private int priority;
    private int arrivalTime;
    private int burstTime;
    private int totalTicks;
    private int state;

    public Process (int pid, int priority, int arrivalTime, int burstTime, int totalTicks)
    {
        this.pid = pid;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.totalTicks = totalTicks;
        setState("NEW");
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

    public void setState(String stateString)
    {
        switch (stateString)
        {
            case "NEW":
                state = 0;
                break;
            case "READY":
                state = 1;
                break;
            case "RUNNING":
                state = 2;
                break;
            case "WAITING":
                state = 3;
                break;
            case "TERMINATED":
                state = 4;
                break;
            default:
                break;
        }
    }

    public int getStateInt()
    {
        return state;
    }

    public String getStateString()
    {
        switch (state)
        {
            case 0:
                return "NEW";
            case 1:
                return "READY";
            case 2:
                return "RUNNING";
            case 3:
                return "WAITING";
            case 4:
                return "TERMINATED";
            default:
                return null;
        }
    }
}