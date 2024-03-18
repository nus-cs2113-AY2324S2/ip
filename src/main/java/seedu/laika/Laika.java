package seedu.laika;

import seedu.laika.parser.Parser;
import seedu.laika.storage.Storage;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;
import java.io.IOException;

/**
 * Entry point for chatbot Laika.
 * Initializes the application and starts the interaction with the user.
 */
public class Laika {

    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private boolean isConvoOngoing;

    /**
     * Constructor for Laika application.
     * @param fileName the name of the file used for storage of tasklist data.
     */
    public Laika(String fileName){
        storage = new Storage(fileName);
        parser = new Parser();
        taskList = storage.load();
        isConvoOngoing = true;
    }

    /**
     * Runs the program until termination.
     */
    public void run(){
        TextUi.startMessage();
        while (isConvoOngoing) {
            String line = TextUi.getUserCommand();
            isConvoOngoing = parser.parse(line, taskList);
        }
        try {
            storage.save(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextUi.endMessage();
    }

    /**
     * Main entry point of the application.
     * @param args arguments passed to the program.
     */
    public static void main(String[] args) {
        new Laika("laika.txt").run();
    }
}
