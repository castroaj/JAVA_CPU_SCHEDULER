import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class HelperFunctions {

    public static List<Process> GetListOfProcessesFromFile(String filename)
    {
        List<Process> processes = new ArrayList<Process>();
        try 
        {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine())
            {
                String[] lineSplit = scan.nextLine().split(" ");

                if (lineSplit.length == 5)
                {
                    processes.add(new Process(
                                        Integer.parseInt(lineSplit[0]), 
                                        Integer.parseInt(lineSplit[1]),
                                        Integer.parseInt(lineSplit[2]), 
                                        Integer.parseInt(lineSplit[3]), 
                                        Integer.parseInt(lineSplit[4])));
                }
                else
                {
                    return null;
                }
            }
            return processes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}