import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class to parse and handle user's command appropriately.
 */
public class Parser {
    /**
     * Processes user's command.
     *
     * @param userCommand A string containing user's command.
     * @param tasks An array list with tasks added by user.
     */
    public static void handleCommand(String userCommand, ArrayList<Task> tasks) {
        final int NUMBER_OF_COMPONENTS = 4;
        String[] arrayOfCommand = new String[NUMBER_OF_COMPONENTS];
        Scanner in = new Scanner(System.in);

        while (!userCommand.equals("bye")) {
            String[] words = userCommand.split(" ");
            try {
                switch (words[0]) {
                case "list":
                    TaskList.printTasks(tasks);
                    userCommand = in.nextLine();
                    break;
                case "unmark":
                case "mark":
                    TaskList.handleMarkAndUnmarkRequest(userCommand, arrayOfCommand, tasks, tasks.size());
                    userCommand = in.nextLine();
                    break;
                case "delete":
                    TaskList.removeTask(tasks, Integer.parseInt(words[1]) - 1);
                    System.out.println(Messages.ITEM_REMOVED_MESSAGE);
                    System.out.println(Messages.NUMBER_OF_TASKS_MESSAGE_FRONT + tasks.size()
                            + Messages.NUMBER_OF_TASKS_MESSAGE_BACK);
                    userCommand = in.nextLine();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    TaskList.handleTodoDeadlineAndEvent(userCommand, arrayOfCommand, tasks);
                    System.out.println(Messages.NUMBER_OF_TASKS_MESSAGE_FRONT + tasks.size()
                            + Messages.NUMBER_OF_TASKS_MESSAGE_BACK);
                    userCommand = in.nextLine();
                    break;
                case "find":
                    TaskList.findKeyword(tasks, words[1]);
                    userCommand = in.nextLine();
                default:
                    System.out.println(Messages.REQUESTS_FORMAT);
                    userCommand = in.nextLine();
                }
            } catch (IndexOutOfBoundsException e) {
                switch (words[0]) {
                case "delete":
                    System.out.println(Messages.DELETE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "todo":
                    System.out.println(Messages.TODO_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    System.out.println(Messages.DEADLINE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "event":
                    System.out.println(Messages.EVENT_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                default:
                    break;
                }
            }
        }
    }
}
