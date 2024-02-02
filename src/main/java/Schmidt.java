import java.util.ArrayList;
import java.util.Scanner;

public class Schmidt {
    public static void main(String[] args) {
        String logo = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
                "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
                "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
                "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
                "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
                "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello from\n" + logo);

        System.out.println("------------------------------------------------------------\n" +
                " Hello! I'm Schmidt\n" +
                " What can I do for you?\n" +
                "------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\t -> ");

            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("------------------------------------------------------------\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "------------------------------------------------------------");
                break;
            } else if (input.equals("list")) {
                System.out.println("------------------------------------------------------------\n" +
                        " Here are the tasks in your list:");

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
                }

                System.out.println("------------------------------------------------------------");
            } else {
                tasks.add(new Task(input));

                System.out.println("------------------------------------------------------------\n" +
                        "added " + input + "\n" +
                        "------------------------------------------------------------");
            }
        }
    }
}
