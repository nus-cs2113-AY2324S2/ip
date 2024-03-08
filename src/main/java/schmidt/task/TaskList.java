package schmidt.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static final String EMPTY_TASK_LIST_MESSAGE = "You have no tasks in your list!";
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a list of tasks with the specified tasks.
     *
     * @param tasks the list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index the index of the task to be deleted.
     * @return the task that was deleted.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Sets the task at the specified index as done or not done.
     *
     * @param index the index of the task.
     * @param isDone whether the task is done.
     * @return the task that was set as done or not done.
     */
    public Task setDone(int index, boolean isDone) {
        if (isDone) {
            this.tasks.get(index).markAsDone();
        } else {
            this.tasks.get(index).unmarkAsDone();
        }

        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds tasks that contain the specified keyword in their description, by, from, or to.
     *
     * @param keyword the keyword to search for.
     * @return the list of tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        // Iterate through the list of tasks to find matching tasks
        for (Task task : this.tasks) {
            // Check if the keyword is in the description
            boolean isKeywordInDescription = task.getDescription().contains(keyword);
            if (isKeywordInDescription) {
                matchingTasks.add(task);
                continue;
            }

            // Check if the keyword is in the by field
            if (task instanceof Deadline) {
                boolean isKeywordInBy = ((Deadline) task).getBy().contains(keyword);
                if (isKeywordInBy) {
                    matchingTasks.add(task);
                    continue;
                }
            }

            // Check if the keyword is in the from or to field
            if (task instanceof Event) {
                boolean isKeywordInFrom = ((Event) task).getFrom().contains(keyword);
                boolean isKeywordInTo = ((Event) task).getTo().contains(keyword);
                if (isKeywordInFrom || isKeywordInTo) {
                    matchingTasks.add(task);
                }
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Returns a readable string representation of the list of tasks.
     *
     * @return the string representation of the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.tasks.isEmpty()) {
            return EMPTY_TASK_LIST_MESSAGE;
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i).toString());
            if (i != this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

