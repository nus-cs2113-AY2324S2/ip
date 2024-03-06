import java.util.List;

public class Zap {
    private static List<Task> tasks;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public Zap(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = storage.load();
        TaskList taskList = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        ui.printInstructions();
        parser.parseCommands();
        storage.save(tasks);
        ui.printFarewell();
    }
    public static void main(String[] args) {
        new Zap("tasks.txt").run();
    }
}