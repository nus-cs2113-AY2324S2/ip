package parser;

import baron.Baron;
import task.TaskList;

public class Parser {
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static void parseInput(String input, TaskList tasks) throws NullPointerException {
        String[] inputArray = input.split(" ", 2);
        if (input.equals(EXIT_COMMAND)) {
            Baron.printExitMessage();
            System.exit(0);
        } else if (input.equals(LIST_COMMAND)) {
            tasks.displayTasksList();
        } else if (input.startsWith(TODO_COMMAND)) {
            tasks.addTask(TODO_COMMAND, inputArray);
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            tasks.addTask(DEADLINE_COMMAND, inputArray);
        } else if (input.startsWith(EVENT_COMMAND)) {
            tasks.addTask(EVENT_COMMAND, inputArray);
        } else if (input.startsWith(MARK_COMMAND)) {
            int taskIndex = Integer.parseInt(inputArray[1]) - 1;
            tasks.markTask(taskIndex);
        } else if (input.startsWith(UNMARK_COMMAND)) {
            int taskIndex = Integer.parseInt(inputArray[1]) - 1;
            tasks.unmarkTask(taskIndex);
        } else {
            System.out.println("Sorry but that is not a valid command. Please enter a valid command.");
        }
    }
}

