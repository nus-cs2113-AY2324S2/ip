package misty;

import misty.exception.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserUi userUi = new UserUi();
        Parser parser = new Parser(userUi);
        Storage storage = new Storage();
        List taskList = new List(storage, userUi);

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
            userInput = userUi.getUserCommand();
            userUi.printMessageBorder();
            try {
                parser.parseCommand(userInput, taskList);
            } catch (UnknownCommandException e) {
                userUi.printUnknownCommandMessage();
            } catch (InvalidParameterFormatException e) {
                userUi.printErrorInvalidFormat();
            } catch (EmptyParameterException e) {
                userUi.printErrorEmptyParameter();
            } catch (IllegalListIndexException e) {
                userUi.printErrorInvalidId();
            }

            userUi.printMessageBorder();
        }
    }
}
