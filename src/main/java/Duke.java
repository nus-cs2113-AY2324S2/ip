import java.util.Scanner;

public class Duke {
    public static void printReply (String input) {
        System.out.println("\t--------------------------------------------------");
        System.out.println("\t" + input);
        System.out.println("\t--------------------------------------------------");
    }

    public static void main(String[] args) {
        String welcome = "Hello! I'm Misty\n"
                + "\tWhat can I do for you?";
        String bye = "Bye. Hope to see you again soon!";

        printReply(welcome);

        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                printReply(bye);
                break;
            } else {
                printReply(input);
            }
        }
    }
}
