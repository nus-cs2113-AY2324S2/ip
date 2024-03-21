import InvalidInputExceptions.InvalidInputException;
import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main executable class that controls program flow
 */
public class CheeseBot {
    public final static DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final static String inFilePath = "./data/CheeseBot.txt";
    private final static String outFilePath = "./data/temp.txt";
    private final static File inFile = new File(inFilePath);
    private final static File outFile = new File(outFilePath);
    protected final static TasksList TASKS_LIST = new TasksList();
    private static final Parser PARSER = new Parser();
    protected static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage();


    private void botAction(String[] arguments) throws InvalidInputException {
        String command = arguments[0];

        switch (command) {
        case "list":
            TASKS_LIST.listTasks();
            break;

        case "mark":
            TASKS_LIST.mark(arguments, true);
            break;

        case "unmark":
            TASKS_LIST.mark(arguments, false);
            break;

        case "help":
            UI.printCommandList();
            break;

        case "delete":
            Task deletedTask = TASKS_LIST.delete(arguments);
            UI.printDeleted(deletedTask, TASKS_LIST.getNumberOfTasks());
            break;

        case "find":
            TASKS_LIST.find(arguments);
            break;

        default:
            addTask(arguments);
        }
    }

    private void inputLoop() {
        while (true) {
            try {
                String input = UI.printInputPrompt();
                UI.printDivider();
                String[] arguments = PARSER.validateAndParseInput(input);
                String command = arguments[0];
                if (PARSER.isBye(command)) {
                    break;
                }
                botAction(arguments);
                UI.printDivider();
            } catch (InvalidInputException e) {
                // No action required. Just catch the exception.
            }
        }
    }

    private void run() {
        try {
            STORAGE.readFromInputFile(inFile);
        } catch (FileNotFoundException e) {
            STORAGE.createFile(inFile);
        }
        STORAGE.createFile(outFile);
        UI.printGreeting();
        UI.printDivider();
        inputLoop();
        STORAGE.storeData(inFile, outFile);
        UI.printFarewell();
    }

    public static void main(String[] args) {
        CheeseBot cheeseBot = new CheeseBot();
        cheeseBot.run();
    }
  
    /**
     * Adds a task of a specific type (Todo, Deadline or Event) using the arguments supplied.
     *
     * @param arguments The required parsed arguments for each specific task type.
     */
    public static void addTask(String[] arguments) {
        String command = arguments[0];
        String taskName = arguments[1];

        switch (command) {
        case "todo":
            TASKS_LIST.addTask(new Todo(taskName));
            break;

        case "deadline":
            LocalDateTime by = LocalDateTime.parse(arguments[2], INPUT_FORMAT);
            TASKS_LIST.addTask(new Deadline(taskName, by));
            break;

        case "event":
            LocalDateTime start = LocalDateTime.parse(arguments[2], INPUT_FORMAT);
            LocalDateTime end = LocalDateTime.parse(arguments[3], INPUT_FORMAT);
            TASKS_LIST.addTask(new Event(taskName, start, end));
            break;
        }
        UI.printAdded(taskName, TASKS_LIST.getNumberOfTasks());
    }
}
