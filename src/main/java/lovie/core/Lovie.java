package lovie.core;

import lovie.file.Storage;
import lovie.parser.Parser;
import lovie.task.*;
import lovie.ui.Ui;
import java.util.Scanner;

public class Lovie {
    private static final String FILEPATH = "data/lovie.txt";
    private static Storage storage;
    private static Ui ui;
    private static TaskList tasksList;

    public Lovie(String filepath) {
        storage = new Storage(FILEPATH);
        ui = new Ui();
        tasksList = storage.loadTasks();
    }

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
    public static void main(String[] args) {
        new Lovie("data/lovie.txt").run();
    }

}

