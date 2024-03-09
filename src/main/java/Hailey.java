import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo +
                "____________________________________________________________\n" +
                "Hello! I'm Hailey\nWhat can I do for you?\n" +
                "____________________________________________________________");

        new Scanner(System.in).nextLine();

        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}

