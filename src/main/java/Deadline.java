public class Deadline extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static int addDeadline(Task[] tasks, String userInput, int index) {
        String[] split = userInput.split("/");
        tasks[index] = new Deadline(split[0].substring(9), split[1]);
        System.out.println(LINE_SEPARATOR);
        System.out.println(ADDED_MESSAGE + tasks[index]);
        index++;
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
        return index;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.substring(3) + ")";
    }

}
