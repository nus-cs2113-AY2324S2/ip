package chris.tasktypes;

import java.util.ArrayList;
public class taskList {
    private final ArrayList<Task> tasks;
    private int taskCount = 0;
    public taskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList and increments total count.
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks task at index and returns a string that tells user if task was marked or unmarked
     * @param taskNumber index of task to be marked
     * @return string that tells user if task was marked or unmarked
     */
    public String markTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        if (tasks.get(index - 1).markTask()) {
            return "Task marked!";
        } else {
            return "Task unmarked!";
        }
    }

    /**
     * Deletes the task at index, decreases total count and returns the deleted task
     * @param taskNumber index of task to be deleted
     * @return Task object that was deleted
     */
    public Task deleteTask(String taskNumber){
        int index = Integer.parseInt(taskNumber);
        Task deletedTask = tasks.remove(index - 1);
        taskCount--;
        return deletedTask;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns a string of all tasks in the list that will be displayed to the user.
     * Tells the user that there are no tasks if task count is 0.
     * @return string of all tasks
     */
    public String printTaskList() {
        StringBuilder string = new StringBuilder();
        if (taskCount == 0) {
            return "You currently have no tasks!";
        }
        string.append("Here are your current tasks!");
        int count = 1;
        for (Task i : tasks) {
            string.append("\n");
            string.append(count);
            string.append(". ");
            string.append(i.toString());
            count++;
        }
        return string.toString();
    }

    /**
     * Finds tasks that contains the given keyword in the description
     * @param keyword keyword to be searched
     * @return taskList object of tasks that contain the given keyword
     */
    public taskList find(String keyword) {
        taskList foundTasks = new taskList();
        for (Task i : tasks) {
            if (i.getDescription().contains(keyword)) {
                foundTasks.addTask(i);
            }
        }
        return foundTasks;
    }
}
