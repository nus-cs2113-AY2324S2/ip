package vibes.task;

import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;
import vibes.ui.TextUi;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadTasks) {
        this.tasks = loadTasks;
    }

    public void addEvent(String[] parsedInput) {
        tasks.add(new Event(parsedInput[0], parsedInput[1], parsedInput[2]));
    }

    public void addDeadline(String[] parsedInput) {
        tasks.add(new Deadline(parsedInput[0], parsedInput[1]));
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
