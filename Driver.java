import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Process> processes = HelperFunctions.GetListOfProcessesFromFile(args[0]);
        
        if (processes != null)
        {
            System.out.println(processes.size());
        }
        
        

    }
}
