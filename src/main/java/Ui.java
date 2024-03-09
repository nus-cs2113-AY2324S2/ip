import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the user interface (UI) for the task management application.
 * It interacts with the user, takes input commands, and interacts with the `TaskList`
 * to manage tasks.
 */
public class Ui {

    private final Scanner scanner;

    private final TaskList taskList;

    /**
     * Constructor for the Ui class.
     *
     * @param taskList the TaskList object to be used by the UI
     */
    public Ui(TaskList taskList) {
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Starts the user interface loop, continuously prompting the user for commands
     * until the user enters "bye" to exit.
     */
    public void start() {
        System.out.println("Hello! I'm Wongster\nWhat can I do for you?\n");

        try {
            Storage.loadTasks(taskList); // Load tasks from file (optional)
        } catch (IOException e) {
            System.out.println("ERROR OCCURRED during loading tasks.");
        }

        while (true) {
            String userInput = scanner.nextLine();
            handleCommand(userInput);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                try {
                    Storage.saveTasks(taskList); // Save tasks to file (optional)
                } catch (IOException e) {
                    System.out.println("ERROR SAVING FILE.");
                }
                break;
            }
        }

        scanner.close();
    }

    private void handleCommand(String userInput) {
        Parser parser = new Parser(userInput);
        Command command = parser.parseCommand();

        if (command != null) {
            command.execute(taskList);
        } else {
            if (!userInput.equalsIgnoreCase("bye")) {
                System.out.println("Please input a proper task!");
                System.out.println("Help: Tasks start with todo, deadline or event.");
            }
        }
    }
}
