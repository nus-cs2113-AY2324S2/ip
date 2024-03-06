import java.util.Arrays;
import java.util.Scanner;

public class Echo {
    static String break_line = "----------------------------------------";
    private static Task[] list = new Task[1];
    private static int count = 0;
    static final String FILE_PATH = "C:\\Users\\TONY\\Desktop\\CS2113\\MyDukeBot\\docs\\dukebot.txt";


    public void startEchoing() {

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(list, count);
        taskList.initTasks(FILE_PATH);

        while (true) {
            System.out.println("What can I do for you?");
            System.out.println("type 'help' for list of instructions.");

            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            switch (parts[0].toLowerCase()) {

            case "bye":
                Bye.sayGoodbye();
                break;

            case "list":
                taskList.listTasks();

                break;

            case "mark":
                if (parts.length > 1) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        taskList.markTask(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println(break_line);
                        System.out.println("Please enter a valid task number.");
                        System.out.println(break_line);
                    }
                } else {
                    System.out.println(break_line);
                    System.out.println("Please specify which task to mark as done.");
                    System.out.println(break_line);
                }
                break;

            case "unmark":
                if (parts.length > 1) {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        taskList.unmarkTask(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println(break_line);
                        System.out.println("Please enter a valid task number.");
                        System.out.println(break_line);
                    }
                } else {
                    System.out.println(break_line);
                    System.out.println("Please specify which task to mark as not done.");
                    System.out.println(break_line);
                }
                break;

            case "clearlist":
                taskList.clearList();
                break;

            case "todo":
                String description = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                if (description.isEmpty()) {
                    System.out.println(break_line);
                    System.out.println("ToDo requires a description.");
                    System.out.println(break_line);
                } else {
                    taskList.addTodo(input);
                }
                break;

            case "deadline":
                if (!input.toLowerCase().startsWith("deadline")) {
                    // Handle this as a generic task or provide a specific message
                    System.out.println(break_line);
                    System.out.println("Command not recognized. Please use 'deadline' for deadline tasks.");
                    System.out.println(break_line);
                    break;
                }

                String deadlineInput = input.substring("deadline".length()).trim();
                if (!deadlineInput.contains("/by ")) {
                    System.out.println(break_line);
                    System.out.println("Deadline time is missing. Please use '/by' to specify the deadline.");
                    System.out.println(break_line);
                    break;
                } else {
                    taskList.addDeadline(input);
                }

                break;

            case "event":
                // Example input: event project meeting /from 2/10/2019 2pm /to 2/10/2019 4pm
                if (!input.toLowerCase().startsWith("event")) {
                    // Handle this as a generic task or provide a specific message
                    System.out.println(break_line);
                    System.out.println("Command not recognized. Please use 'event' for event tasks.");
                    System.out.println(break_line);
                    break;
                }

                String eventInput = input.substring("event".length()).trim();
                if (!eventInput.contains("/from ") || !eventInput.contains("/to ")) {
                    System.out.println(break_line);
                    System.out.println("Event time details are missing. Please use '/from' and '/to' to specify the event timing.");
                    System.out.println(break_line);
                    break;
                } else {
                    taskList.addEvent(input);
                }
                break;

            case "delete":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to delete.");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(parts[1]);
                        taskList.deleteTask(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }
                }


            case "help":
                System.out.println(break_line);
                System.out.println("'bye' to exit ");
                System.out.println("'list' to display the list");
                System.out.println("'clearlist' to clear the list ");
                System.out.println("'mark' plus a number to mark that task as done");
                System.out.println("'unmark' plus a number to unmark that task.");
                System.out.println("'help' for list of instructions.");
                System.out.println("'todo [description]' to add a new ToDo task");
                System.out.println("'deadline [description] /by [time]' to add a new Deadline");
                System.out.println("'event [description] /from [start time] /to [end time]' to add a new Event");
                System.out.println("'delete' plus a number to delete that task.");
                System.out.println(break_line);
                break;

            default:

                System.out.println(break_line);
                System.out.println("Sorry, Hiko don't understand that command.");
                System.out.println("Type 'help' for a list of valid commands.");
                System.out.println(break_line);

            }
        }
    }


}