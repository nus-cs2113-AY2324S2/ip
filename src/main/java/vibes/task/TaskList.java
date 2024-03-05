package vibes.task;

import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;
import vibes.ui.TextUi;
import java.util.ArrayList;

public class TaskList {
    public static final int DESCRIPTION_INDEX = 0;
    public static final int BY_INDEX = 1;
    public static final int FROM_INDEX = 1;
    public static final int TO_INDEX = 2;

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadTasks) {
        this.tasks = loadTasks;
    }

    public void addEvent(String[] parsedInput) {
        tasks.add(new Event(parsedInput[DESCRIPTION_INDEX], parsedInput[FROM_INDEX], parsedInput[TO_INDEX]));
    }

    public void addDeadline(String[] parsedInput) {
        tasks.add(new Deadline(parsedInput[DESCRIPTION_INDEX], parsedInput[BY_INDEX]));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void setAsDone(int taskNumber, TextUi ui) {
        tasks.get(taskNumber).setDone(true);
        ui.showMarkedMessage(tasks, taskNumber);
    }

    public void setAsNotDone(int taskNumber, TextUi ui) {
        tasks.get(taskNumber).setDone(false);
        ui.showUnmarkedMessage(tasks, taskNumber);
    }

    public void deleteTask(int taskNumber, TextUi ui) {
        Task taskToDelete = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        ui.showDeletedMessage(tasks, taskToDelete);
    }
}
