import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class Battch {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Battch() {
        parser = new Parser();
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
    }

    public void run(String[] args, String botName) {
        ui.greet(botName);
        tasks.newTasks();
        storage.newFile(args);
        parser.taskManager();
        ui.bye();
    }

    public static void main(String[] args) {
        String botName = "Battch";

        Battch battchBot = new Battch();
        battchBot.run(args, botName);
    }
}
