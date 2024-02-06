import java.util.Scanner;

public class Misty {
    public static void printLineOutput() {
        System.out.println("\t--------------------------------------------------");
    }

    public static void printWelcomeMessage() {
        String welcome = "Hello! I'm Misty\n"
                + "\tWhat can I do for you?";

        printLineOutput();
        System.out.println("\t" + welcome);
        printLineOutput();
    }

    public static void printByeMessage() {
        String bye = "Bye! Hope to see you again soon!";

        printLineOutput();
        System.out.println("\t" + bye);
        printLineOutput();
    }

    public static void printUnknownCommand() {
        String bye = "Sorry, unknown command entered!";
        System.out.println("\t" + bye);
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        List itemList = new List();
        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();
            switch (input) {
            case "list":
                printLineOutput();
                itemList.listAll();
                printLineOutput();
                break;
            case "bye":
                break;
            default:
                printLineOutput();
                if (input.startsWith("unmark")) {
                    int index;
                    index = Integer.parseInt(input.substring(7));
                    itemList.unmarkTask(index);
                } else if (input.startsWith("mark")) {
                    int index;
                    index = Integer.parseInt(input.substring(5));
                    itemList.markTask(index);
                } else if (input.startsWith("todo")) {
                    String description = input.substring(input.indexOf(" ")).trim();
                    itemList.addTodo(description);
                    printLineOutput();
                    break;
                } else if (input.startsWith("deadline")) {
                    String description = input.substring(input.indexOf(" "), input.indexOf("/by")).trim();
                    String by = input.substring(input.indexOf("/by") + 4);
                    itemList.addDeadline(description, by);
                } else if (input.startsWith("event")) {
                    String description = input.substring(input.indexOf(" "), input.indexOf("/from")).trim();
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                    String to = input.substring(input.indexOf("/to") + 3).trim();
                    itemList.addEvent(description, from, to);
                } else {
                    printUnknownCommand();
                }
                printLineOutput();
            }

            if (input.equals("bye")) {
                printByeMessage();
                break;
            }
        }
    }
}
