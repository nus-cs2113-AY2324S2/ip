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
     *
     */
    private void processTaskCommand(String command) {
        try {
            String[] commandParts = command.split(" ", 2);
            String taskType = commandParts[0];

            switch (taskType) {
            case "todo":
                if (commandParts[1].trim().isEmpty()) {
                    System.out.println("Invalid command format for 'todo'. Provide a description.");
                    return;
                }
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
            case "find":
                taskList.findTasks(commandParts[1]);
                break;
            default:
                System.out.println("☹ Dobby does not understand.");
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Provide valid input: <Task type> <Description> ");

        } catch (NumberFormatException e) {
            System.out.println("Invalid command format. Input a valid number");

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
