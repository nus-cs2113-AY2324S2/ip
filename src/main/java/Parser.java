import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the command given by the user to run the respective function
     * @param command the command given by the user
     * @param isRestore to toggle between restoring a saved file or new addition
     * @return true if (command == bye)
     * @throws IOException when there is an error writing to the save file.
     */
    public static boolean parseCommand(String command, boolean isRestore) throws IOException {
        String[] splitCommand = command.split(" ", 2);
        String commandWord =  splitCommand[0];
        switch (commandWord) {
        case "bye":
            return true;
        case "mark":
            TaskList.markTask(splitCommand[1]);
            break;
        case "unmark":
            TaskList.unmarkTask(splitCommand[1]);
            break;
        case "delete":
            TaskList.deleteTask(splitCommand[1]);
            break;
        case "list":
            Ui.printTasks(TaskList.tasks);
            break;
        case "todo":
            //fallthrough
        case "deadline":
            //fallthrough
        case "event":
            if (!isRestore) {
                System.out.println("Added: ");
            }
            TaskList.addNewTask(commandWord, splitCommand[1]);
            if (!isRestore) {
                Storage.writeToFile(command);
            }
            break;
        case "find":
            ArrayList<Task> tasks = TaskList.findTasks(splitCommand[1]);
            Ui.printTasks(tasks);
            break;
        default:
            System.out.println("Invalid command, please try again: ");
            break;
        }
        return false;
    }
}
