import java.util.Scanner;

public class Carrot {

    public static void main(String[] args) {
        Ui.greetUser();
        Ui.printHelpCommand();

        while (true) {
            String userInput = Ui.readUserInput();

            CommandType userCommand = Parser.getCommandType(userInput);
            processUserCommand(userCommand, userInput, Ui.scanner);
        }
    }

    private static void processUserCommand(CommandType userCommand, String userInput, Scanner scanner) {
        switch (userCommand) {
        case HELP:
            Ui.printHelpCommand();
            break;
        case LIST:
            Ui.printListItems(TaskList.getTaskList());
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            addTaskToList(userCommand, userInput);
            break;
        case DELETE:
            deleteTaskFromList(userInput);
            break;
        case MARK:
        case UNMARK:
            handleMarkUnmarkTask(userCommand, userInput);
            break;
        case EXIT:
            scanner.close();
            Ui.sayGoodbye();
            System.exit(0);
            return;
        default:
        case INVALID:
            Ui.printInvalidCommand();
            break;
        }
    }

    private static void addTaskToList(CommandType command, String userInput) {
        String[] commandArguments =
                Parser.getCommandArguments(command, userInput);
        Task task;

        switch (command) {
        case TODO:
            task = new Todo(commandArguments[0]);
            break;
        case DEADLINE:
            task = new Deadline(commandArguments[0], commandArguments[1]);
            break;
        case EVENT:
            task = new Event(commandArguments[0], commandArguments[1], commandArguments[2]);
            break;
        default:
            return;
        }

        TaskList.addTask(task);
    }

    private static void deleteTaskFromList(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(CommandType.DELETE, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex) - 1;

        try {
            TaskList.deleteTask(taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }

    private static void handleMarkUnmarkTask(CommandType command, String userInput) {
        boolean isDone = (command == CommandType.MARK);

        String taskIndex =
                Parser.getCommandArguments(command, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            TaskList.changeTaskStatus(isDone, taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
