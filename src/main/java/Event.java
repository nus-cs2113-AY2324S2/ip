import Exceptions.DuckInvalidEventDescriptionException;

public class Event extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    public static int addEvent(Task[] tasks, String userInput, int index){
        try {
            String[] split = userInput.split("/");
            if (split.length != 3 || !split[1].startsWith("from ") || !split[2].startsWith("by ")) { //throws exception if the inputs are not to program specifications
                throw new DuckInvalidEventDescriptionException();
            }
            tasks[index] = new Event(split[0].substring(6), split[1].substring(5), split[2].substring(3));
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks[index]);
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (DuckInvalidEventDescriptionException e) {
            System.out.println("Invalid Event input. Please type in format: event [string] /from [string] /by [string]");
        }

        return index;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + this.by + ")";
    }
}
