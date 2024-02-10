package geepee.task.list;

import geepee.system.SystemMessage;
import geepee.task.*;

public class List {

    protected Task[] tasks;
    protected int size;

    public List() {
        tasks = new Task[100];
        size = 0;
    }

    public void addTodo(String description) {
        tasks[size] = new Todo(description);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void addDeadline(String description, String by) {
        tasks[size] = new Deadline(description, by);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void addEvent(String description, String from, String to) {
        tasks[size] = new Event(description, from, to);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks[index].changeStatus(isDone);
        SystemMessage.printHorizontalLine();
        ListMessage.printTaskStatusMessage(isDone, tasks[index]);
        SystemMessage.printHorizontalLine();
    }

    public int getSize() {
        return size;
    }
}
