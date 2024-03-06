package Parser;

import TaskList.TaskList;
import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Storage.UploadData.updateFile;
import static Ui.Ui.*;

/**
 * Represents a utility class for parsing user commands based on actions relevant to the TaskList.
 * This class provides methods for creating, deleting, marking/unmarking, and listing tasks based on user input.
 */
public class Parser {
    static TaskList tasks;
    static String filepath;

    /**
     * Constructs a Parser object with the specified file path and TaskList.
     * @param filepath The file path of the storage
     * @param tasks The TaskList to be managed
     */
    public Parser(String filepath, TaskList tasks){
        Parser.filepath = filepath;
        Parser.tasks = tasks;
    }

    /**
     * Prints confirmation messages after adding a task. The task added is shown first,
     * before the total number of tasks is shown.
     */
    private static void echo() {
        printTaskAddedMessage(tasks.get(tasks.size() - 1));
        printRemainingTaskNumber(tasks.size());
    }

    /**
     * Saves tasks to the storage file and closes the application.
     */
    private static void sayBye() {
        try {
            updateFile(filepath,tasks);
        } catch (IOException e) {
            printChangesNotSavedMessage();
        }
        closeApp();
    }

    /**
     * Creates a new Todo based on the input string.
     * @param input The input string containing the task description
     */
    public static void createTodo(String input) {
        try {
            String todo = input.substring(input.indexOf("todo ") + 5);
            if (todo.isBlank()) {
                throw new EmptyTaskException();
            }
            ToDo newEntry = new ToDo(todo);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException e){
            printError(e.getMessage());
        }

    }

    /**
     * Creates a new Deadline based on the input string.
     * @param input The input string containing the task description and deadline
     */
    private static void createDeadline(String input) {
        try {
            String description = input.replaceFirst("deadline ", "");
            int by = description.indexOf("/");
            if (by == -1) {
                throw new MissingDeadlineException();
            }
            String deadline = description.substring(0, by - 1);
            if (deadline.isBlank()) {
                throw new EmptyTaskException();
            }
            String date = description.substring(by + 4);
            Deadline newEntry = new Deadline(deadline, date);
            tasks.add(newEntry);
            echo();
        } catch (EmptyTaskException | MissingDeadlineException e){
            printError(e.getMessage());
        }
    }

    /**
     * Creates a new Event based on the input string.
     * @param input The input string containing the task description, start date, and end date
     */
    private static void createEvent(String input){
        try {
            String description = input.replaceFirst("event ", "");
            int from = description.indexOf("/from");
            if (from == -1) {
                throw new MissingStartException();
            }
            Event newEntry = getEvent(description, from);
            tasks.add(newEntry);
            echo();
        } catch (MissingStartException | MissingDeadlineException|EmptyTaskException e){
            printError(e.getMessage());
        }
    }

    /**
     * Creates a new Event object based on the description and start date index.
     * @param description The description of the event
     * @param from The index of the start date
     * @return The Event object
     * @throws MissingDeadlineException If the end date is missing
     * @throws EmptyTaskException If the description is empty
     */
    private static Event getEvent(String description, int from) throws MissingDeadlineException, EmptyTaskException {
        int by = description.indexOf("/to");
        if (by == -1) {
            throw new MissingDeadlineException();
        }
        String startDate = description.substring(from + 6, by - 1);
        String endDate = description.substring(by + 4);
        if (from == 0) {
            throw new EmptyTaskException();
        }
        String event = description.substring(0, from - 1);
        if (event.isBlank()){
            throw new EmptyTaskException();
        }
        return new Event(event,startDate,endDate);
    }

    /**
     * Deletes a task based on the input string.
     * @param input The input string containing the index of the task to delete
     */
    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (tasks.size() < index) {
                throw new ArrayListOutOfBoundsException();
            }
            printDeleteMessage(tasks.get(index).toString());
            tasks.remove(index);
            printRemainingTaskNumber(tasks.size());
        } catch (ArrayListOutOfBoundsException e){
            printError(e.getMessage());
        }
    }

    /**
     * Handles user input by executing corresponding actions.
     * If an unknown input is given, it prints a corresponding error message.
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
     * @param input The input string containing the index of the task
     * @return The index of the task to mark
     */
    public static int getIndexToMark(String input) {
        int idx = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
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
     * @param input The input string containing the index of the task to unmark
     */
    public static void unmarkItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(false);
        printMarkedAsUndoneMessage(tasks.get(idx));
    }
    /**
     * Marks a task as done based on the input string.
     * @param input The input string containing the index of the task to mark
     */
    public static void markItem(String input) {
        int idx = getIndexToMark(input);
        tasks.get(idx).setDone(true);
        printMarkedAsDoneMessage(tasks.get(idx));
    }

    /**
     * Finds tasks containing a specific keyword and prints them.
     * @param input The input string containing the keyword to search for
     */
    public static void findItems(String input) {
        TaskList matchedItems = new TaskList();
        for (Task task : tasks.getAll()){
            if (task.getDescription().contains(input)){
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
            } else {
                handleInput(input);
            }
            showLine();
        } while (!input.equals("bye"));
    }
}
