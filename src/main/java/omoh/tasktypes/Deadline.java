package omoh.tasktypes;

import omoh.Omoh;

import omoh.Parser;
import omoh.customexceptions.IllegalDeadlineInput;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        //parent class sets String description and bool isDone
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task, including its task status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }

    /**
     * Prints a message indicating that a Deadline task has been added.
     *
     * @param description The Deadline task that has been added.
     */
    public static void printDeadline (Deadline description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + description.description + " (by: " + description.by + ")" );
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }


    /**
     * Adds a Deadline task based on the input string.
     *
     * @param input The input string containing the Deadline task description and due date.
     */
    public static void addDeadline (String input) {
        try {
            Deadline extractedInfo = Parser.extractTaskAndDueDate(input);
            Task.tasks.add(new Deadline(extractedInfo.description, extractedInfo.by));
            Task.totalTasks++;
            Deadline.printDeadline(extractedInfo);
        } catch (IllegalDeadlineInput e) {
            System.out.println("Please enter non empty Input for Deadline in format [task] [/by] " +
                    "example: deadline hang clothes /by tomorrow");
        }
    }
}
