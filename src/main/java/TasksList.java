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
        System.out.println("Deleted! The following task has been removed!");
        System.out.print("    ");
        t.printTask();
        System.out.println("You have " + this.noOfTasks + " tasks remaining");
    }

    /**
     * deleteTask
     * @param s is index of list in String
     * @throws SalmonNotInListException
     */
    public void deleteTask(String s) throws SalmonNotInListException{
        int index = Integer.parseInt(s);
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
        } else {
            System.out.println("Hai! You task is added ~");
            System.out.print("    ");
            list.get(index).printTask();

            // update no of task
            this.noOfTasks--;

            System.out.println("You have " + this.noOfTasks + " tasks remaining");

            list.remove(index);
        }
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

    public String toString() {
        String result = "";
        for(int i = 0; i < this.noOfTasks; i++) {
            result = result + list.get(i).toString() + System.lineSeparator();
        }
        return result;
    }

    /**
     * Find task with description String s (case-sensitive), and mark it as done.
     * @param s
     */
    public void markAsDone(String s) throws SalmonNotInListException{
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            throw new SalmonNotInListException();
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
    public void markAsDone(Integer index) throws SalmonNotInListException{
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
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
    public void markAsNotDone(String s) throws SalmonNotInListException{
        Integer index = this.findIndexWithDesc(s);
        if (index == -1) {
            throw new SalmonNotInListException();
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
    public void markAsNotDone(Integer index) throws SalmonNotInListException{
        index -= 1;
        if (index < 0 || index > (this.noOfTasks - 1)) {
            throw new SalmonNotInListException();
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
