package fredbot;

import fredbot.exception.EmptyDescriptionException;
import fredbot.exception.NoMatchesException;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list of tasks.
 * A TaskList object contains all tasks and the total number of tasks.
 */
public class TaskList {
    private static final int INDEX_DESCRIPTION = 2;
    private static final int INDEX_BY = 3;
    private static final int INDEX_FROM = 3;
    private static final int INDEX_TO = 4;
    private static final int INDEX_STATUS = 1;
    private static final String STATUS_DONE = "1";

    protected static ArrayList<Task> allTasks;
    protected static int count;

    /**
     * Constructs the TaskList object.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
        count = 0;
    }

    /**
     * Reads and adds an Event task from the save file.
     *
     * @param taskArgs Event details.
     * @throws EmptyDescriptionException If the description of the Event is empty.
     */
    public void readEvent(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Event(taskArgs[INDEX_DESCRIPTION].trim(), taskArgs[INDEX_FROM].trim(), taskArgs[INDEX_TO].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    /**
     * Reads and adds a Deadline task from the save file.
     *
     * @param taskArgs Event details.
     * @throws EmptyDescriptionException If the description of the Deadline is empty.
     */
    public void readDeadline(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Deadline(taskArgs[INDEX_DESCRIPTION].trim(), taskArgs[INDEX_BY].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    /**
     * Reads and adds a Todo task from the save file.
     *
     * @param taskArgs Event details.
     * @throws EmptyDescriptionException If the description of the Todo is empty.
     */
    public void readTodo(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Todo(taskArgs[INDEX_DESCRIPTION].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    private static void markDone(Task t, String status) {
        if (status.trim().equals(STATUS_DONE)) {
            t.markAsDone();
        }
    }

    /**
     * Adds a Todo task as specified by the user.
     *
     * @param input Todo details.
     * @throws EmptyDescriptionException If the description of the Todo is empty.
     */
    public void addTodo(String input) throws EmptyDescriptionException {
        allTasks.add(new Todo(input));
    }

    /**
     * Adds a Deadline task as specified by the user.
     *
     * @param split Deadline details.
     * @throws EmptyDescriptionException If the description of the Deadline is empty.
     * @throws DateTimeParseException If the date provided is not in the right format.
     */
    public static void addDeadline(String[] split) throws EmptyDescriptionException, DateTimeParseException {
        allTasks.add(new Deadline(split[0], split[1]));
    }

    /**
     * Adds an Event task as specified by the user.
     *
     * @param split Event details.
     * @throws EmptyDescriptionException If the description of the Event is empty.
     */
    public void addEvent(String[] split) throws EmptyDescriptionException {
        allTasks.add(new Event(split[0], split[1], split[2]));
    }

    /**
     * Increases the count of the TaskList by one.
     */
    public void increaseCount() {
        count++;
    }

    /**
     * Decreases the count of the TaskList by one.
     */
    public void decreaseCount() {
        count--;
    }

    /**
     * Retrieves the task from the list of tasks as specified.
     *
     * @param index Index of the task.
     */
    public Task getTask(int index) {
        return allTasks.get(index);
    }

    /**
     * Retrieves the count in the TaskList.
     *
     * @return Total number of tasks.
     */
    public int getCount() {
        return count;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public static void findTask(String keyword, Ui ui) throws NoMatchesException {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Task task = allTasks.get(i);
            if (task.toString().contains(keyword)) {
                matches.add(task);
            }
        }
        if (matches.isEmpty()) {
            throw new NoMatchesException();
        }
        ui.showMatches(matches);
    }
}
