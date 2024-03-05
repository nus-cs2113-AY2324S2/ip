package bossman;

import java.io.IOException;
import java.util.Scanner;
import bossman.command.Command;
import bossman.storage.Storage;
import bossman.exceptions.BossManExceptions;
import bossman.parser.Parser;
import bossman.task.TaskList;
import bossman.ui.Ui;

/**
 * BossMan is a CLI task management assistant. It is built as part of AY2324 S2 CS2113 iP.
 * BossMan allows users to track and manage their tasks, and has persistent data storage.
 * BossMan can track todos, deadlines, events.
 */
public class BossMan {
    private final Scanner SCANNER;
    private final TaskList TASK_LIST;
    private final Storage DATA_STORAGE;
    private boolean isExit;

    /**
     * Constructs a BossMan instance.
     * Initializes the Scanner, TaskList, and Storage objects.
     *
     * @throws IOException if an I/O error occurs when accessing the data storage
     */
    public BossMan() throws IOException {
        this.DATA_STORAGE = new Storage();
        this.SCANNER = new Scanner(System.in);
        this.TASK_LIST = DATA_STORAGE.TASK_LIST;
        this.isExit = false;
    }

    /**
     * Starts the BossMan chatbot application.
     * Displays a greeting message to the user, accepts user input,
     * processes user commands, and saves tasks to file upon exit.
     *
     * @throws IOException if an I/O error occurs when accessing the data storage
     */
    public void startChat() throws IOException {
        Ui.greetUser();

        while (!isExit) {

            Ui.promptUserInput();

            String userInput = SCANNER.nextLine();

            //execute user command if it is valid else throw exception
            //save tasks to file after each command
            try {
                Command userCommand = Parser.determineCommand(TASK_LIST, userInput);
                userCommand.execute();
                isExit = userCommand.isExit();
                DATA_STORAGE.saveTasksToFile();
            } catch (BossManExceptions | IOException e) {
                Ui.printMessageWithSepNewLine(e.getMessage());
            }
        }

        DATA_STORAGE.saveTasksToFile();

        Ui.sayGoodbye();
    }
}