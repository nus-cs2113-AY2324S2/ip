import Exceptions.DuckInvalidToDoDescriptionException;

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
        try {
            String split = userInput.substring(4);
            if (split.trim().isEmpty()) {
                throw new DuckInvalidToDoDescriptionException();
            }
            tasks[index] = new ToDo(split);
            System.out.println(LINE_SEPARATOR);
            System.out.println(ADDED_MESSAGE + tasks[index]);
            index++;
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(LINE_SEPARATOR);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Too many items! Max 100 items");
        } catch (DuckInvalidToDoDescriptionException e) {
            System.out.println("Invalid ToDo input. Please type in format: todo [string]");
        }
        return index;
    }
}
