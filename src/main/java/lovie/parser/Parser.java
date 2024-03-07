package lovie.parser;

import lovie.exception.LovieException;
import lovie.file.Storage;
import lovie.task.*;
import lovie.ui.Ui;

public class Parser {
    private String line;
    private String processedInput;
    private String firstCommand;
    private Storage storage;
    private TaskList tasksList;

    private Ui ui;
    public Parser(String input, Storage storage, TaskList tasksList) {
        this.line = input;
        this.storage = storage;
        this.tasksList = tasksList;
        this.processedInput = input.toLowerCase().trim();
        this.firstCommand = processedInput.split(" ")[0];
        ui = new Ui();
    }

    public boolean inputSorter() {
        switch (firstCommand) {
            case "bye":
                storage.saveTasks(tasksList);
                ui.goodbyePrinter();
                return true;
            case "list":
                tasksList.printTasks();
                break;
            case "unmark":
                unmarkTaskHelper(line);
                storage.saveTasks(tasksList);
                break;
            case "mark":
                markTaskHelper(line);
                storage.saveTasks(tasksList);
                break;
            case "delete":
                tasksList.deleteTask(line);
                storage.saveTasks(tasksList);
                break;
            default:
                String taskType = processedInput.split(" ")[0];
                Task newTask;

                // Switch statement to keep track of taskType + default of incorrect input
                switch (taskType) {
                    case "event":
                        try {
                            eventFormatChecker(line);
                        } catch (LovieException e) {
                            ui.print(e.getMessage());
                            break;
                        }
                        newTask = new Event(line);
                        tasksList.addTask(newTask);
                        ui.addTaskPrinter(newTask);
                        storage.saveTasks(tasksList);
                        break;
                    case "deadline":
                        try {
                            deadlineFormatChecker(line);
                        } catch (LovieException e) {
                            ui.print(e.getMessage());
                            break;
                        }
                        newTask = new Deadline(line);
                        tasksList.addTask(newTask);
                        ui.addTaskPrinter(newTask);
                        storage.saveTasks(tasksList);
                        break;
                    case "todo":
                        try {
                            todoFormatChecker(line);
                        } catch (LovieException e) {
                            ui.print(e.getMessage());
                            break;
                        }
                        newTask = new ToDo(line);
                        tasksList.addTask(newTask);
                        ui.addTaskPrinter(newTask);
                        storage.saveTasks(tasksList);
                        break;
                    default:
                        ui.invalidCommandPrinter();
                }
        }
        return false;
    }

    public void markTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasksList.markTask(taskNumber);
            ui.markTaskPrinter(tasksList.get(taskNumber));
        }
    }

    public void unmarkTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        boolean output = true;
        if (taskNumber >= tasksList.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasksList.unmarkTask(taskNumber);
            ui.unmarkTaskPrinter(tasksList.get(taskNumber));
        }
    }

    public void todoFormatChecker(String input) throws LovieException {
        String[] splitUpInput = input.split(" ", 2);
        if (splitUpInput.length <= 1) {
            throw new LovieException("Oops! Make sure you add a description for your todo! Here is the format:\n" +
                    "todo **description**");
        }
    }

    public static void deadlineFormatChecker(String input) throws LovieException {
        String firstHalf = input.split("/", 2)[0].trim();
        String[] splitUpFirstHalf = firstHalf.split(" ", 2);
        if (splitUpFirstHalf.length <= 1) {
            throw new LovieException("Oops! Make sure you add a description for your deadline! Here is the format:\n" +
                    "deadline **description** /by **end**");
        }
        String[] bySplitter = input.trim().split("/by", 2);
        if (bySplitter.length <= 1 || bySplitter[1].trim().isEmpty()) { //what if there are 2 /by methods
            throw new LovieException("Oops! Make sure you include a /by for your deadline. Here is the format:\n" +
                    "deadline **description** /by **end**");
        }
    }

    public static void eventFormatChecker(String input) throws LovieException {
        String splitUpInput = input.split("/from")[0].trim();
        String[] splitUpDescription = splitUpInput.split(" ", 2);
        if (splitUpDescription.length <= 1) {
            throw new LovieException("Oops! Make sure you add a description for your event! Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
        String[] fromSplitter = input.split("/from", 2);
        if (fromSplitter.length <= 1) { //what if there are 2 /from methods
            throw new LovieException("Oops! Make sure you include a /from for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
        String[] fromDescription = fromSplitter[1].split("/to", 2);
        if (fromSplitter.length <= 1 || fromDescription[0].trim().isEmpty()) { //what if there are 2 /from methods
            throw new LovieException("Oops! Make sure you include a /from for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
        String[] toSplitter = input.split("/to", 2);
        if (toSplitter.length <= 1) { //what if there are 2 /from methods
            throw new LovieException("Oops! Make sure you include a /to for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
        String[] toDescription = toSplitter[1].split("/to", 2);
        if (toSplitter.length <= 1 || toDescription[0].trim().isEmpty()) { //what if there are 2 /from methods
            throw new LovieException("Oops! Make sure you include a /to for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
    }
}
