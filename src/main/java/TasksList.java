import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private Integer noOfTasks;
    private List<Task> list = new ArrayList<Task>();

    public TasksList(){
        this.noOfTasks = 0;
    }

    /**
     * Add task T into TaskList
     * @param t
     */
    public void addTask(Task t) {
        this.noOfTasks++;
        this.list.add(t);
        System.out.println("Hai! You task is added ~");
        System.out.print("    ");
        t.printTask();
        System.out.println("You have " + this.noOfTasks + " tasks remaining");
    }

    /**
     * list out the Tasks in the TaskList
     */
    public void show() {
        System.out.println("Osu! Your task is as follows:");
        for(int i = 0; i < this.noOfTasks; i++) {
            System.out.print("  ");
            System.out.print((i + 1) + ".");
            this.list.get(i).printTask();
        }
    }

    /**
     * Find task with description String s (case-sensitive), and mark it as done.
     * @param s
     */
    public void markAsDone(String s) {
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            System.out.println("Gomen! Task is not in your list");
            return;
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Mark task at number index as done
     * @param index
     */
    public void markAsDone(Integer index) {
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            System.out.println("Gomen! You went out from the list");
            return;
        } else {
            this.list.get(index).markAsDone();
        }
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Find task with description String s (case-sensitive), and mark it as not done.
     * @param s
     */
    public void markAsNotDone(String s) {
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            System.out.println("Gomen! Task is not in your list");
            return;
        } else {
            this.list.get(index).markAsNotDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Mark task at number index as not done
     * @param index
     */
    public void markAsNotDone(Integer index) {
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            System.out.println("Gomen! You went out from the list");
            return;
        } else {
            this.list.get(index).markAsNotDone();
        }
        System.out.println("As you wished, your task is now undone:");
        System.out.print("    ");
        this.list.get(index).printTask();
    }

    /**
     * Find the index of inputted task String s (case-sensitive)
     * @param s
     * @return Integer index
     */
    public Integer findIndexWithDesc(String s) {
        for (int i = 0; i < this.noOfTasks; i++){
            if (this.list.get(i).getDescription().equals(s)){
                return i;
            }
        }
        return -1;
    }

    /**
     * return Task at specific index in list (0 indexed)
     * @param i
     * @return Task
     */
    public Task getTaskWithIndex(int i) {
        return this.list.get(i);
    }

    /**
     * return the latest Task inserted
     * @return Task
     */
    public Task getLatestTask() {
        return this.list.get(this.noOfTasks - 1); // -1 to get index
    }

}
