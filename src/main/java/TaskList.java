import java.util.ArrayList;

/**
 * The TaskList class handles various operations on a list of tasks.
 */
public class TaskList {
    private static Ui ui = new Ui();

    /**
     * Deletes a task from the list of tasks.
     *
     * @param userInput The user input specifying the task to be deleted.
     * @param tasksList The list of tasks.
     * @throws IndexOutOfBoundsException If the task number specified is out of bounds.
     */
    public static void deleteTask(String userInput, ArrayList<Task> tasksList) {
        String[] deleteCommand = Parser.splitTaskNumber(userInput);
        int taskNumber = Integer.parseInt(deleteCommand[1].trim()) - 1;
        if (taskNumber < 0 || taskNumber >= tasksList.size()) {
            throw new IndexOutOfBoundsException();
        }
        ui.taskDeleteMessage(taskNumber, tasksList);
        tasksList.remove(taskNumber);
        ui.taskRemainderDisplay(tasksList);
    }

    /**
     * Adds an event task to the list of tasks.
     *
     * @param userInput The user input specifying the event task.
     * @param tasksList The list of tasks.
     */
    public static void addEvent(String userInput, ArrayList<Task> tasksList) {
        String[] eventParts = Parser.splitEvent(userInput);
        String[] events = Parser.splitTodo(eventParts[0]);
        String[] timelineStrings = Parser.splitTimeline(eventParts[1]);

        tasksList.add(new Event(events[1], timelineStrings[0].trim(), timelineStrings[1].trim()));
        ui.displayMessageSelector(events[0]);
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param userInput The user input specifying the todo task.
     * @param tasksList The list of tasks.
     */
    public static void addTodo(String userInput, ArrayList<Task> tasksList) {
        String[] todoParts = Parser.splitTodo(userInput);
        tasksList.add(new Todo(todoParts[1]));
        ui.displayMessageSelector(todoParts[0]);
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param userInput The user input specifying the deadline task.
     * @param tasksList The list of tasks.
     */
    public static void addDeadline(String userInput, ArrayList<Task> tasksList) {
        String[] deadlineParts = Parser.splitDeadline(userInput);
        String[] timelineParts = Parser.splitTodo(deadlineParts[0]);

        tasksList.add(new Deadline(timelineParts[1], deadlineParts[1]));
        ui.displayMessageSelector(timelineParts[0]);
    }

    /**
     * Identifies and marks tasks in the list of tasks.
     *
     * @param taskNumber The number of the task to be marked.
     * @param mark       The mark indicating whether to mark or unmark the task.
     * @param tasksList  The list of tasks.
     */
    public static void identifyAndMarkTasks(int taskNumber, String mark, ArrayList<Task> tasksList) {
        String displayString;
        if (mark.equals("mark")) {
            if (!tasksList.get(taskNumber).isDone) {
                displayString = ui.markTask;
                tasksList.get(taskNumber).markTask();
            } else {
                displayString = ui.markedTask;
            }
        } else {
            if (tasksList.get(taskNumber).isDone) {
                displayString = ui.unmarkTask;
                tasksList.get(taskNumber).unmarkTask();
            } else {
                displayString = ui.unmarkedTask;
            }
        }
        ui.displayMarking(taskNumber, tasksList, displayString);
    }
    public static void findTasks(String keyword, ArrayList<Task> tasksList) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.noTaskFound();
        } else {
            Ui.displayTasks(ui.FIND,matchingTasks);
        }
    }

}