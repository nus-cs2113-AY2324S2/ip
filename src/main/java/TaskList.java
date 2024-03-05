import java.util.ArrayList;

public class TaskList {

    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int MIN_LENGTH = 2;

    public static void addTodo(String userInput, ArrayList<Task> taskArrayList) {
        if (userInput.length() < TODO_LENGTH + MIN_LENGTH) {
            // ADD EXCEPTION
        } else {
            String todoDescription = Parser.obtainTodoDescription(userInput);
            taskArrayList.add(new ToDo(todoDescription));
        }
    }

    public static void addDeadline(String userInput, ArrayList<Task> taskArrayList) {
        if (userInput.length() < DEADLINE_LENGTH + MIN_LENGTH) {
            // ADD EXCEPTION
        } else {
            String deadlineDescription = Parser.obtainDeadlineDescription(userInput);
            taskArrayList.add(new Deadline(deadlineDescription));
        }
    }

    public static void addEvent(String userInput, ArrayList<Task> taskArrayList) {
        if (userInput.length() < EVENT_LENGTH + MIN_LENGTH) {
            // ADD EXCEPTION
        } else {
            String eventDescription = Parser.obtainEventDescription(userInput);
            taskArrayList.add(new Event(eventDescription));
        }
    }

    public static void deleteTask(int index, ArrayList<Task> taskArrayList) {
        String removedTask = taskArrayList.get(index).description;
        taskArrayList.remove(index);
    }

}
