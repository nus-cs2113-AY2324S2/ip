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

    public static Deadline extractTaskAndDueDate (String input) {
        Deadline taskAndDeadlineString = new Deadline("random","random");
        //splits string according to /by keyword
        String[] parts = input.split("/by");
        //extracts task portion from input, after deadline keyword
        taskAndDeadlineString.description = parts[0].substring("deadline".length()).trim();
        //extracts deadline portion from input
        taskAndDeadlineString.by = parts[1].trim();
        return taskAndDeadlineString;
    }

    public String getBy() {
        return by;
    }

    public static void printDeadline (Deadline description) {
        Omoh.printHorizontalLine();
        System.out.print("     ");
        System.out.println("Got it. I've added this task:");
        System.out.print("       ");
        System.out.println("[D][ ] " + description.description + " (by: " + description.by + ")" );
        System.out.print("     ");
        System.out.println("Now you have " + List.totalTasks + " tasks in the list.");
        Omoh.printHorizontalLine();
    }
}
