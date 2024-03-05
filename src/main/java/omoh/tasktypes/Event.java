package omoh.tasktypes;

import omoh.Parser;
import omoh.customexceptions.IllegalEventInput;
import omoh.Omoh;


public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Prints a message indicating that an event task has been added.
     *
     * @param description The Event object containing the details of the added event task.
     */
    public static void addEventMessage (Event description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + description.description + " (from: " + description.from
                + " to: " + description.to + ")" );
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

    /**
     * Adds an event task based on the input string.
     *
     * @param input The input string containing the event details.
     */
    public static void addEvent (String input) {
        try {
            Event extractedInfo = Parser.extractEvent(input);

            Task.tasks.add(new Event(extractedInfo.description, extractedInfo.from, extractedInfo.to));
            Task.totalTasks++;
            Event.addEventMessage(extractedInfo);
        } catch (IllegalEventInput e) {
            System.out.println("Invalid Input for Event. Ensure that format follows " +
                    "event [task] [/from] [/to]");
        }
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representing the Event object, including its task status, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")" ;
    }
}
