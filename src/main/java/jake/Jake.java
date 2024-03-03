package jake;

import jake.parser.Parser;
import jake.storage.Storage;
import jake.task.TaskList;
import jake.ui.Ui;

public class Jake {
    static final String savedTaskFilePath = "./src/main/java/jake/data/tasks.txt";
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage(savedTaskFilePath);
    private static Parser parser = new Parser(storage, tasks);

    public static void main(String[] args) throws JakeException {
        storage.loadTasks(tasks);
        ui.showGreeting();
        parser.readInput();
    }
}