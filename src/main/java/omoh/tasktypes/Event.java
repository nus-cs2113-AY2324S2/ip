package omoh.tasktypes;

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

    public static Event extractEvent (String input) throws IllegalEventInput {
        Event eventDetails  = new Event("random","random", "random");
        //splits string according to /by keyword
        String[] parts = input.split("/from|/to");
        if (parts.length < 3) {
            throw new IllegalEventInput();
        }
        //extracts task portion from input, after deadline keyword
        eventDetails.description = parts[0].substring("event".length()).trim();
        //extracts from and to portion from input
        eventDetails.from = parts[1].trim();
        eventDetails.to = parts[2].trim();
        if (eventDetails.to.isEmpty() || eventDetails.from.isEmpty()) {
            throw new IllegalEventInput();
        }
        return eventDetails;
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
            Event extractedInfo = extractEvent(input);
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
