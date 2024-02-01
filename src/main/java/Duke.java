import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____  _    _  ____ _____ ___  ____  \n"
                    + "|  _ \\| |  | |/ ___|_   _/ _ \\|  _ \\ \n"
                    + "| | | | |  | | |     | || | | | | | |\n"
                    + "| |_| | |__| | |___  | || |_| | |_| |\n"
                    + "|____/ \\____/ \\____| |_| \\___/|____/ \n";
        System.out.println("Hello from Duke\n" + 
                " Hello! I'm Duke\n" + 
                " What can I do for you?\n" + 
                "____________________________________________________________");

        Scanner in = new Scanner(System.in);
        String line;

        do {
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if (!line.equals("bye")) {
                System.out.println(line);
                System.out.println("____________________________________________________________");
            }
        } while (!line.equals("bye"));

        System.out.println(" Bye. Hope to see you again soon!\n" + 
                "____________________________________________________________\n" + 
                "\n" + logo);
        in.close();
    }
}
