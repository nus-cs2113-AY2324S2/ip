import commands.Command;
import commands.ExitCommand;
import exceptions.KikuException;
import parsers.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The main class for KikuBot.
 * This class initializes the user interface, storage, and task list,
 * and processes user input until the exit command is received.
 */
public class KikuBot {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private static final String FILE_PATH = "./data/Kiku.txt";
    private static final String HORIZONTAL = "____________________________________________________________";

    /**
     * Constructs a new instance of KikuBot.
     * Initializes the user interface, storage, and loads existing tasks from the storage file (if any).
     * If the storage file is not found, starts with an empty task list.
     *
     * @param filePath The file path of the storage file for tasks.
     */
    public KikuBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the main loop of the application.
     * Continuously reads user commands, parses them, executes them, and displays the results
     * until the exit command is received.
     */
    public void run() {
        ui.greetingMessage();

        boolean isExit = false;
        while(!isExit) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);
                if(command instanceof ExitCommand) {
                    isExit = true;
                }
                System.out.println(HORIZONTAL);
            } catch (KikuException e) {
                ui.errorMessage(e.getMessage());
            }
        }
    }

    /**
     * The entry point of KikuBot.
     * Creates an instance of KikuBot and runs it.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        KikuBot kikuBot = new KikuBot(FILE_PATH);
        kikuBot.run();
    }
}