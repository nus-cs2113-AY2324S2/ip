import java.util.ArrayList;

/**
 * Storing tasks that the user input into the program. A <code>TaskList</code> object
 * corresponds to a list of tasks input by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    Storage storage;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        storage = new Storage();
    }

    public void find(String keyword){
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Here are the matching tasks in your list: ");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getName().contains(keyword)){
                System.out.println((count++) + ". " + tasks.get(i));
            }
        }
        System.out.println(UI.LINE_SEPARATOR);
    }

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
        if (isValidIndex) {
            System.out.println(UI.LINE_SEPARATOR);
            System.out.println("Deleted task " + deletedTask + ".");
            System.out.println("Now you have " + tasks.size() + " task(s) left.");
            System.out.println(UI.LINE_SEPARATOR);
        }
    }

    public void listTasks () {
        System.out.println(UI.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println(UI.LINE_SEPARATOR);
    }

    public void mark (int index) {
        try {
            tasks.get(index - 1).isDone = true;
        } catch (IndexOutOfBoundsException iobe) {
            UI.printMessage("Index out of bound");
            return;
        }
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    public void unmark (int index) {
        try {
            tasks.get(index - 1).isDone = false;
        } catch (IndexOutOfBoundsException iobe) {
            UI.printMessage("Index out of bound");
            return;
        }
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }




}
