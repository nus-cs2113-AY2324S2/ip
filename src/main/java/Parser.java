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
        String[] arrayOfCommand = new String[4];
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
                    System.out.println("Item above has been removed!");
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "todo":
                case "deadline":
                case "event":
                    TaskList.handleTodoDeadlineAndEvent(userCommand, arrayOfCommand, tasks);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    userCommand = in.nextLine();
                    break;
                case "find":
                    TaskList.findKeyword(tasks, words[1]);
                    userCommand = in.nextLine();
                default:
                    System.out.println(Guide.REQUESTS_FORMAT);
                    userCommand = in.nextLine();
                }
            } catch (IndexOutOfBoundsException e) {
                switch (words[0]) {
                case "delete":
                    System.out.println(Guide.DELETE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "todo":
                    System.out.println(Guide.TODO_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "deadline":
                    System.out.println(Guide.DEADLINE_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                case "event":
                    System.out.println(Guide.EVENT_REQUEST_FORMAT);
                    userCommand = in.nextLine();
                    break;
                default:
                    break;
                }
            }
        }
    }
}
