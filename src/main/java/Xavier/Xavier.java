package Xavier;

import Exceptions.XavierException;

public class Xavier {
    public static final String FILEPATH = "/Users/jasonlienardi/Documents/CS2113/ip/src/main/java/Xavier/toDoList.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Xavier(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (XavierException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        while (!ui.isExit) {
            ui.readCommand(parser, tasks, ui, storage, FILEPATH);
        }
    }
    public static void main(String[] args) {
        new Xavier(FILEPATH).run();
    }
}

