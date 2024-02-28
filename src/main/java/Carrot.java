import java.util.Scanner;
import java.util.ArrayList;

public class Carrot {
    private static ArrayList<Task> listOfTasks = Storage.loadListOfTasks();

    public static void main(String[] args) {
        Ui.greetUser();
        Ui.printHelpCommand();

        while (true) {
            String userInput = Ui.readUserInput();

            CommandType userCommand = CommandManager.getCommandType(userInput);
            processUserCommand(userCommand, userInput, Ui.scanner);
        }
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            CommandType userCommand = CommandManager.getCommandType(userInput);

            processUserCommand(userCommand, userInput, Ui.scanner);
        }
    }

    private static void processUserCommand(CommandType userCommand, String userInput, Scanner scanner) {
        switch (userCommand) {
        case HELP:
            Ui.printHelpCommand();
            break;
        case LIST:
            Ui.printListItems(listOfTasks);
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
                CommandManager.getCommandArguments(command, userInput);
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

        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);
        Ui.printAddedTask(task, listOfTasks);
    }

    private static void deleteTaskFromList(String userInput) {
        String taskIndex =
                CommandManager.getCommandArguments(CommandType.DELETE, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex) - 1;

        try {
            Task taskToRemove = listOfTasks.get(taskIndexInt);
            listOfTasks.remove(taskIndexInt);
            Storage.writeAllTasksToStorage(listOfTasks);

            Ui.printDeletedTask(taskToRemove, listOfTasks);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }

    private static void handleMarkUnmarkTask(CommandType command, String userInput) {
        boolean isDone = (command == CommandType.MARK);

        String taskIndex =
                CommandManager.getCommandArguments(command, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            changeTaskStatus(isDone, taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }

    private static void changeTaskStatus(boolean isDone, int taskIndex) {
        // listOfTasks is indexed from 0.
        // taskIndex is indexed from 1, as how the user sees the list is ordered
        Task task = listOfTasks.get(taskIndex - 1);

        task.setStatus(isDone);
        Storage.writeAllTasksToStorage(listOfTasks);
        Ui.printChangedTaskStatus(isDone, task);
    }
}
