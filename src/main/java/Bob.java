import java.util.ArrayList;
import java.util.List;
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

        List<String> list = new ArrayList<>();

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();

            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }

                System.out.println("____________________________________________________________");

                continue;

            } else if (line.equals("bye")) {
                break;
            }

            list.add(line);

            System.out.println("____________________________________________________________");
            System.out.println("added: " + line);
            System.out.println("____________________________________________________________\n");
        }


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
