import java.util.ArrayList;

/**
 * A list of tasks. This list can add/remove a task,
 * find a task by a keyword or mark/unmark a task.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    Storage storage;
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        storage = new Storage();
    }

    /**
     * Add a new task to the list.
     * @param task the new task to be added to the list.
     */
    public void addTask (Task task) {
        System.out.println(UI.LINE_SEPARATOR);

        switch (task.getTaskType()) {
            case DEADLINE:
            case EVENT:
            case TODO:
                System.out.println("Got it. I've added this task:");
                tasks.add(task);
                storage.save(tasks);
                break;
        }
        System.out.println(task);

        System.out.println(UI.LINE_SEPARATOR);
    }

    /**
     * Delete a task from the list.
     * If the task index is out of the list's bound,
     * the method will print "Index out of bound!" to the terminal.
     * If the method catches a NumberFormatException,
     * the method will print "Invalid index!" to the terminal.
     * If a task is successfully deleted, the method will
     * display the deleted task and the number of tasks left
     * in the list.
     * @param index the index of the task to be deleted.
     */
    public void delete(int index) {
        boolean isValidIndex;
        Task deletedTask = null;
        try {
            isValidIndex = true;
            deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            storage.save(tasks);
        }   catch (IndexOutOfBoundsException ioobe) {
            isValidIndex = false;
            UI.printMessage("Index out of bound!");
        }   catch (NumberFormatException nfe) {
            isValidIndex = false;
            UI.printMessage("Invalid index!");
        }
        if (isValidIndex){
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Deleted task " + deletedTask + ".");
            System.out.println("Now you have " + tasks.size() + " task(s) left.");
            System.out.println(UI.LINE_SEPARATOR);
        }
    }

    /**
     * Display all tasks in the list on the terminal.
     */
    public void listTasks () {
        System.out.println(UI.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(UI.LINE_SEPARATOR);
    }

    /**
     * Mark a task as done.
     * @param index the index of a task to be marked.
     */
    public void mark (int index) {
        tasks.get(index - 1).isDone = true;
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    /**
     * mark a task as not finished.
     * @param index the index of the task to be mark as not finished.
     */
    public void unmark (int index) {
        tasks.get(index - 1).isDone = false;
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }
}
