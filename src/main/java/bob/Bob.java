package bob;

import java.util.Scanner;

/**
 * Represents the main class of the program.
 */
public class Bob {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    Scanner in;

    /**
     * Constructor for the Bob class.
     * @param filePath The file path to the file where the list is stored.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.generateListOnStartup());

        ui.displayWelcomeMessage();
        in = new Scanner(System.in);
    }

    /**
     * Runs the main loop of the program.
     */
    public void run() {

        while (true) {
            String line = in.nextLine();
            String command = line.split(" ")[0];
            try {
                boolean endLoop = Parser.processCommand(command, line, tasks, storage, ui);

                if (endLoop) {
                    break;
                }
            } catch (BobException e) {
                ui.displayErrorMessage(e);
            }
        }
    }

    /**
     * Main method of the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bob("bob.txt").run();
    }
}
