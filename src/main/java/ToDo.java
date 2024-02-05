public class ToDo extends Task{

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String ADDED_MESSAGE = "Got it. I've added this task: \n";
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public static int addToDo(Task[] tasks, String userInput, int index) {
        String split = userInput.substring(5);
        tasks[index] = new ToDo(split);
        System.out.println(LINE_SEPARATOR);
        System.out.println(ADDED_MESSAGE + tasks[index]);
        index++;
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
        return index;
    }
}
