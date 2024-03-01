package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;
import ava.Parser;

import java.util.Scanner;
import java.util.ArrayList;

public class Ava {

    protected final Storage storage;
    protected final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Ava(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(ui);
        parser = new Parser();
        storage.loadFile(tasks);
    }

    public static void main(String[] args) {
        new Ava("./data/ava.txt").run();
    }

    public void run() {
        ui.greet();
        mainProcess();
        ui.exit();
    }

    public void mainProcess() {
        while (!parser.isExit()) {
            String task = ui.getUserCommand();
            ui.printLine();
            parser.parseCommand(task, tasks, storage, ui);
            if (parser.isExit()) {
                return;
            }
            ui.printLine();
        }
    }
}
