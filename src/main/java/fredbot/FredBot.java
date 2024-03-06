package fredbot;

import fredbot.exception.EmptyDescriptionException;
import fredbot.exception.UnknownCommandException;
import fredbot.exception.NoMatchesException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class FredBot {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;

    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    private static final String FILE_PATH = "data/fredbot.txt";

    private static final int INDEX_COMMAND_WORD = 0;
    private static final int INDEX_COMMAND_ARGS = 1;

    private static final String SINGULAR_TASK = " task ";
    private static final String PLURAL_TASK = " tasks ";

    private static final int EMPTY_COUNT = 0;
    private static final int SINGULAR_COUNT = 1;

    private static final int NORMAL_TERMINATION = 0;

    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        loadFredBot();
        ui.showWelcomeMessage();
        while (true) {
            String userInput = ui.readUserInput();
            executeCommand(userInput);
            storage.saveFredBot(ui, tasks);
        }
    }

    /**
     * Initialises FredBot.
     * If there is an existing task list, it is loaded.
     * Else, a new task list is created.
     */
    private static void loadFredBot() {
        initTaskList();
        File f = new File(FILE_PATH);
        try {
            storage.loadTaskList(f, ui, tasks);
        } catch (FileNotFoundException e) {
            storage.initSaveFile(f, ui);
        }
    }

    /**
     * Creates a new TaskList class.
     */
    private static void initTaskList() {
        tasks = new TaskList();
    }

    /**
     * Reads the command input.
     * If the command word is not recognised, an error message is shown.
     *
     * @param input User input.
     */
    private static void executeCommand(String input) {
        try {
            extractCommand(input);
        } catch (UnknownCommandException e) {
            ui.showUnknownCommandMessage();
        }
    }

    /**
     * Splits the command word and arguments, then executes based on the command word.
     *
     * @param input User input.
     * @throws UnknownCommandException If the command word is not recognised.
     */
    private static void extractCommand(String input) throws UnknownCommandException {
        final String[] commandWordAndArgs = parser.splitCommandWordAndArgs(input);
        final String commandWord = commandWordAndArgs[INDEX_COMMAND_WORD];
        final String commandArgs = commandWordAndArgs[INDEX_COMMAND_ARGS];

        switch (commandWord) {
        case COMMAND_TODO:
            executeAddTodo(commandArgs);
            break;
        case COMMAND_DEADLINE:
            executeAddDeadline(commandArgs);
            break;
        case COMMAND_EVENT:
            executeAddEvent(commandArgs);
            break;
        case COMMAND_FIND:
            executeFind(commandArgs);
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_MARK:
            executeMark(commandArgs);
            break;
        case COMMAND_UNMARK:
            executeUnmark(commandArgs);
            break;
        case COMMAND_DELETE:
            executeDelete(commandArgs);
            break;
        case COMMAND_EXIT:
            executeExit();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void executeFind(String keyword) {
        try {
            tasks.findTask(keyword, ui);
        } catch (NoMatchesException e) {
            ui.showNoMatches();
        }
    }


    /**
     * Creates a Todo task and adds it to the TaskList.
     * If the description is empty, an error message is shown.
     *
     * @param input Todo details.
     */
    private static void executeAddTodo(String input) {
        try {
            tasks.addTodo(input);
            echoTask();
        } catch (EmptyDescriptionException e) {
            ui.showEmptyDescription();
        }
    }

    /**
     * Creates a Deadline task and adds it to the TaskList.
     * If the description is empty, an error message is shown.
     *
     * @param input Deadline details.
     */
    private static void executeAddDeadline(String input) {
        String[] split = parser.splitDeadlineAndDate(input);
        try {
            tasks.addDeadline(split);
            echoTask();
        } catch (EmptyDescriptionException e) {
            ui.showEmptyDescription();
        } catch (DateTimeParseException e) {
            ui.showWrongDateFormat();
        }
    }

    /**
     * Creates an Event task and adds it to the TaskList.
     * If the description is empty, an error message is shown.
     *
     * @param input Event details.
     */
    private static void executeAddEvent(String input) {
        String[] split = parser.splitEventAndDates(input);
        try {
            tasks.addEvent(split);
            echoTask();
        } catch (EmptyDescriptionException e) {
            ui.showEmptyDescription();
        }
    }

    /**
     * Echoes the task that has just been added back to the user.
     */
    private static void echoTask() {
        ui.showMessageAdd(tasks);
        tasks.increaseCount();
        int count = tasks.getCount();
        String task = (count == SINGULAR_COUNT) ? SINGULAR_TASK : PLURAL_TASK;
        ui.showNumberOfTasks(count, task);
    }

    /**
     * Prints the TaskList to the user.
     */
    private static void executeList() {
        int count = tasks.getCount();
        if (count == EMPTY_COUNT) {
            ui.showEmptyListMessage();
            return;
        }
        ui.showList(tasks.getTaskList(), count);
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber Index of the task.
     */
    private static void executeMark(String taskNumber) {
        int index = parser.getIndex(taskNumber);
        tasks.getTask(index).markAsDone();
        ui.showMarkMessage(tasks.getTaskList(), index);
    }

    /**
     * Marks the specified task as undone.
     *
     * @param taskNumber Index of the task.
     */
    private static void executeUnmark(String taskNumber) {
        int index = parser.getIndex(taskNumber);
        tasks.getTask(index).unmarkAsDone();
        ui.showUnmarkMessage(tasks.getTaskList(), index);
    }

    /**
     * Deletes the specified task off the TaskList.
     *
     * @param taskNumber Index of the task.
     */
    private static void executeDelete(String taskNumber) {
        int index = parser.getIndex(taskNumber);
        ui.showDeleteMessage(tasks.getTaskList(), index);
        tasks.decreaseCount();
        int count = tasks.getCount();
        String task = (count == EMPTY_COUNT) ? SINGULAR_TASK : PLURAL_TASK;
        ui.showNumberOfTasks(count, task);
    }

    /**
     * Closes FredBot.
     */
    private static void executeExit() {
        ui.showGoodbyeMessage();
        System.exit(NORMAL_TERMINATION);
    }
}