import java.io.IOException;

public class Parser {
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
            TaskList.printAllTasks();
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
        default:
            System.out.println("Invalid command, please try again: ");
            break;
        }
        return false;
    }
}
