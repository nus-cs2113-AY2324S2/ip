import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The Incy class is the main entry point of the application.
 * It handles user input, manages tasks, and interacts with the TaskManager class.
 */

public class Incy {
    /**
     * The main method of the application.
     * It initializes the TaskManager, loads tasks from file, processes user input,
     * saves tasks to file, and handles the application's lifecycle.
     *
     * @param args The command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        ensureDataFolderExists();
        taskManager.loadTasksFromFile();

        printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input)) {
                break;
            }
            processInput(input, taskManager);
        }

        taskManager.saveTasksToFile();
        printGoodbyeMessage();
        scanner.close();
    }

    /**
     * Ensures that the data folder exists, creating it if necessary.
     */

    private static void ensureDataFolderExists() {
        Path dataFolderPath = Paths.get(Constants.DATA_FOLDER);
        if (!Files.exists(dataFolderPath)) {
            try {
                Files.createDirectory(dataFolderPath);
            } catch (IOException e) {
                System.err.println("Failed to create data folder: " + e.getMessage());
            }
        }
    }

    /**
     * Prints the welcome message to the console.
     */
    
    private static void printWelcomeMessage() {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Oi bruv! I'm\n" + Constants.LOGO + Constants.ANSI_CYAN + "Wotcha need from me today?\n" + Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Prints the goodbye message to the console.
     */

    private static void printGoodbyeMessage() {
        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Cya later mate!\n" + Constants.LINE_STRING_BOTTOM);
    }

    /**
     * Processes the user input and delegates the appropriate action to the TaskManager.
     *
     * @param input       The user input string
     * @param taskManager The TaskManager instance
     */

    private static void processInput(String input, TaskManager taskManager) {
        try {
            if ("list".equalsIgnoreCase(input)) {
                taskManager.handleListCommand();
            } else if (input.startsWith("mark ")) {
                taskManager.handleMarkCommand(input, true);
            } else if (input.startsWith("unmark ")) {
                taskManager.handleMarkCommand(input, false);
            } else if (input.startsWith("delete ")) {
                taskManager.handleDeleteCommand(input);
            } else if (input.startsWith("list by ")) {
                taskManager.handleListByDateCommand(input);
            } else if (input.startsWith("find ")) {
                taskManager.handleFindCommand(input);
            } else {
                taskManager.handleAddTask(input);
            }
        } catch (IncyException e) {
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + e.getMessage() + "\n" + Constants.LINE_STRING_BOTTOM);
        }
    }
}
