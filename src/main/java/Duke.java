/*public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}*/
import java.util.Scanner;
public class Duke {
    public static boolean respond(String input) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon! ");
            return false;
        } else {
            System.out.println(input);
            return true;
        }
    }
    public static void main(String[] args) {
        String chatbotName = "Horizon";
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you? ");

        String input;
        Scanner in = new Scanner((System.in));
        do {
            input = in.nextLine();
        } while (respond(input));

    }
}
