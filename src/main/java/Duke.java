import Event.TaskList;
import RuntimeSupport.Ui;
import RuntimeSupport.DukeException;
import RuntimeSupport.Storage;
import RuntimeSupport.Parser;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommand();
            isExit = Parser.execution(command, tasks, ui, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}