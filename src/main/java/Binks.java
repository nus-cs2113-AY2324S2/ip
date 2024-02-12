import java.util.Arrays;
import java.util.Scanner;

public class Binks {
    /**
     * Prints out the greeting message from the chatbot "Binks".
     */
    public static void greetUser() {
        createLineSpacing();
        System.out.println("Hello! I'm Binks.");
        System.out.println("What can I do for you?");
        createLineSpacing();
    }

    /**
     * Prints out the line spacing between inputs and outputs in the console.
     */
    public static void createLineSpacing() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the exit message from the chatbot "Binks" when user inputs "Bye".mark
     */
    public static void exitChatbot() {
        createLineSpacing();
        System.out.println("Bye. Hope to see you again soon!");
        createLineSpacing();
    }

    public static void main(String[] arg) {
        greetUser();
        List list = new List();
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] command = line.split(" ");

            switch (command[0].toLowerCase()) {
            case "list":
                list.getList();
                break;

            //when the mark command is given
            case "mark":
                if (command.length == 2) {
                    int taskNumber = Integer.parseInt(command[1]);
                    list.markAsDone(taskNumber);
                } else {
                    createLineSpacing();
                    System.out.println("Please specify which task to mark.");
                    createLineSpacing();
                }
                break;

            //when the unmark command is given
            case "unmark":
                if (command.length == 2) {
                    int taskNumber = Integer.parseInt(command[1]);
                    list.unmarkAsDone(taskNumber);
                } else {
                    createLineSpacing();
                    System.out.println("Please specify which task to unmark.");
                    createLineSpacing();
                }
                break;

            //when the bye command is given
            case "bye":
                exitChatbot();
                return;

            //when there is a new todo to add
            case "todo":
                if (command.length >= 2) {
                    list.addTodo(line.substring(5));
                } else {
                    createLineSpacing();
                    System.out.println("WARNING! The description of todo cannot be empty!");
                    createLineSpacing();
                }
                break;

            //when there is a new deadline to add
            case "deadline":
                if (command.length >= 2) {
                    int bySeparator = line.indexOf("/by ");
                    if (bySeparator != -1) {
                        String deadline = line.substring(bySeparator + 4);
                        String task = line.substring(9, bySeparator);
                        list.addDeadline(task + " (by: " + deadline + ")");
                    } else {
                        createLineSpacing();
                        System.out.println("WARNING! Please indicate a deadline using /by.");
                        createLineSpacing();
                    }
                } else {
                    createLineSpacing();
                    System.out.println("Invalid deadline command.");
                    createLineSpacing();
                }
                break;

            //when there is a new event to add
            case "event":
                if (command.length >= 2) {
                    int fromSeparator = line.indexOf("/from ");
                    int toSeparator = line.indexOf("/to ");
                    if (fromSeparator != -1 && toSeparator != -1) {
                        String startTime = line.substring(fromSeparator + 6, toSeparator);
                        String endTime = line.substring(toSeparator + 4);
                        String task = line.substring(6, fromSeparator);
                        list.addEvent(task + " (from: " + startTime + "to: " + endTime + ")");
                    } else {
                        createLineSpacing();
                        System.out.println("OOPS!!! Please specify start and end times using /from and /to.");
                        createLineSpacing();
                    }
                } else {
                    createLineSpacing();
                    System.out.println("Invalid event command.");
                    createLineSpacing();
                }
                break;

            default:
                createLineSpacing();
                System.out.println("This is not a valid command!");
                createLineSpacing();
            }
        }
    }
}
