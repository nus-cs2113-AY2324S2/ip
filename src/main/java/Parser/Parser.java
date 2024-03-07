package Parser;

import TaskList.TaskList;
import exceptions.MissingDeadlineException;
import exceptions.MissingStartException;
import exceptions.EmptyTaskException;
import exceptions.ArrayListOutOfBoundsException;
import exceptions.UnknownInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Storage.UploadData.updateFile;
import static Ui.Ui.showHelp;
import static Ui.Ui.showLine;
import static Ui.Ui.printRemainingTaskNumber;
import static Ui.Ui.printError;
import static Ui.Ui.closeApp;
import static Ui.Ui.printChangesNotSavedMessage;
import static Ui.Ui.printDeleteMessage;
import static Ui.Ui.printMarkedAsDoneMessage;
import static Ui.Ui.printMarkedAsUndoneMessage;
import static Ui.Ui.printTaskAddedMessage;
import static Ui.Ui.printTaskList;
import static Ui.Ui.printUnknownInputMessage;
import static Ui.Ui.printMatchingTasks;
import static Ui.Ui.printNoIndexError;

/**
 * Represents a utility class for parsing user commands based on actions relevant to the TaskList.
 * This class provides methods for creating, deleting, marking/unmarking, and listing tasks based on user input.
 */
public class Parser {
    static TaskList tasks;
    static String filepath;
    static final int CONVERT_TO_ZERO_INDEXING = 1;
    static final int SIZE_OF_TODO_COMMAND = 5;
    static final int SIZE_OF_BY_PARAM = 4;
    static final int SIZE_OF_FROM_PARAM = 6;
    static final int FIRST_ELEMENT = 0;
    static final int SIZE_OF_DELETE_PARAM = 7;
    static final int SIZE_OF_SPACING = 1;

    /**
     * Constructs a Parser object with the specified file path and TaskList.
     *
     * @param filepath The file path of the storage
     * @param tasks    The TaskList to be managed
     */
    public Parser(String filepath, TaskList tasks) {
        Parser.filepath = filepath;
        Parser.tasks = tasks;
    }

    /**
     * Prints confirmation messages after adding a task. The task added is shown first,
     * before the total number of tasks is shown.
     */
    private static void echo() {
        printTaskAddedMessage(tasks.get(tasks.size() - CONVERT_TO_ZERO_INDEXING));
        printRemainingTaskNumber(tasks.size());
    }

    /**
     * Saves tasks to the storage file and closes the application.
     */
    private static void sayBye() {
        try {
            updateFile(filepath, tasks);
        } catch (IOException e) {
            printChangesNotSavedMessage();
        }
        closeApp();
    }

    /**
     * Creates a new Todo based on the input string.
     *
     * @param input The input string containing the task description
     */
    public static void createTodo(String input) {
        try {
            String todo = input.substring(input.indexOf("todo ") + SIZE_OF_TODO_COMMAND);
            if (todo.isBlank()) {
                throw new EmptyTaskException();
            }
            ToDo newEntry = new ToDo(todo);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException e) {
            printError(e.getMessage());
        }

    }

    /**
     * Creates a new Deadline based on the input string.
     *
     * @param input The input string containing the task description and deadline
     */
    private static void createDeadline(String input) {
        try {
            String description = input.replaceFirst("deadline ", "");
            int by = description.indexOf("/");
            if (by == -1) {
                throw new MissingDeadlineException();
            }
            String deadline = description.substring(FIRST_ELEMENT, by - CONVERT_TO_ZERO_INDEXING);
            if (deadline.isBlank()) {
                throw new EmptyTaskException();
            }
            String date = description.substring(by + SIZE_OF_BY_PARAM);
            Deadline newEntry = new Deadline(deadline, date);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException | MissingDeadlineException e) {
            printError(e.getMessage());
        }
    }

    /**
     * Creates a new Event based on the input string.
     *
     * @param input The input string containing the task description, start date, and end date
     */
    private static void createEvent(String input) {
        try {
            String description = input.replaceFirst("event ", "");
            int from = description.indexOf("/from");
            if (from == -1) {
                throw new MissingStartException();
            }
            Event newEntry = getEvent(description, from);
            tasks.add(newEntry);
            echo();
        } catch (MissingStartException | MissingDeadlineException | EmptyTaskException e) {
            printError(e.getMessage());
        }
    }

