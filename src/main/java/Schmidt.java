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
            } else if (input.startsWith("mark")) {
                String[] tokens = input.split(" ");

                if (tokens.length != 2) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Please specify the task number to mark as done\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                int index;
                try {
                    index = Integer.parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Please specify the task number to mark as done\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                if (index < 0 || index >= tasks.size()) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Task number out of range\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                tasks.get(index).markAsDone();

                System.out.println("------------------------------------------------------------\n" +
                        " Nice! I've marked this task as done:\n" +
                        "   " + tasks.get(index) + "\n" +
                        "------------------------------------------------------------");
            } else if (input.startsWith("unmark")) {
                String[] tokens = input.split(" ");

                if (tokens.length != 2) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Please specify the task number to unmark as done\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                int index;
                try {
                    index = Integer.parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Please specify the task number to unmark as done\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                if (index < 0 || index >= tasks.size()) {
                    System.out.println("------------------------------------------------------------\n" +
                            " Task number out of range\n" +
                            "------------------------------------------------------------");
                    continue;
                }

                tasks.get(index).unmarkAsDone();

                System.out.println("------------------------------------------------------------\n" +
                        " Nice! I've unmarked this task as done:\n" +
                        "   " + tasks.get(index) + "\n" +
                        "------------------------------------------------------------");
            } else {
                tasks.add(new Task(input));

                System.out.println("------------------------------------------------------------\n" +
                        "added " + input + "\n" +
                        "------------------------------------------------------------");
            }
        }
    }
}
