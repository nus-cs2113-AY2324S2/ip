package Task;

/**
 * Represents an event task in the chatbot application.
 * An event task includes start and end times in addition to the base task properties.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with specified name, task number, start time, and end time.
     * Inherits name and task number from the Task class and adds time-specific information.
     *
     * @param name The name or description of the event.
     * @param taskNo The unique number assigned to the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String name, int taskNo, String from, String to) {
        super(name, taskNo);
        this.from = from; // The start time of the event
        this.to = to; // The end time of the event
    }

    /**
     * Marks this event as completed.
     * Overrides the markedTask method in the Task class to include event-specific details in the confirmation message.
     */
    @Override
    public void markedTask() {
        this.hasDone=true;
        System.out.println("    " + "--------------");
        System.out.println("    " + "Nice! I've marked this task as done:");
        System.out.println("      [E]" + "[X] "+this.name);
        System.out.println("    " + "--------------");
    }

    /**
     * Marks this event as not completed.
     * Overrides the unmarkedTask method in the Task class to include event-specific details in the confirmation message.
     */
    @Override
    public void unmarkedTask() {
        this.hasDone=false;
        System.out.println("    " + "--------------");
        System.out.println("    " + "OK, I've marked this task as not done yet:");
        System.out.println("      [E]" + "[ ] "+this.name);
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
        System.out.println(name + " (from: " + from+" to: "+ to+ ")");
    }
}
