package Task;

/**
 * Represents a task in the chatbot application.
 * A task has a name, and a completion status.
 */
public class Task {
    public String name; // The name or description of the task
    public boolean hasDone; // The completion status of the task

    /**
     * Constructs a new Task with the specified name and task number.
     * Initially, the task is marked as not done.
     *
     * @param name The name or description of the task.
     */
    public Task(String name, boolean hasDone) {
        this.name = name;
        this.hasDone = hasDone;
    }

    /**
     * Marks this task as completed.
     * Prints a confirmation message including the task's name.
     */
    public void markTaskAsComplete(){
        this.hasDone=true;
        System.out.println("    " + "-----RUBY-----");
        System.out.println("    " + "Nice! I've marked this task as done:");
    }

    /**
     * Marks this task as not completed.
     * Prints a confirmation message indicating the task is not done yet.
     */
    public void markTaskAsIncomplete(){
        this.hasDone=false;
        System.out.println("    " + "-----RUBY-----");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
    }

    /**
     * Prints the task's details, including its number, completion status, and name.
     */
    public void printTask(){
        if (hasDone){
            System.out.print(".[X] ");
        }else{
            System.out.print(".[ ] ");
        }
        System.out.println(name);
    }
}
