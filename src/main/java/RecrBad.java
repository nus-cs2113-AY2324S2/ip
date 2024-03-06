import helperFunctions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecrBad { //TODO more OOP

    // private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public RecrBad() { // String FILE_PATH
        ui = new Ui();
        // storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList();
        } catch (InvalidParamsException e) {
            ui.showLoadingError(e.getMessage());
            // tasks = new TaskList();
        }
    }

    public void run() {
        ui.readInput(tasks);
    }

    /**
     * Input
     * [any text]           to addToList,
     * list                 to displayList,
     * mark/unmark [index]  to mark item
     */
    public static void main(String[] args) {
        // String FILE_PATH = "saveFile.txt";
        new RecrBad().run();

        // ui
        // parser
        // taskList
        // storage (add filepath to RecrBad())
    }
}

