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
        String[] tasks = new String[100]; 
        int taskCount = 0; 

        do {
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            if (line.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = line;
                System.out.println("added: " + line + "\n"
                        + "____________________________________________________________");
                taskCount++;
            }
        } while (!line.equals("bye"));

        System.out.println(" Bye. Hope to see you again soon!\n" + 
                "____________________________________________________________\n" + 
                "\n" + logo);
        in.close();
    }
}
