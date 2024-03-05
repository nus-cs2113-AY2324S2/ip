public class Mavis {

    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./data/mavis.txt";


    public Mavis(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (MavisException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Mavis(FILE_PATH).run();
    }
}
