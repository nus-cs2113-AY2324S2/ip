import java.util.Scanner;

public class Parser {
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static void parseInput(String input, TaskList tasks) {
        String[] inputArray = input.split(" ", 2);
        if (input.equals(LIST_COMMAND)) {
            tasks.displayTasksList();
        } else if (input.startsWith(TODO_COMMAND)) {
            tasks.addTask(new ToDo(inputArray[1]));
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            int dividerPosition = inputArray[1].indexOf('/');
            String description = inputArray[1].substring(0, dividerPosition);
            String deadline = inputArray[1].substring(dividerPosition + 4);
            tasks.addTask(new Deadline(description, deadline));
        } else if (input.startsWith(EVENT_COMMAND)) {
            int firstDividerPosition = inputArray[1].indexOf('/');
            int secondDividerPosition = inputArray[1].indexOf("to");
            String description = inputArray[1].substring(0, firstDividerPosition);
            String eventStart = inputArray[1].substring(firstDividerPosition + 6, secondDividerPosition - 1);
            String eventEnd = inputArray[1].substring(secondDividerPosition + 3);
            tasks.addTask(new Event(description, eventStart, eventEnd));
        } else if (input.startsWith(MARK_COMMAND)) {
            int taskIndex = Integer.parseInt(inputArray[1]) - 1;
            tasks.markTask(taskIndex);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            int taskIndex = Integer.parseInt(inputArray[1]) - 1;
            tasks.unmarkTask(taskIndex);
        } else {
            System.out.println("Sorry but that is not a valid command. Please enter a valid command");
        }
    }
}

