package fredbot;

import fredbot.exception.EmptyDescriptionException;
import fredbot.exception.NoMatchesException;
import fredbot.task.Deadline;
import fredbot.task.Event;
import fredbot.task.Task;
import fredbot.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static final int INDEX_DESCRIPTION = 2;
    private static final int INDEX_BY = 3;
    private static final int INDEX_FROM = 3;
    private static final int INDEX_TO = 4;

    private static final int INDEX_STATUS = 1;

    private static final String STATUS_DONE = "1";

    protected static ArrayList<Task> allTasks;
    protected static int count;

    public TaskList() {
        allTasks = new ArrayList<>();
        count = 0;
    }

    public static void readEvent(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Event(taskArgs[INDEX_DESCRIPTION].trim(), taskArgs[INDEX_FROM].trim(), taskArgs[INDEX_TO].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    public static void readDeadline(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Deadline(taskArgs[INDEX_DESCRIPTION].trim(), taskArgs[INDEX_BY].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    public static void readTodo(String[] taskArgs) throws EmptyDescriptionException {
        allTasks.add(new Todo(taskArgs[INDEX_DESCRIPTION].trim()));
        markDone(allTasks.get(count), taskArgs[INDEX_STATUS]);
        count++;
    }

    private static void markDone(Task t, String status) {
        if (status.trim().equals(STATUS_DONE)) {
            t.markAsDone();
        }
    }

    public static void addTodo(String input) throws EmptyDescriptionException {
        allTasks.add(new Todo(input));
    }

    public static void addDeadline(String[] split) throws EmptyDescriptionException, DateTimeParseException {
        allTasks.add(new Deadline(split[0], split[1]));
    }

    public static void addEvent(String[] split) throws EmptyDescriptionException {
        allTasks.add(new Event(split[0], split[1], split[2]));
    }

    public static void increaseCount() {
        count++;
    }

    public static void decreaseCount() {
        count--;
    }

    public static Task getTask(int index) {
        return allTasks.get(index);
    }

    public static int getCount() {
        return count;
    }

    public static ArrayList<Task> getTaskList() {
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
