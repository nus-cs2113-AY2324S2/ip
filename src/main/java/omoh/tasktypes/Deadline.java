package omoh.tasktypes;

import omoh.Omoh;

import omoh.customexceptions.IllegalDeadlineInput;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        //parent class sets String description and bool isDone
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline extractTaskAndDueDate (String input) throws IllegalDeadlineInput {
        Deadline taskAndDeadlineString = new Deadline("random","random");
        //splits string according to /by keyword
        String[] parts = input.split("/by");
        //if array is not size 2, means that we are missing inputs
        if (parts.length != 2) {
            throw new IllegalDeadlineInput();
        }
        //extracts task portion from input, after deadline keyword
        taskAndDeadlineString.description = parts[0].substring("deadline".length()).trim();
        //extracts deadline portion from input
        taskAndDeadlineString.by = parts[1].trim();
        if (taskAndDeadlineString.description.isEmpty() || taskAndDeadlineString.by.isEmpty()) {
            throw new IllegalDeadlineInput();
        }
        return taskAndDeadlineString;
    }

    public String getBy() {
        return by;
    }

    public static void printDeadline (Deadline description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + description.description + " (by: " + description.by + ")" );
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }


    public static void addDeadline (String input) {
        try {
            Deadline extractedInfo = Deadline.extractTaskAndDueDate(input);
            Task.tasks[totalTasks] = new Deadline(extractedInfo.description, extractedInfo.by);
            Task.totalTasks++;
            Deadline.printDeadline(extractedInfo);
        } catch (IllegalDeadlineInput e) {
            System.out.println("Please enter non empty Input for Deadline in format [task] [/by] " +
                    "example: deadline hang clothes /by tomorrow");
        }
    }
}
