import java.nio.file.Paths;

import java.io.IOException;
import exceptions.WallybotException;


/**
 * Represents Wallybot, which is a personal assistant chatbot.
 * It allows the user to keep track of and manage their tasks.
 */
public class Wallybot {
    // ATTRIBUTES
    private Tasklist tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    // METHODS
    /**
     * Constructs Wallybot.
     *
     * @param filepath Where data is stored.
     */
    public Wallybot (String filepath) {
        ui = new Ui();
        parser = new Parser();
        tasks = new Tasklist();
        storage = new Storage(filepath, tasks);
    }

    /**
     * Echoes user input.
     */
    public void echo(String input) {
        System.out.println(input);
    }

    /**
     * Runs Wallybot.
     */
    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            try {
                 String[] details = ui.processInput();
                 isRunning = parser.executeCommand(details[0], details[1], tasks);
                 storage.writeFile(tasks.formatWriteData());

            } catch (WallybotException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oopsies, that number does not correspond to a task in your list :O");
            } catch (NumberFormatException e) {
                System.out.println("Shucks...that's not a number :O");
            } catch (IOException e) {
                System.out.println("NO FILE FOUND...UH OH D:");

            } finally {
                ui.showLine();
            }
        }

        ui.showExit();
    }

    /**
     * Main for Wallybot.
     */
    public static void main(String[] args) {
        String filepath = Paths.get(System.getProperty("user.home"), "Documents", "wallybot_data.txt").toString();
        new Wallybot(filepath).run();
    }
}
