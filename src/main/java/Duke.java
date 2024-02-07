import java.util.Scanner;

public class Duke {
    public static final String LINE_SEPARATOR = "--------------------------------------";
    public static void main(String[] args) {
        String name = "Stella";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            Task.responseToCommand(line);
            line = in.nextLine();
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }
}
