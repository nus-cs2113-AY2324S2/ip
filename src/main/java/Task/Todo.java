package Task;

/**
 * Represents a todo task in the chatbot application.
 * A todo task is a basic task type without any date or time constraints.
 */
public class Todo extends Task{
    /**
     * Constructs a new Todo task with a specified name and task number.
     *
     * @param name The name or description of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Marks this todo task as completed.
     * Overrides the markedTask method in the Task class to include todo-specific details in the confirmation message.
     */
    @Override
    public void markedTask() {
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("      [T]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this todo task as not completed.
     * Overrides the unmarkedTask method in the Task class to include todo-specific details in the confirmation message.
     */
    @Override
    public void unmarkedTask() {
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("      [T]" + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints the todo task's details, including its type (T for todo), number, completion status, and name.
     * Overrides the printTask method in the Task class.
     */
    @Override
    public void printTask() {
        System.out.print("[T]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name);
    }
}
