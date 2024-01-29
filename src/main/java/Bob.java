import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    // todo: better indexing for the list of items
    // todo: better display of statusicon

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

        List<Task> list = new ArrayList<>();

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();

            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + "." + list.get(i).getListItem());
                }

                System.out.println("____________________________________________________________");


            } else if (line.startsWith("mark")) {
                String[] lineSplit = line.split(" ");
                list.get(Integer.parseInt(lineSplit[1]) - 1).setDone(true);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(Integer.parseInt(lineSplit[1]) - 1).getListItem());
                System.out.println("____________________________________________________________");


            } else if (line.startsWith("unmark")) {
                String[] lineSplit = line.split(" ");
                list.get(Integer.parseInt(lineSplit[1]) - 1).setDone(false);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(Integer.parseInt(lineSplit[1]) - 1).getListItem());
                System.out.println("____________________________________________________________");

            } else if (line.equals("bye")) {
                break;
            } else {
                Task t = new Task(line);
                list.add(t);

                System.out.println("____________________________________________________________");
                System.out.println("added: " + t.getDescription());
                System.out.println("____________________________________________________________\n");
            }


        }


        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
