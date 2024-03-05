package cody;

import cody.tasks.Deadline;
import cody.tasks.Event;
import cody.tasks.Task;
import cody.tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String printList() {
        String listString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                    + "[" + task.getStatusIcon() + "] "
                    + task.getDescription() + "\n";
        }
        return listString;
    }

    public String handleMarking(String command, int index) throws IndexOutOfBoundsException {
        boolean isDone = command.equals("mark");
        tasks.get(index - 1).markTask(isDone);
        return "Good job! I've marked this task as " + (isDone ? "done" : "not done") + ":\n"
                + "[" + tasks.get(index - 1).getStatusIcon() + "] " + tasks.get(index - 1).getDescription();
    }

    public String addTask(String input) throws CodyException {
        Task task = createTaskFromInput(input);
        tasks.add(task);
        return "Got it. I've added this task:\n"
                + "[" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    public static Task createTaskFromInput(String input) throws CodyException {
        if (input.startsWith("todo")) {
            return createTodoTask(input);
        } else if (input.startsWith("deadline")) {
            return createDeadlineTask(input);
        } else if (input.startsWith("event")) {
            return createEventTask(input);
        } else {
            throw new CodyException("I'm not sure what you mean. "
                    + "Please use 'todo', 'deadline', or 'event' to add tasks");
        }
    }

    private static Todo createTodoTask(String input) throws CodyException {
        if (input.length() <= TODO_PREFIX_LENGTH) {
            throw new CodyException("The description of a todo cannot be empty. Please use 'todo <description>'");
        }

        String description = input.substring(TODO_PREFIX_LENGTH).trim(); // Removing 'todo ' prefix.
        return new Todo(description);
    }

    private static Deadline createDeadlineTask(String input) throws CodyException {
        if (input.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new CodyException("The description of a deadline cannot be empty. "
                    + "Please use 'deadline <description> /by <deadline>'");
        }

        String[] deadlineDetails = input.split(" /by ", 2);
        if (deadlineDetails.length < 2) {
            throw new CodyException("Invalid deadline format. Please use 'deadline <description> /by <deadline>'");
        }

        String description = deadlineDetails[0].substring(DEADLINE_PREFIX_LENGTH).trim(); // Removing 'deadline ' prefix.
        String by = deadlineDetails[1];
        return new Deadline(description, by);
    }

    private static Event createEventTask(String input) throws CodyException {
        if (input.length() <= EVENT_PREFIX_LENGTH) {
            throw new CodyException("The description of an event cannot be empty. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String[] eventDetails = input.split(" /from | /to ", 3);
        if (eventDetails.length < 3) {
            throw new CodyException("Invalid event format. "
                    + "Please use 'event <description> /from <start time> /to <end time>'");
        }

        String description = eventDetails[0].substring(EVENT_PREFIX_LENGTH).trim(); // Removing 'event ' prefix.
        String from = eventDetails[1];
        String to = eventDetails[2];
        return new Event(description, from, to);
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException {
        Task task = tasks.remove(index - 1);
        return "Noted. I've removed this task:\n"
                + "[" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    public String findTask(String keyword) {
        String listString = "Here are the matching tasks in your list:\n";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                listString += " " + (i + 1) + ". [" + task.getTaskType() + "] "
                        + "[" + task.getStatusIcon() + "] "
                        + task.getDescription() + "\n";
                count++;
            }
        }
        if (count == 0) {
            listString = "There are no matching tasks in your list.";
        }
        return listString;
    }
}
