import java.util.List;

public class FCFS {

    private List<Process> processes;
    private int cpuTicker;

    public FCFS(List<Process> processes) 
    {
        this.processes = processes;
        cpuTicker = 0;
    };

    public void Run() 
    {
        while (isAnyProcessRunning())
        {
            System.out.println("CPU Clock: " + cpuTicker);

            for (Process process : processes) {
                if (process.getArrivalTime() == cpuTicker)
                {
                    process.setState("READY");
                }

                if (process.getStateString() == "READY")
                {
                    
                }





                // Temporary Terminator
                if (cpuTicker > 50)
                {
                    process.setState("TERMINATED");
                }

                System.out.println("PID: " +process.getPID() + " STATE:" + process.getStateString());
            }

            System.out.println();
            cpuTicker++;
        }
    }

    public boolean isAnyProcessRunning()
    {
        for (Process process : processes) {
            if (process.getStateInt() != 4) {return true;}
        }
        return false;
    }
    
}
