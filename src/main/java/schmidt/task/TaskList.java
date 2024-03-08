package schmidt.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Sets the task at the specified index as done or not done.
     *
     * @param index the index of the task
     * @param isDone whether the task is done
     * @return the task that was set as done or not done
     */
    public Task setDone(int index, boolean isDone) {
        if (isDone) {
            this.tasks.get(index).markAsDone();
        } else {
            this.tasks.get(index).unmarkAsDone();
        }

        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds tasks that contain the specified keyword in their description, by, from, or to.
     *
     * @param keyword the keyword to search for
     * @return the list of tasks that contain the keyword
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            boolean isKeywordInDescription = task.getDescription().contains(keyword);
            if (isKeywordInDescription) {
                matchingTasks.add(task);
                continue;
            }

            if (task instanceof Deadline) {
                boolean isKeywordInBy = ((Deadline) task).getBy().contains(keyword);
                if (isKeywordInBy) {
                    matchingTasks.add(task);
                    continue;
                }
            }

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
     * @return the string representation of the list of tasks
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.tasks.isEmpty()) {
            return "You have no tasks in your list!";
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

