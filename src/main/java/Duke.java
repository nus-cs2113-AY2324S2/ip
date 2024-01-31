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
        String bye = "Bye. Hope to see you again soon!";

        printReply(welcome);

        List itemList = new List();

        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            switch (input) {
            case "list":
                printBorder();
                itemList.listAll();
                printBorder();
                break;
            case "bye":
                break;
            default:
                printBorder();
                itemList.add(input);
                printBorder();
                break;
            }

            if (input.equals("bye")) {
                printReply(bye);
                break;
            }
        }
    }
}
