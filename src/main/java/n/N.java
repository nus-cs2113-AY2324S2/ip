package n;

import n.exceptions.EmptyTaskDescriptionException;

import java.io.FileNotFoundException;

public class N {
    private Ui ui;
    public N() {
        ui = new Ui();
    }
    public void run() throws FileNotFoundException, EmptyTaskDescriptionException {
        this.ui = new Ui();
        ui.printWelcome();
        Storage.loadTaskList();
        ui.handleMessages();
    }
    public static void main(String[] args) throws FileNotFoundException, EmptyTaskDescriptionException {
        new N().run();
    }
}
