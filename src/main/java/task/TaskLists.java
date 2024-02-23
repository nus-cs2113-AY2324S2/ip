package task;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class TaskLists implements Serializable{
    /** All the tasks stored */
    private final List<Tasks> tasksList;

    /**
     * Constructor of the class.
     */
    public TaskLists() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add Task to the array inside TaskLists.
     * @param task Task assigned.
     */
    public void addTask(Tasks task) {
        this.tasksList.add(task);
    }

    /**
     * Change the status of the Task at that index to checked.
     *
     * @param index index of the Task to change.
     */
    public void box(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.mark();
        this.tasksList.set(index - 1, fetch);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + fetch);
        System.out.println("____________________________________________________________");
    }

    /**
     * Change the status of the Task at that index to unchecked.
     *
     * @param index index of the Task to change.
     */
    public void unBox(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        fetch.unMark();
        this.tasksList.set(index - 1, fetch);
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + fetch);
        System.out.println("____________________________________________________________");
    }

    public void delete(int index) {
        Tasks fetch = this.tasksList.get(index - 1);
        this.tasksList.remove(index - 1);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + fetch);
        int size = this.tasksList.size();
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void taskNumPrint() {
        System.out.println("Now you have " + this.tasksList.size() + " tasks in the list");
    }

    public int tasksSize() {
        return this.tasksList.size();
    }

    /**
     * Print all the tasks stored inside the array of the class with their index.
     */
    public void printTasks() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasksList.get(i).toString());
        }
    }

    public String print() {
        String output = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            output = output + (i + 1) + ". " + this.tasksList.get(i).toString() + '\n';
        }
        return output;
    }
}
