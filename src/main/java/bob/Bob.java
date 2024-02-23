package bob;

import java.util.Scanner;

public class Bob {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    Scanner in;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.generateListOnStartup());

        ui.displayWelcomeMessage();
        in = new Scanner(System.in);
    }
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
    public static void main(String[] args) {
        new Bob("bob.txt").run();
    }
}
