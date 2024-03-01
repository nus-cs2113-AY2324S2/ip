package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Ava {

    protected final Storage storage;
    protected final TaskList tasks;
    private final Ui ui;

    public Ava(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
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
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            String task = in.nextLine();
            if (task.equals("bye")) {
                isExit = true;
                continue;
            } else if (task.equals("list")) {
                tasks.listTask();
                continue;
            } else if (task.contains("mark")) {
                tasks.markTask(task);
                storage.saveTasks(tasks);
                continue;
            } else if (task.startsWith("todo")) {
                try {
                    tasks.addTask(task, "todo");
                } catch (EmptyTaskNameException e) {
                    ui.printEmptyTaskNameExceptionMessage("todo");
                    continue;
                }
            } else if (task.startsWith("deadline")) {
                try {
                    try {
                        tasks.addTask(task, "deadline");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.printDateFormatExceptionMessage();
                        continue;
                    }
                } catch (EmptyTaskNameException e) {
                    ui.printEmptyTaskNameExceptionMessage("deadline");
                    continue;
                }
            } else if (task.startsWith("event")) {
                try {
                    try {
                        tasks.addTask(task, "event");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.printDateFormatExceptionMessage();
                        continue;
                    }
                } catch (EmptyTaskNameException e) {
                    ui.printEmptyTaskNameExceptionMessage("event");
                    continue;
                }
            } else if (task.startsWith("delete")) {
                tasks.deleteTask(task);
                storage.saveTasks(tasks);
                continue;
            } else {
                ui.printUnknownCommandExceptionMessage();
                continue;
            }
            ui.printAfterAddingTask(tasks);
            storage.saveTasks(tasks);
        }
    }
}
