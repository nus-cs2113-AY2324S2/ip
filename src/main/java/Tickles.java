
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for this iP; this program is called Tickles. Manages deadlines, events, and todo task types.
 */
public class Tickles {

    private Storage storage;
    private Ui ui;

    /**
     * Instantiates the Tickles project by loading in save data if it exists, and creating a user interface instance.
     */
    public Tickles(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        storage.loadTickles();
    }

    /**
     * Begins the program by displaying the Tickles opening and prompting the user for task inputs.
     */
    public void run() {
        ui.displayOpening();
        ui.promptUser();
    }

    public static void main(String[] args) {
        new Tickles("./data/tickles.txt").run();
    }
}
