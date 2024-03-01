package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task in the chatbot application.
 * A deadline task includes a due date in addition to the base task properties.
 */
public class Deadline extends Task {
    protected LocalDate by; // The deadline date for the task
    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM dd uuuu", Locale.ENGLISH);

    /**
     * Constructs a new Deadline task with specified name, task number, and deadline date.
     *
     * @param name The name or description of the deadline task.
     * @param hasDone The completed status.
     * @param by The deadline date for the task.
     */
    public Deadline(String name, boolean hasDone, LocalDate by) {
        super(name, hasDone);
        this.by = by;
    }

    /**
     * Marks this deadline task as completed.
     * Overrides the markedTask method in the Task class to include deadline-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsComplete() {
        super.markTaskAsComplete();
        System.out.println("      [D]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this deadline task as not completed.
     * Overrides the unmarkedTask method in the Task class to include deadline-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsIncomplete() {
        super.markTaskAsIncomplete();
        System.out.println("      [D]" + "[ ] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints the deadline task's details, including its type (D for deadline), number, completion status, name, and due date.
     * Overrides the printTask method in the Task class to include the due date information.
     */
    @Override
    public void printTask() {
        System.out.print("[D]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name + " (by: " + by.format(dTF) +")");
    }

    @Override
    public String toString() {
        return "D | " + (hasDone? 1 : 0) + " | " + name + " | " + by.toString();
    }
}
