package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Construct an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the list.
     * @param task Thr task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark a task in the list as done.
     * @param index The index of task mark as done.
     */
    public void markTask(int index) {
        Task task = tasks.get(index);
        try {
            task.markAsDone();
        } catch (RepeatMark e) {
            System.out.println("This task is already marked.");
        }
    }

    /**
     * Get the task from the task list with an index.
     * @param index The index of task to get.
     * @return The task to get.
     */
    public Task getTask(int index){
        Task task = tasks.get(index);
        return task;
    }

    /**
     * Mark a task in the list as not done.
     * @param index The index of task mark as not done.
     */
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        try {
            task.markAsNotDone();
        } catch (RepeatUnmark e) {
            System.out.println("This task is already unmarked.");
        }
    }

    /**
     * Delete or remove a task from the task list.
     * @param index The index of task to be removed.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Get all the tasks in the task list without having to access one by one.
     * @return The ArrayList with all the existing tasks in the list.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Print all the tasks in the task list.
     */
    public void printList() {
        int index = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            } else {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
    }

    /**
     * Get the length of the array list.
     * @return The number of tasks int the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
