import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Adam {
    static final String HORIZONTAL_LINE = "_____________________________"
            + "_______________________________\n";

    private static String greeting() {
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

        return "Hello from\n" + logo + greeting;
    }

    public static void main(String[] args) {
        System.out.println(greeting());

        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            input = sc.nextLine();
            System.out.print(HORIZONTAL_LINE);

            switch (input) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
                sc.close();
                return;

            case "list":
                IntStream.rangeClosed(1, tasks.size())
                        .mapToObj(x -> x + ". " + tasks.get(x - 1))
                        .forEach(x -> System.out.println(x));
                break;

            default:
                tasks.add(input);
                System.out.println("added: " + input);
                break;
            }

            System.out.println(HORIZONTAL_LINE);
        }

    }
}
