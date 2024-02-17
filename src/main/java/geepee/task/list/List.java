package geepee.task.list;

import java.util.ArrayList;
import geepee.task.*;

public class List {

    protected ArrayList<Task> tasks;

    public List() {
        tasks = new ArrayList<>();
    }

    public void addTodo(String description) {
        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        ListMessage.printAfterAddingTask(tasks.size(), newTodo);
    }

    public void addDeadline(String description, String by) {
        Task newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        ListMessage.printAfterAddingTask(tasks.size(), newDeadline);
    }

    public void addEvent(String description, String from, String to) {
        Task newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        ListMessage.printAfterAddingTask(tasks.size(), newEvent);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks.get(index).changeStatus(isDone);
        ListMessage.printTaskStatusMessage(isDone, tasks.get(index));
    }

    public int getSize() {
        return tasks.size();
    }
}
