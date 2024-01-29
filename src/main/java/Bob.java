import java.util.Scanner;


public class Bob {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\_____ _|__ _\n"
                    + "| |_| | | | | |   \\ \n"
                    + "| |_| | |_| |   O  /\n"
                    + "|____/ \\__,_|_\\__/\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");



        while (true) {
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________\n");
        }


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
