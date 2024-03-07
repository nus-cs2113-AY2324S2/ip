package drosstasks;
import drosstasks.Event;
import myexceptions.InvalidTodoException;
import java.util.ArrayList;

/**
 * Manages a list of tasks, allowing for operations such as adding, deleting,
 * and marking tasks as done or undone.
 */
public class DrossList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new DrossList instance with an empty list of tasks.
     */
    public DrossList() {
        this.tasks = new ArrayList<Task>(); // Use ArrayList instead of array
    }

    /**
     * Returns the current size of the task list.
     * @return The number of tasks in the list.
     */
    public int getSize(){
        return tasks.size();
    }

    /**
     * Retrieves a task from the list based on its index.
     * @param index The index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task getTask(int index){
        return tasks.get(index);
    }

    /**
     * Adds a new Deadline task to the list.
     * @param taskDescription The description of the Deadline task.
     * @param taskDeadline The deadline of the Deadline task.
     */
    public void addTask(String taskDescription, String taskDeadline){
        tasks.add(new Deadline(taskDescription, taskDeadline));
    }

    /**
     * Adds a new Event task to the list.
     * @param taskDescription The description of the Event task.
     * @param taskStart The start time of the Event.
     * @param taskEnd The end time of the Event.
     */
    public void addTask(String taskDescription, String taskStart, String taskEnd) {
        tasks.add(new Event(taskDescription,taskStart, taskEnd));
    }

    /**
     * Adds a new ToDo task to the list.
     * @param taskDescription The description of the ToDo task.
     * @throws InvalidTodoException if the task description is empty.
     */
    public void addTask(String taskDescription) throws InvalidTodoException {
        if (taskDescription.isEmpty()){
            throw new InvalidTodoException();
        }
        tasks.add(new ToDo(taskDescription));
    }

    /**
     * Deletes a task from the list by its index.
     * @param index The index of the task to delete.
     * @throws IndexOutOfBoundsException if the index is out of the list's range.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.remove(index-1);
    }

    /**
     * Prints the last task added to the list, if any.
     */
    public void printLastTask() {
        if (!tasks.isEmpty()) {
            System.out.println("Last added task: " + tasks.get(tasks.size() - 1));
        } else {
            System.out.println("The list is empty.");
        }
    }

    /**
     * Marks a task as done by its index.
     * @param index The index of the task to mark as done.
     * @throws IndexOutOfBoundsException if the index is out of the list's range.
     */
    public void markDoneByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.get(index-1).checkTask();
    }

    /**
     * Marks a task as undone by its index.
     * @param index The index of the task to mark as undone.
     * @throws IndexOutOfBoundsException if the index is out of the list's range.
     */
    public void markUndoneByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index out of range.");
        }
        tasks.get(index-1).uncheckTask();
    }

    /**
     * Prints all tasks currently in the list.
     */
    public void printAllTasks() {
        if (!tasks.isEmpty()) {
            System.out.println("All tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.print((i + 1) + ".");
                System.out.println(currentTask);
            }
        }
        else {
            System.out.println("The list is empty.");
        }
    }
}
