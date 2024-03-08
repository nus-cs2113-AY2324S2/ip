package tasks;
import customexceptions.UnknownPromptException;
import java.util.ArrayList;
import customexceptions.IncompletePromptException;

/** List of various tasks with different types and descriptions */
public class TaskList {
    protected ArrayList<Task> list;
    protected static final String INDENT = "      ";
    public TaskList() {
        list = new ArrayList<>();
    }
    protected Task lastActionTask = null;
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the array list of task list.
     *
     * @param task Task to be added.
     */
    public void addToList(Task task) {
        list.add(task);
    }

    /** Prints how many tasks there are in the task list. */
    public void countTasks() {
        int currSize = list.size();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
    }

    /**
     * Returns the task description or dates from specific keywords after separating them. Used to further
     * separates the dates from deadline and event from the task description itself.
     *
     * @param line Initial input prompt line by user.
     * @param keyword Word used to search and separate itself from the description or date.
     * @return Task description or date.
     */
    private String extractTaskOrDate(String line, String keyword) {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord; // any commands that require a 'next' word
        switch (keyword) {
        case "event":
            nextWord = " from ";
            break;
        case "deadline":
            nextWord = " by ";
            break;
        case "from":
            nextWord = " to ";
            break;
        default:
            nextWord = null;
        }
        if (nextWord != null) {
            int nextIndex = line.indexOf(nextWord, index);
            if (nextIndex != -1) {
                return line.substring(index, nextIndex).trim();
            }
            return "";
        }
        return line.substring(index).trim();
    }

    /**
     * Deletes a task from the task list based on the integer index inputted.
     *
     * @param index Index of task from task list to be deleted.
     */
    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.print(INDENT);
        lastActionTask = list.get(index - 1);
        lastActionTask.print();
        list.remove(index - 1);
        countTasks();
    }

    /** Prints all tasks in the task list. If empty, task list is not printed out. */
    public void printList() {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("      ");
                Task task = list.get(i);
                System.out.print(i + 1 + ".");
                task.print();
            }
        } else {
            System.out.println(INDENT + "There's nothing in this list.");
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