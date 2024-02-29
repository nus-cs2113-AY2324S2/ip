package hachi;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.parser.Parser;
import hachi.storage.Storage;
import hachi.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * This file represents the main class of the chatbot called Hachi
 * and is also the entry point of the program.
 *
 * @author clarencepohh
 * @version 06/02/2024
 */

public class Hachi {
    private static Ui ui;
    private static TaskList tasksList;
    private static Storage storage;


    /**
     * The constructor for Hachi class.
     * Initializes the required Ui, Storage and TaskList classes.
     *
     * @param filePath The file path for save data of the user's task list.
     */
    
    public Hachi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TaskList(storage.load());
        } catch (HachiException | FileNotFoundException e) {
            ui.printFileLoadingError();
        }
    }


    /**
     * The main program that starts the chatbot.
     * Prints to the console for the user to read its messages.
     * Greets the user and awaits user input.
     * <p>
     * Chatbot can:
     * <p>1. retrieve list of tasks with user input "list"
     * <p>2. mark or unmark tasks complete with user input "mark #tasknumber"
     * <p>3. say goodbye to the user with user input "bye" or "goodbye"
     * <p>4. add a to-do to the list of task with "todo <event name>"
     * <p>5. add a deadline to the list of task with "deadline <event name> /by <by date>"
     * <p>6. add an event to the list of task with "event <event name> /from <start date> /to <end date>"
     * <p>7. retrieve a list of chatbot commands with "help"
     *
     */

    public void runHachi() {
        Parser parser = new Parser(ui, tasksList);

        ui.spacerInsert();
        ui.printGreetingMessage();
        ui.printHelpMessage();
        storage.initializeData();

        String command = null;

        do {
            try {
                String userInput = ui.getUserInput();
                String cleanedInput = ui.cleanUserInput(userInput);
                String firstWord;
                int indexOfSpace = cleanedInput.indexOf(" ");
                firstWord = parser.getFirstWordOfInput(indexOfSpace, cleanedInput);

                command = parser.processUserCommand(firstWord, cleanedInput, userInput);
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }
            
            ui.spacerInsert();
        } while (!Objects.equals(command, "BYE"));

        try {
            storage.saveHandler();
        } catch (IOException e) {
            System.out.println("There was an error saving tasks.");
        }
    }

    public static void main(String[] args) {
        new Hachi("hachidata/hachidata.txt").runHachi();
    }
}
