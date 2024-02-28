import junbot.common.TaskList;
import junbot.error.InvalidInputException;
import junbot.error.JunBotException;
import junbot.parser.Parser;
import junbot.storage.Storage;
import junbot.tasks.Task;
import junbot.ui.Ui;

import java.io.IOException;

public class JunBot {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    /**
     * Constructs a JunBot object with the specified filepath.
     * Initializes the user interface, storage, parser, and task list.
     * If an exception occurs during initialization, an empty task list is created.
     *
     * @param filePath The filepath of the storage file.
     */
    public JunBot(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            parser = new Parser();
            tasks = new TaskList(storage.load());
        } catch (JunBotException | IOException | InvalidInputException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Adds an event task with the given description to the task list.
     * Prints success message after adding the task and updates the storage file.
     *
     * @param description The description of the event task.
     * @throws InvalidInputException If the input is not in the correct format
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void addEvent(String description) throws InvalidInputException, IOException {
        Task eventTask = tasks.addEvent(description);

        ui.printDivider();
        ui.printAddSuccessMessage(eventTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());

    }

    /**
     * Adds a deadline task with the given description to the task list.
     * Prints success message after adding the task and updates the storage file.
     *
     * @param description The description of the deadline task.
     * @throws InvalidInputException If the input is not in the correct format
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void addDeadline(String description) throws InvalidInputException, IOException {
        Task deadlineTask = tasks.addDeadline(description);

        ui.printDivider();
        ui.printAddSuccessMessage(deadlineTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());

    }

    /**
     * Adds a todo task with the given description to the task list.
     * Prints success message after adding the task and updates the storage file.
     *
     * @param description The description of the todo task.
     * @throws InvalidInputException If the input is invalid.
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void addTodo(String description) throws InvalidInputException, IOException {
        Task todoTask = tasks.addTodo(description);

        ui.printDivider();
        ui.printAddSuccessMessage(todoTask);
        ui.printNumTasks(tasks.getTasksList());
        ui.printDivider();
        storage.updateFile(tasks.getTasksList());

    }

    /**
     * Checks if the given command represents a valid list position.
     * This method parses the full command of the user to obtain inputted list position, and determines
     * the validity of the list position
     *
     * @param command The command input by the user.
     * @return True if the list position is < size of tasks list and > 0, false otherwise.
     */
    public static boolean isValidListPosition(String command) {
        if (command == null) {
            return false;
        }
        try {
            int listPosition = Integer.parseInt(command);
            return listPosition <= tasks.getSize() && listPosition > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Unmarks a task in the task list based on the given command.
     * This method parses command from user input and unmarks the task corresponding to the obtained list
     * number.
     * Prints success message after unmarking the task and updates the storage file.
     *
     * @param command The full command input by the user.
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void unmarkTaskInList(String command) throws IOException {
        String listNumber = parser.removeCommandIndicator(command,"unmark");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToUnmark = tasks.unmarkTaskInList(taskArrayIndex);

        ui.printUnmarkSuccessMessage(taskToUnmark);
        storage.updateFile(tasks.getTasksList());

    }
    /**
     * Marks a task in the task list based on the given command.
     * This method parses command from user input and marks the task corresponding to the obtained list
     * number.
     * Prints success message after marking the task and updates the storage file.
     *
     * @param command The full command input by the user.
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void markTaskInList(String command) throws IOException {
        String listNumber = parser.removeCommandIndicator(command, "mark");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToMark = tasks.markTaskInList(taskArrayIndex);

        ui.printMarkSuccessMessage(taskToMark);
        storage.updateFile(tasks.getTasksList());

    }

    /**
     * Prints the list of tasks in the task list. If the task list is empty, only the header is printed
     */
    public static void printList() {
        int taskNumber = 1;

        ui.printDivider();
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.getSize(); i++){
            System.out.print( taskNumber + ". ");
            System.out.println(tasks.getTasksList().get(i));
            taskNumber += 1;
        }
        ui.printDivider();
    }

    /**
     * Deletes a task from the task list based on the given command.
     * This method parses the full user input and deletes the corresponding task based on the obtained
     * list number. Prints success message after deleting the task and updates the storage file.
     *
     * @param command The command input by the user.
     * @throws IOException If an I/O error occurs while updating the storage file.
     */
    public static void deleteTask(String command) throws IOException {
        String listNumber = parser.removeCommandIndicator(command, "delete");
        int taskArrayIndex = parser.convertToArrayIndex(listNumber);

        if (!isValidListPosition(listNumber)){
            System.out.println("Invalid List Number");
            return;
        }

        Task taskToDisplay = tasks.getTasksList().get(taskArrayIndex);
        tasks.getTasksList().remove(taskArrayIndex);

        ui.printDeleteSuccessMessage(taskToDisplay);
        storage.updateFile(tasks.getTasksList());

    }


    public static void findTasks(String command) throws IOException {
        String keyword = parser.removeCommandIndicator(command, "find");
        tasks.printMatchingTasks(keyword);
    }


    /**
     * Handles different types of commands based on the command type.
     * Executes corresponding functions for each command.
     *
     * @param command The command type extracted from user input.
     * @param userInput The full user input string.
     * @throws IOException If an I/O error occurs during task handling.
     * @throws InvalidInputException If the user input is invalid for any of the relevant functions
     */
    public static void handleCommands(String command, String userInput) throws IOException, InvalidInputException {
        switch (command) {
        case "list":
            printList();
            break;
        case "mark":
            markTaskInList(userInput);
            break;
        case "unmark":
            unmarkTaskInList(userInput);
            break;
        case "todo":
            addTodo(userInput);
            break;
        case "deadline":
            addDeadline(userInput);
            break;
        case "event":
            addEvent(userInput);
            break;
        case "delete":
            deleteTask(userInput);
            break;
        case "find":
            findTasks(userInput);
            break;
        default:
            System.out.println("Enter a valid command");
            break;
        }
    }

    /**
     * Handles user input until the "bye" command is received.
     * Reads user input from the UI, parses the command, and executes corresponding actions.
     * Continuously prompts the user for input until the "bye" command is entered.
     *
     * @throws IOException If an I/O error occurs while reading input.
     */
    public static void handleUserInput() throws IOException {

        String userInput = ui.readCommand();

        while (!userInput.equals("bye")) {
            String command = parser.getCommandIndicator(userInput);
            try {
                handleCommands(command, userInput);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            userInput = ui.readCommand();
        }

    }

    /**
     * The main method of the application.
     * Initializes the JunBot, prints a welcome message, handles user input, and prints a goodbye message.
     *
     * @param args The command-line arguments (not used).
     * @throws IOException If an I/O error occurs while handling user input.
     * @throws InvalidInputException If the user input is invalid from the relevant function
     */
    public static void main(String[] args) throws IOException, InvalidInputException {
        String FILEPATH = "./data/tasks.txt";
        new JunBot(FILEPATH);
        ui.printWelcomeMessage();
        handleUserInput();
        ui.printGoodbyeMessage();
    }
}
