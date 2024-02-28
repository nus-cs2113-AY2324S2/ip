import java.util.Scanner;

public class ProcessCommands {
    private final TaskList taskList;

    public ProcessCommands(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * identifies the command type and passes the command
     * to the relevant method in TaskList
     * @param command user command to handle
     * @throws ArrayIndexOutOfBoundsException if command format is invalid
     * @throws NumberFormatException if a number in a command is invalid
     */
    private void processTaskCommand(String command) {
        try {
            String[] commandParts = command.split(" ", 2);
            String taskType = commandParts[0];

            switch (taskType) {
            case "todo":
                taskList.addTask(new Task(commandParts[1]), true);
                break;
            case "deadline":
                taskList.addDeadlineTask(commandParts, true);
                break;
            case "event":
                taskList.addEvent(commandParts, true);
                break;
            case "mark":
                taskList.markTask(commandParts);
                break;
            case "unmark":
                taskList.unmarkTask(commandParts);
                break;
            case "delete":
                taskList.deleteTask(Integer.parseInt(commandParts[1])-1);
                break;
            default:
                System.out.println("☹ Dobby does not understand."); // Default to addTask if not recognised
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Provide valid input");
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("Invalid command format. Input a valid number");
            throw e;
        }
    }

    /**
     * reads user command and passes it to the suitable method
     */
    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine().toLowerCase();
            String[] commandParts = command.split(" ", 2);

            switch (commandParts[0]) {
            case "bye":
                System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                return;
            case "list":
                taskList.listTasks();
                break;
            default:
                processTaskCommand(command);
                break;
            }

        }
    }


}
