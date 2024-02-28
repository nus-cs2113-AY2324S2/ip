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

    public static void addEventMessage (Event description) {
        Omoh.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + description.description + " (from: " + description.from
                + " to: " + description.to + ")" );
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")" ;
    }
}
