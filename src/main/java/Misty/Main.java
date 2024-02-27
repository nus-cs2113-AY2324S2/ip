package misty;

import misty.command.Command;
import misty.data.TaskList;
import misty.data.exception.*;
import misty.parser.Parser;
import misty.storage.Storage;
import misty.ui.UserUi;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserUi userUi = new UserUi();
        Parser parser = new Parser(userUi);
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage, userUi);

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
}
