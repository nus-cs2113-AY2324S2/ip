package command;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Class to process the user's inputs.
 */
public class UserInput {

    public final static String LIST_COMMAND = "list";
    public final static String BYE_COMMAND = "bye";
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public final static String TODO_COMMAND = "todo";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    public final static String DELETE_COMMAND = "delete";
    public final static String FIND_COMMAND = "find";
    public static final String LINE_BREAK = "----------------------";

    public static Scanner in = new Scanner(System.in);

    /**
     * Main loop for interaction with the chat bot.
     * Loop continues until the user inputs the bye command.
     */
    public static void loopInterface() {
        String userInput = in.next();

        System.out.println(LINE_BREAK);

        while (!userInput.equalsIgnoreCase("bye")) {
            UserInput.parseInput(userInput);
            System.out.println(LINE_BREAK);
            userInput = in.next();
        }
    }

    /**
     * Parses a user's inputs into the relevant methods.
     * 
     * @param input Command given by the user.
     */
    public static void parseInput(String input) {

        int taskId;

        switch (input.toLowerCase()) {

        case LIST_COMMAND:
            TaskList.printLists();
            break;

        case BYE_COMMAND:
            break;

        case MARK_COMMAND:
            try {
                taskId = in.nextInt();
                TaskList.markTask(taskId);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case UNMARK_COMMAND:
            try {
                taskId = in.nextInt();
                TaskList.unmarkTask(taskId);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case TODO_COMMAND:
            input = in.nextLine();
            TaskList.addTodo(input);
            break;

        case DEADLINE_COMMAND:
            input = in.nextLine();
            TaskList.addDeadline(input);
            break;

        case EVENT_COMMAND:
            input = in.nextLine();
            TaskList.addEvent(input);
            break;

        case DELETE_COMMAND:
            try {
                taskId = in.nextInt();
                TaskList.deleteTask(taskId);
            } catch (InputMismatchException e) {
                System.out.println("A number please");
            }
            break;

        case FIND_COMMAND:
            try {
                input = in.nextLine().trim();
                TaskList.findTasks(input);
            } catch (JohnException e) {
                System.out.println("Invalid query");
            }
            break;

        default:
            System.out.println("Please input a valid command");
            break;
        }
    }

}
