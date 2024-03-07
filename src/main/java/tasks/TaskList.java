package tasks;
import customexceptions.UnknownPromptException;
import java.util.ArrayList;
import customexceptions.IncompletePromptException;

public class TaskList {
    protected ArrayList<Task> list;
    protected static final String INDENT = "      ";
    public TaskList() {
        list = new ArrayList<>();
    }
    protected Task lastActionTask = null; // Allows for 'undo' functionality
    public ArrayList<Task> getList() {
        return list;
    }
    public int getListSize() {
        return list.size();
    }
    public void addToList(Task task) {
        list.add(task);
    }

    public Task getLastActionTask() {
        return lastActionTask;
    }
    public void setLastActionTask(Task lastActionTask) {
        this.lastActionTask = lastActionTask;
    }

    /** Prints out how many tasks there are in the task list. */
    public void countTasks() {
        int currSize = getListSize();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }

    /**
     * Deletes a task from the task list based on the integer index inputted.
     *
     * @param index Index of task from task list to be deleted.
     */
    public void deleteTask(int index) {
        System.out.print(INDENT);
        lastActionTask = list.get(index - 1);
        lastActionTask.print();
        list.remove(index - 1);
        countTasks();
    }

    /** Prints all tasks in the task list. If empty, task list is not printed out. */
    public void printList() {
        if (list.isEmpty()) {
            System.out.println(INDENT + "There's nothing in this list.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print("      ");
            Task task = list.get(i);
            System.out.print(i + 1 + ".");
            task.print();
        }
    }

    /**
     * Returns task from task list based on index.
     *
     * @param index Index of task.
     * @return index-th task.
     */
    public Task getTask(int index) {
        return list.get(index);
    }
}
