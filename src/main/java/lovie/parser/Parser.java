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
    public static final int COMMAND = 0;
    public static final String SPACE = " ";
    public static final int HALVES = 2;
    public static final String TO_INCLUSION = "Oops! Make sure you include a /to for your event. Here is the format:\n" +
            "event **description** /from **start** /to **end**";
    public static final String FROM_INCLUSION = "Oops! Make sure you include a /from for your event. Here is the format:\n" +
            "event **description** /from **start** /to **end**";
    public static final String BY_INCLUSION = "Oops! Make sure you include a /by for your deadline. Here is the format:\n" +
            "deadline **description** /by **end**";
    public static final String DEADLINE_DESCRIPTION_INCLUSION = "Oops! Make sure you add a description for your deadline! Here is the format:\n" +
            "deadline **description** /by **end**";
    public static final String EVENT_DESCRIPTION_INCLUSION = "Oops! Make sure you add a description for your event! Here is the format:\n" +
            "event **description** /from **start** /to **end**";
    public static final String TODO_DESCRIPTION_INCLUSION = "Oops! Make sure you add a description for your todo! Here is the format:\n" +
            "todo **description**";
    private final String line;
    private final String processedInput;
    private final String firstCommand;
    private final Storage storage;
    private final TaskList tasks;
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
        this.tasks = tasksList;
        this.processedInput = input.toLowerCase().trim();
        this.firstCommand = processedInput.split(SPACE)[COMMAND];
        ui = new Ui();
    }

    /**
     * Sorts the input from the user and calls the appropriate methods.
     *
     * @return A boolean value to indicate if the program should exit.
     */
    public boolean inputSorter() {
        Task newTask;
        switch (firstCommand) {
        case "bye":
            storage.saveTasks(tasks);
            ui.goodbyePrinter();
            return true;
        case "list":
            tasks.printTasks();
            break;
        case "unmark":
            unmarkTaskHelper(line);
            storage.saveTasks(tasks);
            break;
        case "mark":
            markTaskHelper(line);
            storage.saveTasks(tasks);
            break;
        case "delete":
            tasks.deleteTask(line);
            storage.saveTasks(tasks);
            break;
        case "find":
            findHelper(line);
            break;
        case "command":
            ui.commandPrinter();
            break;
        case "event":
            try {
                eventFormatChecker(line);
            } catch (LovieException e) {
                ui.print(e.getMessage());
                break;
            }
            newTask = new Event(line);
            tasks.addTask(newTask);
            ui.addTaskPrinter(newTask);
            storage.saveTasks(tasks);
            break;
        case "deadline":
            try {
                deadlineFormatChecker(line);
            } catch (LovieException e) {
                ui.print(e.getMessage());
                break;
            }
            newTask = new Deadline(line);
            tasks.addTask(newTask);
            ui.addTaskPrinter(newTask);
            storage.saveTasks(tasks);
            break;
        case "todo":
            try {
                todoFormatChecker(line);
            } catch (LovieException e) {
                ui.print(e.getMessage());
                break;
            }
            newTask = new Todo(line);
            tasks.addTask(newTask);
            ui.addTaskPrinter(newTask);
            storage.saveTasks(tasks);
            break;
        default:
            ui.invalidCommandPrinter();
        }
        return false;
    }

    /**
     * Helper method to mark a task.
     *
     * @param input The input from the user.
     */
    public void markTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(SPACE)[1]) - 1;
        if (taskNumber >= tasks.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasks.markTask(taskNumber);
            ui.markTaskPrinter(tasks.get(taskNumber));
        }
    }

    /**
     * Helper method to find a task using user-given keyword.
     *
     * @param input The input from the user.
     */
    public void findHelper(String input) {
        String[] inputParts = input.split(SPACE);
        if (inputParts.length < HALVES) {
            ui.noValidFindPrinter();
        } else {
           String keyword = inputParts[1];
            tasks.find(keyword);
        }
    }

    /**
     * Helper method to unmark a task.
     *
     * @param input The input from the user.
     */
    public void unmarkTaskHelper(String input) {
        int taskNumber = Integer.parseInt(input.split(SPACE)[1]) - 1;
        if (taskNumber >= tasks.getSize() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            tasks.unmarkTask(taskNumber);
            ui.unmarkTaskPrinter(tasks.get(taskNumber));
        }
    }

    /**
     * Checks if the input for todo is in the correct format.
     *
     * @param input The input from the user.
     * @throws LovieException If the input is in the wrong format.
     */
    public void todoFormatChecker(String input) throws LovieException {
        String[] splitUpInput = input.split(SPACE, HALVES);
        if (splitUpInput.length <= 1) {
            throw new LovieException(TODO_DESCRIPTION_INCLUSION);
        }
    }

    /**
     * Checks if the input for deadline is in the correct format.
     *
     * @param input The input from the user.
     * @throws LovieException If the input is in the wrong format.
     */
    public void deadlineFormatChecker(String input) throws LovieException {
        String firstHalf = input.split("/", HALVES)[0].trim();
        String[] firstHalfParts = firstHalf.split(SPACE, HALVES);
        if (firstHalfParts.length <= 1) {
            throw new LovieException(DEADLINE_DESCRIPTION_INCLUSION);
        }
        String[] byParts = input.trim().split("/by", HALVES);
        if (byParts.length <= 1 || byParts[1].trim().isEmpty()) { //what if there are 2 /by methods
            throw new LovieException(BY_INCLUSION);
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
        String[] descriptionParts = splitUpInput.split(SPACE, HALVES);

        if (descriptionParts.length <= 1) {
            throw new LovieException(EVENT_DESCRIPTION_INCLUSION);
        }

        String[] fromParts = input.split("/from", HALVES);
        if (fromParts.length <= 1) {
            throw new LovieException(FROM_INCLUSION);
        }

        String[] fromWords = fromParts[1].split("/to", HALVES);
        if (fromWords[0].trim().isEmpty()) {
            throw new LovieException(FROM_INCLUSION);
        }

        String[] toParts = input.split("/to", HALVES);
        if (toParts.length <= 1) {
            throw new LovieException(TO_INCLUSION);
        }

        String[] toDescription = toParts[1].split("/to", HALVES);
        if (toDescription[0].trim().isEmpty()) {
            throw new LovieException(TO_INCLUSION);
        }
    }
}
