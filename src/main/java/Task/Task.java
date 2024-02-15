package Task;

/**
 * Represents a task in the chatbot application.
 * A task has a name, a completion status, and a unique task number.
 */
public class Task {
    public String name; // The name or description of the task
    public boolean hasDone; // The completion status of the task
    public int taskNo; // The unique number assigned to the task

    /**
     * Constructs a new Task with the specified name and task number.
     * Initially, the task is marked as not done.
     *
     * @param name The name or description of the task.
     * @param taskNo The unique number assigned to the task.
     */
    public Task(String name, int taskNo) {
        this.name = name;
        this.hasDone=false;
        this.taskNo = taskNo;
    }

    /**
     * Marks this task as completed.
     * Prints a confirmation message including the task's name.
     */
    public void markedTask(){
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("    " + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this task as not completed.
     * Prints a confirmation message indicating the task is not done yet.
     */
    public void unmarkedTask(){
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("    " + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints the task's details, including its number, completion status, and name.
     */
    public void printTask(){
        System.out.print("    " +taskNo);
        if (hasDone){
            System.out.print(".[X] ");
        }else{
            System.out.print(".[ ] ");
        }
        System.out.println(name);
    }
}
