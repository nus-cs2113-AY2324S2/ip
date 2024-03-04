package seedu.laika;

import seedu.laika.parser.Parser;
import seedu.laika.storage.Storage;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;
import java.io.IOException;

public class Laika {



    private Storage storage;
    private TaskList tasks;
    private TextUi ui;
    private Parser parser;
    private boolean isConvoOngoing;
    public Laika(String fileName){
        ui = new TextUi();
        storage = new Storage(fileName);
        parser = new Parser();
        tasks = storage.load();
        isConvoOngoing = true;
    }

    public void run(){
        ui.startMessage();
        while (isConvoOngoing) {
            String line = ui.getUserCommand();
            isConvoOngoing = parser.parse(line, tasks);

        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ui.endMessage();
    }

    public static void main(String[] args) throws IOException {
        new Laika("laika.txt").run();
    }
}
