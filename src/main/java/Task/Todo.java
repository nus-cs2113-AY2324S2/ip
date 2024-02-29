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
    public Todo(String name, boolean hasDone) {
        super(name, hasDone);
    }

    /**
     * Marks this todo task as completed.
     * Overrides the markedTask method in the Task class to include todo-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsComplete() {
        super.markTaskAsComplete();
        System.out.println("      [T]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this todo task as not completed.
     * Overrides the unmarkedTask method in the Task class to include todo-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsIncomplete() {
        super.markTaskAsIncomplete();
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

    @Override
    public String toString() {
        return "T | " + (hasDone? 1 : 0) + " | " + name;
    }
}
