import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Duck";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm " + name
                + "\n" + "What can I do for you?\n" + "\n"
                + "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        while(!userInput.contains("bye")){
            System.out.println("____________________________________________________________"
                    + "\n" + userInput + '\n'
                    + "____________________________________________________________");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
        }
        System.out.println("Bye. Hope to see you again soon!\n"
                + "\n" + "____________________________________________________________");

        }
}
