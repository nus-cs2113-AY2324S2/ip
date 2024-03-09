package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exception.ZukeException;
import ui.ResponseManager;

/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the bottom of task list.
     * 
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the labeled index.
     * 
     * @param index the index of the task to be deleted.
     * @throws ZukeException if the index is out of range.
     */
    public void deleteTaskAt(int index) throws ZukeException {
        if (index > this.getSize()) {
            throw new ZukeException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        tasks.remove(index - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Outputs the task at the specified index in the task list.
     * 
     * @param index the index of the task to be returned.
     * @return the task at the specified index.
     */
    public Task getPosAt(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Marks a task at specified index as done.
     * 
     * @param index the index of the task to be marked.
     * @throws ZukeException if the index is out of range.
     */
    public void markTask(int index) throws ZukeException {
        if (index > this.getSize()) {
            throw new ZukeException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        Task task = tasks.get(index - 1);
        int position = tasks.indexOf(task);
        task.mark();
        tasks.set(position, task);
    }

    /**
     * Unmarks a task at specified index as not done.
     * 
     * @param index the index of the task to be unmarked.
     * @throws ZukeException if the index is out of range.
     */
    public void unmarkTask(int index) throws ZukeException {
        if (index > this.getSize()) {
            throw new ZukeException(ResponseManager.INDEX_ERROR_MESSAGE);
        }
        Task task = tasks.get(index - 1);
        int position = tasks.indexOf(task);
        task.unmark();
        tasks.set(position, task);
    }

    /**
     * Lists all the tasks in the task list and
     * numbers tasks based their positions in the task list.
     * 
     * @return a string containing all the tasks in the task list.
     */
    public String listTasks() {
        String tasksToBeListed = "";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksToBeListed += String.format("%d.%s\n", i, this.getPosAt(i).toString());
        }
        return tasksToBeListed;
    }

    /**
     * Lists all the tasks in the task list for saving current data in a file.
     * 
     * @return a string containing all the tasks in the task list 
     * which is formatted for saving.
     */
    public String listTasksForSave() {
        String tasksToBeListed = "";
        for (int i = 1; i <= tasks.size(); i++) {
            tasksToBeListed += String.format("%s\n", this.getPosAt(i).toSave());
        }
        return tasksToBeListed;
    }

    /**
     * Shows the task that was most recently added to the task list.
     * 
     * @return the task that was most recently added to the task list.
     */
    public Task showNewlyAddedTask() {
        return this.getPosAt(tasks.size());
    }

    /**
     * Finds a task contains the stated keyword in the task list.
     * 
     * @param keyword the keyword to be searched for.
     * @return a string containing the tasks that contain the keyword.
     */
    public String findTask(String keyword) {
        List<Task> eligibleList =
                tasks.stream().filter(task -> task.containsWord(keyword))
                .collect(Collectors.toList());
        return new TaskList(eligibleList).listTasks();
    }

    /**
     * Finds a task contains the stated time in the task list.
     * 
     * @param time the date or time to be searched for.
     * @return a string containing the tasks that contain the date.
     */
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
