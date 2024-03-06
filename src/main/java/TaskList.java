import exceptions.InvalidDeadlineSyntaxException;
import exceptions.InvalidEventSyntaxException;
import exceptions.InvalidFindTaskException;
import exceptions.InvalidTodoSyntaxException;

import java.util.ArrayList;

public class TaskList {

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int MIN_LENGTH = 2;
    private static final String NO_DESC = "No description provided.";

    public static void addTodo(String userInput, ArrayList<Task> taskArrayList) throws InvalidTodoSyntaxException {
        if (userInput.length() < TODO_LENGTH + MIN_LENGTH) {
            throw new InvalidTodoSyntaxException(NO_DESC);
        } else {
            String todoDescription = Parser.obtainTodoDescription(userInput);
            taskArrayList.add(new ToDo(todoDescription));
        }
    }

    public static void addDeadline(String userInput, ArrayList<Task> taskArrayList) throws InvalidDeadlineSyntaxException {
        if (userInput.length() < DEADLINE_LENGTH + MIN_LENGTH) {
            throw new InvalidDeadlineSyntaxException(NO_DESC);
        } else if (!userInput.contains("/by")) {
            throw new InvalidDeadlineSyntaxException("Invalid Deadline syntax provided.");
        } else {
            String deadlineDescription = Parser.obtainDeadlineDescription(userInput);
            taskArrayList.add(new Deadline(deadlineDescription));
        }
    }

    public static void addEvent(String userInput, ArrayList<Task> taskArrayList) throws InvalidEventSyntaxException {
        if (userInput.length() < EVENT_LENGTH + MIN_LENGTH) {
            throw new InvalidEventSyntaxException(NO_DESC);
        } else if (!(userInput.contains("/from ") && userInput.contains("/to "))) {
            throw new InvalidEventSyntaxException("Invalid Event syntax provided.");
        } else {
            String eventDescription = Parser.obtainEventDescription(userInput);
            taskArrayList.add(new Event(eventDescription));
        }
    }

    public static void deleteTask(int index, ArrayList<Task> taskArrayList) {
        taskArrayList.remove(index);
    }

    public static ArrayList<Task> findTask(ArrayList<Task> taskArrayList, String keyword) throws InvalidFindTaskException {
        if (keyword == null) {
            throw new InvalidFindTaskException("No keyword provided. Please enter a keyword.");
        } else if (taskArrayList.isEmpty()) {
            throw new InvalidFindTaskException("Your tasks list is empty. Try adding some first!");
        } else {
            ArrayList<Task> findTaskList = new ArrayList<>();
            for (Task task : taskArrayList) {
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
