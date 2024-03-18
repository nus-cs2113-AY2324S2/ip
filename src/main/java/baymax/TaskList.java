package baymax;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import exceptions.InvalidDeadlineSyntaxException;
import exceptions.InvalidEventSyntaxException;
import exceptions.InvalidFindTaskException;
import exceptions.InvalidTodoSyntaxException;

import java.util.ArrayList;

/**
 * Handles the manipulation of the tasks, such as adding and deleting tasks.
 */

public class TaskList {

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int MIN_LENGTH = 2;
    private static final String NO_DESC = "No description provided.";

    /**
     * Adds a new ToDo object into the tasks.
     *
     * @param userInput the String of the user's input.
     * @param tasks the ArrayList containing the Task objects.
     * @throws InvalidTodoSyntaxException If no description provided from the user's input.
     */
    public static void addTodo(String userInput, ArrayList<Task> tasks) throws InvalidTodoSyntaxException {
        if (userInput.length() < TODO_LENGTH + MIN_LENGTH) {
            throw new InvalidTodoSyntaxException(NO_DESC);
        } else {
            String todoDescription = Parser.obtainTodoDescription(userInput);
            tasks.add(new ToDo(todoDescription));
        }
    }

    /**
     * Adds a new Deadline object into the tasks.
     *
     * @param userInput the String of the user's input.
     * @param tasks the ArrayList containing the Task objects
     * @throws InvalidDeadlineSyntaxException If no description or syntax error provided from the user's input.
     */
    public static void addDeadline(String userInput, ArrayList<Task> tasks) throws InvalidDeadlineSyntaxException {
        if (userInput.length() < DEADLINE_LENGTH + MIN_LENGTH) {
            throw new InvalidDeadlineSyntaxException(NO_DESC);
        } else if (!userInput.contains("/by")) {
            throw new InvalidDeadlineSyntaxException("Invalid Deadline syntax provided.");
        } else {
            String deadlineDescription = Parser.obtainDeadlineDescription(userInput);
            tasks.add(new Deadline(deadlineDescription));
        }
    }

    /**
     * Adds a new Event object into the tasks.
     *
     * @param userInput the String of the user's input.
     * @param tasks the ArrayList containing the Task objects
     * @throws InvalidEventSyntaxException If no description or syntax error provided from the user's input.
     */

    public static void addEvent(String userInput, ArrayList<Task> tasks) throws InvalidEventSyntaxException {
        if (userInput.length() < EVENT_LENGTH + MIN_LENGTH) {
            throw new InvalidEventSyntaxException(NO_DESC);
        } else if (!(userInput.contains("/from ") && userInput.contains("/to "))) {
            throw new InvalidEventSyntaxException("Invalid Event syntax provided.");
        } else {
            String eventDescription = Parser.obtainEventDescription(userInput);
            tasks.add(new Event(eventDescription));
        }
    }

    /**
     * Delete a task from the tasks.
     *
     * @param index the position of the deleted task.
     * @param tasks the ArrayList containing the Task objects
     */
    public static void deleteTask(int index, ArrayList<Task> tasks) {
        tasks.remove(index);
    }

    public static ArrayList<Task> findTask(ArrayList<Task> tasks, String keyword) throws InvalidFindTaskException {
        if (keyword == null) {
            throw new InvalidFindTaskException("No keyword provided. Please enter a keyword.");
        } else if (tasks.isEmpty()) {
            throw new InvalidFindTaskException("Your tasks list is empty. Try adding some first!");
        } else {
            ArrayList<Task> findTaskList = new ArrayList<>();
            for (Task task : tasks) {
                if (task.description.contains(keyword)) {
                    findTaskList.add(task);
                }
            }
            if (findTaskList.isEmpty()) {
                throw new InvalidFindTaskException("There are no tasks containing the keyword.");
            } else {
                return findTaskList;
            }
        }

    }
}
