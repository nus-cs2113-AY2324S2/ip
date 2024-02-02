
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Tickles {

    private static boolean stillGoing = true;
    private static int totalTasks = 0;
    private static List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        String logo =
            "╱╭╮╱╱╱╱╭╮╱╭╮\n" +
            "╭╯╰╮╱╱╱┃┃╱┃┃\n" +
            "╰╮╭╋┳━━┫┃╭┫┃╭━━┳━━╮\n" +
            "╱┃┃┣┫╭━┫╰╯┫┃┃┃━┫━━┫\n" +
            "╱┃╰┫┃╰━┫╭╮┫╰┫┃━╋━━┃\n" +
            "╱╰━┻┻━━┻╯╰┻━┻━━┻━━╯\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");
        while (stillGoing) {
            promptUser();
        }
    }

    // Prompts user for input, and handles the special input cases "bye", "unmark"/"mark", "list".
    public static void promptUser() {
        Scanner in = new Scanner(System.in);
        String prompt = in.nextLine();
        if (prompt.equals("bye")) {
            stillGoing = false;
            printThis("Bye. Hope to see you again soon! Mr. Tickles will miss you.");
        } else if (prompt.equals("list")) {
            printList();
        } else if (prompt.length() == 8 && prompt.substring(0,6).equals("unmark")) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(prompt.substring(7,8));
            } catch (NumberFormatException e) {
                taskNo = Integer.MAX_VALUE;
            }
            if (taskNo <= totalTasks) {
                list.get(taskNo - 1).markAsUndone();
                printThis("OK, I've marked this task as not done yet: \n [ ]" + list.get(taskNo - 1).getDescription());
            }
        } else if (prompt.length() == 6 && prompt.substring(0,4).equals("mark")) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(prompt.substring(5,6));
            } catch (NumberFormatException e) {
                taskNo = Integer.MAX_VALUE;
            }
            if (taskNo <= totalTasks) {
                list.get(taskNo - 1).markAsDone();

                printThis("Nice! I've marked this task as done: \n [X]" + list.get(taskNo - 1).getDescription());
            }
        } else {
            addToList(prompt);
            printThis("added: " + prompt);
        }

    }

    // Prints the tasks in the list as well as their status icon.
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list) {
            System.out.println(counter + ". [" + t.getStatusIcon() + "]" + t.getDescription());
            counter += 1;
        }
    }

    // Creates a new Task from the inputted string, and adds it to our list.
    public static void addToList(String str) {
        Task task = new Task(str);
        list.add(task);
        totalTasks += 1;
        System.out.println(task.getStatusIcon());
    }

    // Abstracts out the printing with line breaks.
    public static void printThis(String str) {
        printLine();
        System.out.println(str);
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
