import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    Storage storage;
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
        storage = new Storage();
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int i){
        return tasks.get(i);
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
        if (isValidIndex){
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
        tasks.get(index - 1).isDone = true;
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }

    public void unmark (int index) {
        tasks.get(index - 1).isDone = false;
        storage.save(tasks);
        System.out.println(UI.LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        System.out.println(UI.LINE_SEPARATOR);
    }




}
