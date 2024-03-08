import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor for TaskList
     * Initialises tasks as an empty ArrayList
     *
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList
     *
     * @param tasks list of tasks
     */
    TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    /**
     * Returns a new TaskList with an added task
     *
     * @param task adds new Task to existing TaskList class
     */
    TaskList add(Task task) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.add(task);
        return newList;
    }

    /**
     * Returns Task from index
     *
     * @param index index of task
     * @return Task from specific indexes
     * @throws IndexOutOfBoundsException if index given is out of bounds
     */
    Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns a new TaskList with an updated task
     *
     * @param index index of task to change
     * @param task updated Task
     * @return new TaskList with updated Task
     */
    TaskList set(int index, Task task) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.set(index, task);
        return newList;
    }

    /**
     * Returns a new TaskList with removed task
     *
     * @param index index of task to remove
     * @return new TaskList with removed Task
     */
    TaskList remove(int index) {
        TaskList newList = new TaskList(this.tasks);
        newList.tasks.remove(index);
        return newList;
    }

    /**
     * Returns length of TaskList
     *
     * @return length of TaskList
     */
    int size() {
        return this.tasks.size();
    }

    /**
     * Getter for list of tasks
     *
     * @return list of tasks
     */
    List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Print task in list of tasks
     *
     */
    void printTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            result.append(String.format("%s. %s%n", (i + 1), this.get(i)));
        }
        System.out.println(result);
    }

    /**
     * Find task in list of tasks and prints out the search results
     *
     */
    void findTasks(String keyword) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < this.size(); ++i) {
            Task task = this.get(i);
            if (task.toString().contains(keyword)) {
                results.append(String.format("%s. %s%n", (i + 1), this.get(i)));
            }
        }
        System.out.println(results);
    }

}
