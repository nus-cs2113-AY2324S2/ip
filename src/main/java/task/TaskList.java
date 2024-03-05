package task;

import exception.InputException;
import tool.ResponseManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * This method adds a task to the task list
     * @param task the task to be added
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * This method deletes a task from the task list
     * @param index the index of the task to be deleted
     * @throws InputException if the index is out of range
     */
    public void deleteTaskAt(int index) throws InputException {
        if (index > this.getSize()) {
            throw new InputException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        tasks.remove(index - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * This method returns the task at the specified index in the task list
     * @param index the index of the task to be returned
     * @return the task at the specified index
     */
    public Task getPosAt(int index) {
        return tasks.get(index - 1);
    }

    /**
     * This method marks a task as done
     * @param taskNum the index of the task to be marked
     * @throws InputException if the index is out of range
     */
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

    /**
     * This method lists all the tasks in the task list
     * @return a string containing all the tasks in the task list
     */
    public String listTasks() {
        String tasksToBeListed = "";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksToBeListed += String.format("%d.%s\n", i, this.getPosAt(i).toString());
        }
        return tasksToBeListed;
    }

    /**
     * This method returns the task that was most recently added to the task list
     * @return the task that was most recently added to the task list
     */
    public Task showNewlyAddedTask() {
        return this.getPosAt(tasks.size());
    }

    public String findTask(String keyword) {
        List<Task> eligibleList =
                tasks.stream().filter(task -> task.containsWord(keyword))
                .collect(Collectors.toList());
        return new TaskList(eligibleList).listTasks();
    }

    public String findTime(String time) {
        List<Task> eligibleList =
                tasks.stream().filter(task -> task.containsTime(time))
                .collect(Collectors.toList());
        return new TaskList(eligibleList).listTasks();
    }

    @Override
    public String toString() {
        String formOfTask = tasks.size() > 1 ? "tasks" : "task";
        return String.format("Now you have %d %s in the list", tasks.size(), formOfTask);
    }
}
