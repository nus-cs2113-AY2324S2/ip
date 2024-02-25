import quill.command.Command;
import quill.command.ExitCommand;
import quill.parser.Parser;
import quill.storage.Save;
import quill.task.*;
import quill.ui.TextUi;

/**
 * The Quill Class represents the main application class for Quill, a task management application.
 * It initializes the user interface, task storage and task list.
 * It runs the application loop, managing the user interface, task storage, and task list.
 */
public class Quill {
    private static TextUi ui;
    private static TaskList tasks;

    /**
     * Constructs a Quill object
     * Initializes the user interface and task list.
     */
    public Quill() {
        ui = new TextUi();
        tasks = Save.readFile();
    }

    /**
     * Runs the Quill application.
     * It displays a welcome message, reads user commands, and executes corresponding actions.
     * The application continues running until the user exits.
     */
    public void run() {
        TextUi.showWelcomeMessage();
        Command c;
        do {
            String line = ui.getUserCommand();
            TextUi.showDivider();
            c = Parser.parse(line);
            c.execute(tasks, ui);
            TextUi.showDivider();
        } while (!ExitCommand.isExit(c));
    }

    /**
     * The main method that starts the Quill application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Quill().run();
    }
}
