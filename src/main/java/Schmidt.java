import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for Schmidt, a multi-functional chatbot
 */
public class Schmidt {
    /**
     * This is the main method which makes use of Schmidt class.
     * @param args Unused.
     */
    public static void main(String[] args) {
        String LOGO = "░██████╗░█████╗░██╗░░██╗███╗░░░███╗██╗██████╗░████████╗\n" +
                "██╔════╝██╔══██╗██║░░██║████╗░████║██║██╔══██╗╚══██╔══╝\n" +
                "╚█████╗░██║░░╚═╝███████║██╔████╔██║██║██║░░██║░░░██║░░░\n" +
                "░╚═══██╗██║░░██╗██╔══██║██║╚██╔╝██║██║██║░░██║░░░██║░░░\n" +
                "██████╔╝╚█████╔╝██║░░██║██║░╚═╝░██║██║██████╔╝░░░██║░░░\n" +
                "╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═════╝░░░░╚═╝░░░";
        ArrayList<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello from\n" + LOGO);

        System.out.println("------------------------------------------------------------\n" +
                " Hello! I'm Schmidt\n" +
                " What can I do for you?\n" +
                "------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\t-> ");

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
                markTaskHelper(tasks, input, true);
            } else if (input.startsWith("unmark")) {
                markTaskHelper(tasks, input, false);
            } else {
                tasks.add(new Task(input));

                System.out.println("------------------------------------------------------------\n" +
                        "added " + input + "\n" +
                        "------------------------------------------------------------");
            }
        }
    }

    /**
     * This is a helper method to mark or unmark a task as done
     * @param tasks The list of tasks
     * @param input The user input
     * @param isDone A boolean to indicate if the task is done
     */
    public static void markTaskHelper(ArrayList<Task> tasks, String input, boolean isDone) {
        String[] tokens = input.split(" ");

        if (tokens.length != 2) {
            System.out.println("------------------------------------------------------------\n" +
                    " Please specify the task number to mark as done\n" +
                    "------------------------------------------------------------");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("------------------------------------------------------------\n" +
                    " Please specify the task number to mark as done\n" +
                    "------------------------------------------------------------");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("------------------------------------------------------------\n" +
                    " Task number out of range\n" +
                    "------------------------------------------------------------");
            return;
        }

        if (isDone) {
            tasks.get(index).markAsDone();

            System.out.println("------------------------------------------------------------\n" +
                    " Nice! I've marked this task as done:\n" +
                    "   " + tasks.get(index) + "\n" +
                    "------------------------------------------------------------");
        } else {
            tasks.get(index).unmarkAsDone();

            System.out.println("------------------------------------------------------------\n" +
                    " Nice! I've unmarked this task as done:\n" +
                    "   " + tasks.get(index) + "\n" +
                    "------------------------------------------------------------");
        }
    }
}
