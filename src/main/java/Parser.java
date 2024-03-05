import Exceptions.ArgumentNotFoundException;
import Exceptions.TaskNotFoundException;
import Tasks.Task;

import java.util.ArrayList;

public class Parser {
    public static final int MARK_TASK_INDEX = 5;
    public static final int UNMARK_TASK_INDEX = 7;
    public static final int EVENT_TASK_INDEX = 6;
    public static final int DEADLINE_TASK_INDEX = 9;
    public static final int TODO_TASK_INDEX = 5;
    public static final int DELETE_TASK_INDEX = 7;
    protected String parseEvent(String input) throws ArgumentNotFoundException {
        if (EVENT_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String event = input.substring(EVENT_TASK_INDEX);
        String[] parts = event.split(" /from ");
        String[] time = parts[1].split(" /to ");
        return parts[0] + " (from: " + time[0] + " to: " + time[1] + ")";
    }

    protected String parseDeadline(String input) throws ArgumentNotFoundException {
        if (DEADLINE_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String deadline = input.substring(DEADLINE_TASK_INDEX);
        String[] parts = deadline.split(" /by ");
        return parts[0] + " (by: " + parts[1] + ")";
    }

    protected String parseTodo(String input) throws ArgumentNotFoundException {
        if (TODO_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        return input.substring(TODO_TASK_INDEX);
    }

    protected int getIndexMark(ArrayList<Task> inputList, String input) throws ArgumentNotFoundException, TaskNotFoundException {
        if (MARK_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        int indexMark = Integer.parseInt(input.substring(MARK_TASK_INDEX)) - 1;
        if (inputList.get(indexMark) == null) {
            throw new TaskNotFoundException();
        }
        return indexMark;
    }

    protected int getIndexUnmark(ArrayList<Task> inputList, String input) throws ArgumentNotFoundException, TaskNotFoundException {
        if (UNMARK_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        int indexUnmark = Integer.parseInt(input.substring(UNMARK_TASK_INDEX)) - 1;
        if (inputList.get(indexUnmark) == null) {
            throw new TaskNotFoundException();
        }
        return indexUnmark;
    }

    protected int getIndexDelete(String input) {
        return Integer.parseInt(input.substring(DELETE_TASK_INDEX)) - 1;
    }
}
