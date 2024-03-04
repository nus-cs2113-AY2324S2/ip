package OGFCore;

import java.io.IOException;
import java.util.ArrayList;
import OGFTask.*;
import OGFCommand.Command;
public class OGF {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String STORAGE_PATH_NAME = "data/tasklist.txt";

    private static Storage listStorage;
    private static TaskList tempTasks;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printIntroMessage();
        try {
            listStorage = new Storage();
            tempTasks = listStorage.readFile();
        }
        catch(OGFException e){
            System.out.println(e.getMessage());
            if (e.getFatal()){
                isRunning = false;
            }
        }
        catch(IOException e){
            System.out.println("There has been a fatal error in reading the storage file. Closing program :("); //made IOExceptions close the program because it is something that the user has to fix while the program is closed
            isRunning = false;
        }
        while (isRunning) {
            String input = ui.getInput();
            try {
                Command command = Parser.parse(input);
                isRunning = command.execute(tempTasks, ui,listStorage);
            }
            catch (OGFException error){
                isRunning = ui.handleOGFException(error);
            }
        }
    }
}

