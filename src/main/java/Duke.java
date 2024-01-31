import java.util.Scanner;

public class Duke {
    public static void printBorder() {
        System.out.println("\t--------------------------------------------------");
    }

    public static void printReply (String input) {
        printBorder();
        System.out.println("\t" + input);
        printBorder();
    }

    public static void main(String[] args) {
        String welcome = "Hello! I'm Misty\n"
                + "\tWhat can I do for you?";

        String bye = "Bye! Hope to see you again soon!";

        printReply(welcome);
        List itemList = new List();

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();
            switch (input) {
            case "list":
                printBorder();
                System.out.println("\tHere are the tasks in your list:");
                itemList.listAll();
                printBorder();
                break;
            case "bye":
                break;
            default:
                printBorder();
                if (input.contains("unmark")) {
                    int index;
                    index = Integer.parseInt(input.substring(7));
                    itemList.unmark(index);
                } else if (input.contains("mark")) {
                    int index;
                    index = Integer.parseInt(input.substring(5));
                    itemList.mark(index);
                } else {
                    itemList.add(input);
                    printBorder();
                    break;
                }
                printBorder();
            }

            if (input.equals("bye")) {
                printReply(bye);
                break;
            }
        }
    }
}
