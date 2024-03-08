package lovie.parser;

import lovie.exception.LovieException;
import lovie.file.Storage;
import lovie.task.TaskList;
import lovie.task.Task;
import lovie.task.Todo;
import lovie.task.Event;
import lovie.task.Deadline;
import lovie.ui.Ui;

/**
 * Represents the parser of the program.
 */
public class Parser {
    private final String line;
    private final String processedInput;
    private final String firstCommand;
    private final Storage storage;
    private final TaskList tasksList;

    private final Ui ui;

    /**
     * Constructor for Parser class.
     *
     * @param input The input from the user.
     * @param storage The storage object.
     * @param tasksList The task list object.
     */
    public Parser(String input, Storage storage, TaskList tasksList) {
        this.line = input;
        this.storage = storage;
        this.tasksList = tasksList;
        this.processedInput = input.toLowerCase().trim();
        this.firstCommand = processedInput.split(" ")[0];
        ui = new Ui();
    }

    /**
     * Sorts the input from the user and calls the appropriate methods.
     *
     * @return A boolean value to indicate if the program should exit.
     */
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
                        newTask = new Todo(line);
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

    /**
     * Helper method to mark a task.
     *
     * @param input The input from the user.
     */
    public void markTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasksList.markTask(taskNumber);
            ui.markTaskPrinter(tasksList.get(taskNumber));
        }
    }

    /**
     * Helper method to unmark a task.
     *
     * @param input The input from the user.
     */
    public void unmarkTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasksList.unmarkTask(taskNumber);
            ui.unmarkTaskPrinter(tasksList.get(taskNumber));
        }
    }

    /**
     * Checks if the input for todo is in the correct format.
     *
     * @param input The input from the user.
     * @throws LovieException If the input is in the wrong format.
     */
    public void todoFormatChecker(String input) throws LovieException {
        String[] splitUpInput = input.split(" ", 2);
        if (splitUpInput.length <= 1) {
            throw new LovieException("Oops! Make sure you add a description for your todo! Here is the format:\n" +
                    "todo **description**");
        }
    }

    /**
     * Checks if the input for deadline is in the correct format.
     *
     * @param input The input from the user.
     * @throws LovieException If the input is in the wrong format.
     */
    public void deadlineFormatChecker(String input) throws LovieException {
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

    /**
     * Checks if the input for event is in the correct format.
     *
     * @param input The input from the user.
     * @throws LovieException If the input is in the wrong format.
     */
    public void eventFormatChecker(String input) throws LovieException {
        String splitUpInput = input.split("/from")[0].trim();
        String[] splitUpDescription = splitUpInput.split(" ", 2);

        if (splitUpDescription.length <= 1) {
            throw new LovieException("Oops! Make sure you add a description for your event! Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }

        String[] fromSplitter = input.split("/from", 2);
        if (fromSplitter.length <= 1) {
            throw new LovieException("Oops! Make sure you include a /from for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }

        String[] fromDescription = fromSplitter[1].split("/to", 2);
        if (fromDescription[0].trim().isEmpty()) {
            throw new LovieException("Oops! Make sure you include a /from for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }

        String[] toSplitter = input.split("/to", 2);
        if (toSplitter.length <= 1) {
            throw new LovieException("Oops! Make sure you include a /to for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }

        String[] toDescription = toSplitter[1].split("/to", 2);
        if (toDescription[0].trim().isEmpty()) {
            throw new LovieException("Oops! Make sure you include a /to for your event. Here is the format:\n" +
                    "event **description** /from **start** /to **end**");
        }
    }
}
