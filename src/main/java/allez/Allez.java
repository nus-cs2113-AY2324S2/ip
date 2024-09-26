package allez;

/**
 * Entry point of the Allez Chatbot Application
 * Initialises the application and start interactions with the user.
 */
public class Allez {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;
    private static Command c;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        startBot();
        runCommandsUntilExit();
        exit();
    }

    /**
     * Save the current list of tasks into the save file. Prints out the exit message.
     */
    private static void exit() {
        storage.writeSave(tasks.getTasks());
        Ui.printExit();
    }

    private static void runCommandsUntilExit() {
        boolean hasEnded = false;
        while(!hasEnded) {
            String currentInput = ui.getUserCommand();
            String command =  parser.parseCommand(currentInput);
            hasEnded = executeCommands(command, currentInput);
        }
    }

    /**
     * Set up required objects, load data from the save file if there is any.
     * Prints out the greeting message.
     */
    private static void startBot() {
        ui = new Ui();
        Ui.printGreeting();
        storage = new Storage("./data/data.txt");
        tasks = new TaskList(storage);
        parser = new Parser();
        c = new Command(tasks);
    }

    /**
     * Executes command specified.
     *
     * @param command user command to execute
     * @param line line input from the user
     * @return true if command received is "bye", false otherwise
     */
    private static boolean executeCommands(String command, String line) {
        Ui.printHyphens();

        switch(command){
        case "bye":
            return true;
        case "mark":
            c.markTask(line);
            break;
        case "todo":
            c.createTask(line, TaskType.TODO);
            break;
        case "deadline":
            c.createTask(line, TaskType.DEADLINE);
            break;
        case "event":
            c.createTask(line, TaskType.EVENT);
            break;
        case "list":
            c.printList(tasks.getTasks());
            break;
        case "delete":
            c.deleteTask(line);
            break;
        case "find":
            c.findTask(line);
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }

        return false;
    }

}
