import java.util.Scanner;

public class Adam {
    static final String HORIZONTAL_LINE = "_____________________________"
            + "_______________________________\n";

    public static void main(String[] args) {
        String logo = "              _                 \n"
                + "     /\\      | |                \n"
                + "    /  \\   __| | __ _ _ __ ___  \n"
                + "   / /\\ \\ / _` |/ _` | '_ ` _ \\ \n"
                + "  / ____ \\ (_| | (_| | | | | | |\n"
                + " /_/    \\_\\__,_|\\__,_|_| |_| |_|\n";

        String greeting = HORIZONTAL_LINE
                + "Hello! I'm Adam\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;

        System.out.println("Hello from\n" + logo + greeting);

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();
            System.out.print(HORIZONTAL_LINE);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
                break;
            }

            System.out.println(input + "\n" + HORIZONTAL_LINE);
        }

        sc.close();
    }

}
