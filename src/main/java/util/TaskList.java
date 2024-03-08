package util;

import java.util.ArrayList;
import tasks.Task;

/**
 * This class handles the array list of tasks.
 *
 */
public class TaskList {
    /**
     * ArrayList is initialised here. Storage is also initialised at the same time.
     */
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected Storage storage = new Storage();

    /**
     * Storage reading is done along with the constructor.
     */
    public TaskList() {
        this.storage.readFromFile(this);
    }

    /**
     * Adds a task to the array list, while updating nocturne.txt.
     *
     * @param task A task that will be added. Can be of type deadline, event or todo.
     */
    public void addTask(Task task) {
        System.out.println("A task I see. I have added it.");
        Ui.printTask(task);
        this.tasks.add(task);
        this.storage.saveToFile(this);
    }

    /**
     * Adds a task without printing a message, which is done
     * on initialisation when reading from nocturne.txt file,
     * while updating nocturne.txt.
     *
     * @param task The task that will be added to the array list.
     */
    public void addTaskStealth(Task task) {
        this.tasks.add(task);
        this.storage.saveToFile(this);
    }

    /**
     * Deletes a task in the array list, while updating nocturne.txt.
     *
     * @param index The index of the entry that will get deleted.
     *              Starts from 1 to be more intuitive, requiring -1
     *              in the accessing.
     */
    public void deleteTask(int index) {
        try {
            System.out.println("Should all acquaintance be forgot...");
            Ui.printTask(this.tasks.get(index - 1));
            this.tasks.remove(index - 1);
            System.out.println(this.tasks.size() + " task(s) remain.");
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }
        this.storage.saveToFile(this);
    }

    /**
     *  Lists out all the tasks recorded in the array list thus far.
     */
    public void listTasks() {
        if (this.tasks.isEmpty()) {
            Ui.emptyListMessage();
        }

        for(int i = 0; i < this.tasks.size(); ++i) {
            System.out.print(i + 1 + ".");
            System.out.println(this.tasks.get(i));
        }

    }

    /**
     * Marks a task as done, while updating nocturne.txt.
     *
     * @param index The index of the task that will be updated as done.
     */
    public void markTask(int index) {
        try {
            (this.tasks.get(index - 1)).markAsDone();
            System.out.println("Congratulations. I have marked this task as finished:");
            Ui.printTask(this.tasks.get(index - 1));
        } catch (IndexOutOfBoundsException var3) {
            Ui.indexOutOfBoundsMessage();
        }

        this.storage.saveToFile(this);
    }

    /**
     * Marks a task as undone, while updating nocturne.txt.
     *
     * @param index The index of the task that will be updated as undone.
     */
    public void unmarkTask(int index) {
        try {
            (this.tasks.get(index - 1)).markAsUndone();
            System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
            Ui.printTask(this.tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }

        this.storage.saveToFile(this);
    }

    /**
     * Finds tasks that contain the keyword and prints out any matches.
     *
     * @param keyFind The string that will be used to find matches.
     */
    public void findTask(String keyFind) {
        try {
            System.out.println("Here is what you are looking for: ");
            int count = 1;
            for (Task task : this.tasks) {
                if (task.getDescription().contains(keyFind)) {
                    System.out.println(count + ". " + task);
                    ++count;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.indexOutOfBoundsMessage();
        }
    }
}
