public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public static Event extractEvent (String input) {
        Event eventDetails  = new Event("random","random", "random");
        //splits string according to /by keyword
        String[] parts = input.split("/from|/to");
        //extracts task portion from input, after deadline keyword
        eventDetails.description = parts[0].substring("event".length()).trim();
        //extracts from and to portion from input
        eventDetails.from = parts[1].trim();
        eventDetails.to = parts[2].trim();
        return eventDetails;
    }


    public static void addEventMessage (Event description) {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("Got it. I've added this task:");
        System.out.print("       ");
        System.out.println("[E][ ] " + description.description + " (from: " + description.from
                + " to: " + description.to + ")" );
        System.out.print("     ");
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }

    public static void addEvent (String input) {
        Event extractedInfo = extractEvent(input);
        Task.tasks[Task.totalTasks] = new Event(extractedInfo.description, extractedInfo.from, extractedInfo.to);
        Task.totalTasks++;
        Event.addEventMessage(extractedInfo);
    }
}
