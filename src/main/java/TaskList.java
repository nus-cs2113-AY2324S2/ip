import java.util.ArrayList;
public class TaskList {
    private static Ui ui = new Ui();

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

    public static void addEvent(String userInput, ArrayList<Task> tasksList) {
        String[] eventParts = Parser.splitEvent(userInput);
        String[] events = Parser.splitTodo(eventParts[0]);
        String[] timelineStrings = Parser.splitTimeline(eventParts[1]);

        tasksList.add(new Event(events[1], timelineStrings[0].trim(), timelineStrings[1].trim()));
        ui.displayMessageSelector(events[0]);
    }

    public static void addTodo(String userInput, ArrayList<Task> tasksList) {
        String[] todoParts = Parser.splitTodo(userInput);
        tasksList.add(new Todo(todoParts[1]));
        ui.displayMessageSelector(todoParts[0]);
    }

    public static void addDeadline(String userInput, ArrayList<Task> tasksList) {
        String[] deadlineParts = Parser.splitDeadline(userInput);
        String[] timelineParts = Parser.splitTodo(deadlineParts[0]);

        tasksList.add(new Deadline(timelineParts[1], deadlineParts[1]));
        ui.displayMessageSelector(timelineParts[0]);
    }

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
}
