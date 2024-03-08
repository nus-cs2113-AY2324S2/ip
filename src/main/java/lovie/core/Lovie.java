package lovie.core;

import lovie.file.Storage;
import lovie.parser.Parser;
import lovie.task.TaskList;
import lovie.ui.Ui;
import java.util.Scanner;

/**
 * The main class of the program.
 */
public class Lovie {
    private static final String FILEPATH = "data/lovie.txt";
    private static Storage storage;
    private static Ui ui;
    private static TaskList tasksList;

    /**
     * Constructor for Lovie.
     */
    public Lovie() {
        storage = new Storage(FILEPATH);
        ui = new Ui();
        tasksList = storage.loadTasks();
    }

    /**
     * Runs the program.
     */
    public void run() {
        String input;
        Scanner inputGetter = new Scanner(System.in);
        ui.introductionPrinter();
        boolean shouldExit = false;
        while (!shouldExit) {
            System.out.print("\t");
            input = inputGetter.nextLine();
            Parser parser = new Parser(input, storage, tasksList);
            shouldExit = parser.inputSorter();
        }
    }

    /**
     * Main method.
     *
     * @param args The arguments from the command line.
     */
    public static void main(String[] args) {
        new Lovie().run();
    }

}

