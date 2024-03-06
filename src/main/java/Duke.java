public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loading_error();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.read_input();
                ui.line();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.getTasks());

            } catch (DukeException e) {
                ui.error(e.getMessage());
            } finally {
                ui.line();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("out/tasks.txt").run();
    }
}



