import ListCommands.SamException;

import java.util.Scanner;

// Main class of the Sam application
public class Sam {
    // File path for storing task data
    private static final String FILE_PATH = "data/sam.txt";

    // Instances of UI, Storage, and TaskList classes
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    // Constructor to initialize the Sam application
    public Sam() {
        ui = new Ui(); // Initialize user interface
        storage = new Storage(FILE_PATH); // Initialize storage for task data
        try {
            taskList = new TaskList(storage.load()); // Load tasks from file into task list
        } catch (SamException e) {
            ui.showLoadingError(); // Display error message if loading tasks fails
            taskList = new TaskList(); // Initialize an empty task list
        }
    }

    // Method to start the Sam application
    public void run() {
        ui.printGreeting(); // Display greeting message
        ui.printTasks(taskList); // Display tasks
        Scanner in = new Scanner(System.in); // Create scanner to read user input
        String line = in.nextLine(); // Read user input line by line

        while (!line.equals("bye")) { // Continue loop until user inputs "bye"
            try {
                ui.printLine(); // Print separator line
                Parser.parseCommand(line, taskList); // Parse and execute user command
                storage.saveTasks(taskList.getTasks()); // Save tasks to file
                ui.printLine(); // Print separator line
                line = in.nextLine(); // Read next user input
            } catch (SamException e) {
                ui.printLine(); // Print separator line
                line = in.nextLine(); // Read next user input
            }
        }
        ui.printFarewell(); // Display farewell message when user exits the application
    }

    // Main method to run the Sam application
    public static void main(String[] args) {
        Sam sam = new Sam(); // Create instance of Sam application
        sam.run(); // Start the Sam application
    }
}
