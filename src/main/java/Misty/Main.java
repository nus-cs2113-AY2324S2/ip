package misty;

import java.io.FileNotFoundException;
import java.io.IOException;
import misty.command.Command;
import misty.data.TaskList;
import misty.data.exception.CorruptedFileException;
import misty.data.exception.IllegalListIndexException;
import misty.data.exception.InvalidParameterFormatException;
import misty.data.exception.UnknownCommandException;
import misty.parser.Parser;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Runs chatbot application.
 */
public class Main {
    private UserUi userUi;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialises core objects and creates/loads save file.
     */
    public Main() {
        userUi = new UserUi();
        parser = new Parser(userUi);
        storage = new Storage();
        taskList = new TaskList(storage, userUi);

        try {
            storage.createFiles();
        } catch (IOException e) {
            userUi.printErrorIO();
        } catch (SecurityException e) {
            userUi.printErrorSecurity();
        }

        try {
            storage.loadData(taskList);
        } catch (FileNotFoundException e) {
            userUi.printErrorFileNotFound();
        } catch (CorruptedFileException e) {
            userUi.printErrorCorruptedFile();
        }
    }

    /**
     * Executes main function of chatbot.
     */
    public void run() {
        userUi.printWelcomeMessage();
        String userInput;

        while (true) {
            try {
                userInput = userUi.getUserCommand();
                userUi.printMessageBorder();
                Command c = parser.parseCommand(userInput, taskList);
                c.execute(taskList, storage, userUi);
            } catch (UnknownCommandException e) {
                userUi.printUnknownCommandMessage();
            } catch (InvalidParameterFormatException e) {
                userUi.printErrorInvalidFormat();
            } catch (IllegalListIndexException e) {
                userUi.printErrorInvalidId();
            }

            userUi.printMessageBorder();
        }
    }

    /**
     * Starts chatbot.
     *
     * @param args Command line arguments - Not needed.
     */
    public static void main(String[] args) {
        new Main().run();
    }
}