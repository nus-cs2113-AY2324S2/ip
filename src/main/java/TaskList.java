import java.util.ArrayList;

public class TaskList {
    static final ArrayList<Task> tasks = Storage.load();

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param indexToDelete The index of the task to be deleted.
     * Note: This method also updates the storage with the current state of the tasks list after deletion.
     */
    public static void deleteTask(int indexToDelete) {
        tasks.remove(indexToDelete);
        Storage.save(tasks);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added to the list.
     * Note: This method also updates the storage with the current state of the tasks list after adding the new task.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public static int getSize() {

        return tasks.size();
    }

    /**
     * Prints the list of tasks to the console. Each task is prefixed with its index in the list.
     * Note: If a null task is encountered, the printing terminates early.
     */
    public static void printList() {
        int count = 0;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            count++;
            System.out.print(count + ".");
            System.out.println(t);
        }
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {

        return tasks.get(index);
    }

    /**
     * Searches for and prints tasks that contain the specified string.
     *
     * @param stringToFind The string to search for within task descriptions.
     * Note: This method prints a formatted list of matching tasks to the console.
     */
    public static void searchTasks(String stringToFind) {
        Ui ui = new Ui();
        ui.printFormat();
        System.out.println("Here are the matching tasks in your list: ");
        int count = 1;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            if(t.toString().contains(stringToFind)) {
                System.out.print(count + ". ");
                System.out.println(t);
                count++;
            }
        }
        ui.printFormat();
    }

    /**
     * Checks if the specified index is within the bounds of the task list.
     *
     * @param currentIndex The current index being checked.
     * @param index The index to check against the list size.
     * @return true if the index is within bounds, false otherwise.
     * Note: This method also prints messages to the console indicating if the index is out of bounds.
     */
    public static boolean isWithinBounds(int currentIndex, int index) {
        Ui ui = new Ui();
        if(index < currentIndex && currentIndex >= 0) {
            return true;
        }
        ui.printFormat();
        if(index == 0) {
            System.out.println("Hey, the index is out of bounds!");
        }
        if(currentIndex == 0) {
            System.out.println("Hey, you have no tasks added yet!");
        } else {
            System.out.println("Hey, you don't have that many tasks!");
        }
        ui.printFormat();
        return false;
    }

}
