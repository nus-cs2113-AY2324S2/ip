import java.util.Scanner;

public class Jeff {
    private static void printIndented(String s) {
        System.out.println("    " + s);
    }
    public static void main(String[] args) {
        String divider = "____________________________________________________________";
        Scanner in = new Scanner(System.in);

        printIndented(divider);
        printIndented("Hello! I'm Jeff");
        printIndented("What can I do for you?");
        printIndented(divider);

        while (true) {
            String line = in.nextLine();
            switch (line) {
            case "list":
                printIndented(divider);
                printIndented("list");
                printIndented(divider);
                break;
            case "blah":
                printIndented(divider);
                printIndented("blah");
                printIndented(divider);
                break;
            case "bye":
                printIndented(divider);
                printIndented("Bye. Hope to see you again soon!");
                printIndented(divider);
                return;
            default:
                printIndented(divider);
                printIndented("Invalid command.");
                printIndented(divider);
                break;
            }
        }
    }
}
