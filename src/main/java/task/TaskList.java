package task;

import exception.InputException;
import tool.ResponseManager;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void deleteTaskAt(int index) throws InputException {
        if (index > this.getSize()) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        tasks.remove(index - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getPosAt(int index) {
        return tasks.get(index - 1);
    }

    public void markTask(int taskNum) throws InputException {
        if (taskNum > this.getSize()) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        Task task = tasks.get(taskNum - 1);
        int position = tasks.indexOf(task);
        task.mark();
        tasks.set(position, task);
    }

    public void unmarkTask(int taskNum) throws InputException {
        if (taskNum > this.getSize()) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        Task task = tasks.get(taskNum - 1);
        int position = tasks.indexOf(task);
        task.unmark();
        tasks.set(position, task);
    }

    public String listTasks() {
        String tasksToBeListed = "";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksToBeListed += String.format("%d.%s\n", i, this.getPosAt(i).toString());
        }
        return tasksToBeListed;
    }

    public Task showNewlyAddedTask() {
        return this.getPosAt(tasks.size());
    }

    @Override
    public String toString() {
        String formOfTask = tasks.size() > 1 ? "tasks" : "task";
        return String.format("Now you have %d %s in the list", tasks.size(), formOfTask);
    }
}
