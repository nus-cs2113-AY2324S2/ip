package Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an event task in the chatbot application.
 * An event task includes start and end times in addition to the base task properties.
 */
public class Event extends Task{
    protected LocalDate from; // The start date of the event
    protected LocalDate to; // The end date of the event
    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM dd uuuu", Locale.ENGLISH);

    /**
     * Constructs a new Event task with specified name, task number, start time, and end time.
     * Inherits name and task number from the Task class and adds time-specific information.
     *
     * @param name The name or description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String name, boolean hasDone, LocalDate from, LocalDate to) {
        super(name, hasDone);
        this.from = from; // The start time of the event
        this.to = to; // The end time of the event
    }

    /**
     * Marks this event as completed.
     * Overrides the markedTask method in the Task class to include event-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsComplete() {
        super.markTaskAsComplete();
        System.out.println("      [E]" + "[X] " + this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this event as not completed.
     * Overrides the unmarkedTask method in the Task class to include event-specific details in the confirmation message.
     */
    @Override
    public void markTaskAsIncomplete() {
        super.markTaskAsIncomplete();
        System.out.println("      [E]" + "[ ] " + this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Prints the task's details, including its number, completion status, and name.
     */
    @Override
    public void printTask() {
        System.out.print("[E]");
        if (hasDone){
            System.out.print("[X] ");
        }else{
            System.out.print("[ ] ");
        }
        System.out.println(name + " (from: " + from.format(dTF) + " to: " + to.format(dTF) + ")");
    }

    @Override
    public String toString() {
        return "E | " + (hasDone? 1 : 0) + " | " + name + " | " + from.toString() + " - "  + to.toString();
    }
}