    /**
     * Creates a new Event object based on the description and start date index.
     *
     * @param description The description of the event
     * @param from        The index of the start date
     * @return The Event object
     * @throws MissingDeadlineException If the end date is missing
     * @throws EmptyTaskException       If the description is empty
     */
    private static Event getEvent(String description, int from) throws MissingDeadlineException, EmptyTaskException {
        int by = description.indexOf("/to");
        if (by == -1) {
            throw new MissingDeadlineException();
        }
        String startDate = description.substring(from + SIZE_OF_FROM_PARAM, by - CONVERT_TO_ZERO_INDEXING);
        String endDate = description.substring(by + SIZE_OF_BY_PARAM);
        if (from == 0) {
            throw new EmptyTaskException();
        }
        String event = description.substring(FIRST_ELEMENT, from - CONVERT_TO_ZERO_INDEXING);
        if (event.isBlank()) {
            throw new EmptyTaskException();
        }
        return new Event(event, startDate, endDate);
    }

    /**
     * Deletes a task based on the input string.
     *
     * @param input The input string containing the index of the task to delete
     */
    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(SIZE_OF_DELETE_PARAM)) - CONVERT_TO_ZERO_INDEXING;
            if (tasks.size() < index || index == -1) {
                throw new ArrayListOutOfBoundsException();
            }
            printDeleteMessage(tasks.get(index).toString());
            tasks.remove(index);
            printRemainingTaskNumber(tasks.size());
        } catch (ArrayListOutOfBoundsException e) {
            printError(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            printNoIndexError();
        }
    }

    /**
     * Handles user input by executing corresponding actions.
     * If an unknown input is given, it prints a corresponding error message.
     *
     * @param input The user input
     */
    private static void handleInput(String input) {
        if (input.contains("todo")) {
            createTodo(input);
        } else if (input.contains("deadline")) {
            createDeadline(input);
        } else if (input.contains("event")) {
            createEvent(input);
        } else if (input.contains("bye")) {
            sayBye();
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else {
            printUnknownInputMessage();
        }
    }

    /**
     * Parses the input string to get the index of the task to mark.
     *
     * @param input The input string containing the index of the task
     * @return The index of the task to mark
     */
    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + SIZE_OF_SPACING));
        return idx - 1;
    }

    /**
     * Prints all tasks in the TaskList.
     */
    public static void listItems() {
        printTaskList(tasks);
    }

    /**
     * Unmarks a task as done based on the input string.
     *
     * @param input The input string containing the index of the task to unmark
     */
    public static void unmarkItem(String input) {
        try {
            int idx = getIndexToMark(input);
            tasks.get(idx).setDone(false);
            printMarkedAsUndoneMessage(tasks.get(idx));
        } catch (NumberFormatException e) {
            printNoIndexError();
        }
    }

    /**
     * Marks a task as done based on the input string.
     *
     * @param input The input string containing the index of the task to mark
     */
    public static void markItem(String input) {
        try {
            int idx = getIndexToMark(input);
            tasks.get(idx).setDone(true);
            printMarkedAsDoneMessage(tasks.get(idx));
        } catch (NumberFormatException e) {
            printNoIndexError();
        }
    }

    /**
     * Finds tasks containing a specific keyword and prints them.
     *
     * @param input The input string containing the keyword to search for
     */
    public static void findItems(String input) {
        TaskList matchedItems = new TaskList();
        for (Task task : tasks.getAll()) {
            if (task.getDescription().contains(input)) {
                matchedItems.add(task);
            }
        }
        printMatchingTasks(matchedItems);
    }

    /**
     * Handles user interaction by reading input commands and executing corresponding actions
     * until "bye" is entered.
     */
    public static void chat() {
        String input;
        do {
            input = new Scanner(System.in).nextLine();
            showLine();
            if (input.equals("list")) {
                listItems();
            } else if (input.contains("unmark")) {
                unmarkItem(input);
            } else if (input.contains("mark")) {
                markItem(input);
            } else if (input.startsWith("find")) {
                findItems(input.substring("find".length()).trim());
            } else if (input.startsWith("help")) {
                showHelp();
            } else {
                handleInput(input);
            }
            showLine();
        } while (!input.equals("bye"));
    }
}
