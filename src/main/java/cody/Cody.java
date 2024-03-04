package cody;

public class Cody {

private Storage storage;
private TaskList tasks;
private Ui ui;

    public Cody(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (CodyException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printMessage(tasks.executeCommand(fullCommand));
                isExit = tasks.isExit();
            } catch (CodyException e) {
                ui.printException(e);
            }
        }
        ui.exit();
    }


    public static void main(String[] args) {
        new Cody("data/tasks.txt").run();
    }
}


