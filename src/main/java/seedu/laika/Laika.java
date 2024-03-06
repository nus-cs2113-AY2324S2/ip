package seedu.laika;

import seedu.laika.parser.Parser;
import seedu.laika.storage.Storage;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;
import java.io.IOException;

public class Laika {



    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private boolean isConvoOngoing;
    public Laika(String fileName){
        storage = new Storage(fileName);
        parser = new Parser();
        taskList = storage.load();
        isConvoOngoing = true;
    }

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

    public static void main(String[] args) throws IOException {
        new Laika("laika.txt").run();
    }
}
