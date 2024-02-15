
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

//        switch (prompt) {
//        case "bye":
//            stillGoing = false;
//            printThis("Bye. Hope to see you again soon! Mr. Tickles will miss you.");
//        case "list":
//            printList();
//        }

        if (prompt.equals("bye")) {
            stillGoing = false;
            printThis("Bye. Hope to see you again soon! Mr. Tickles will miss you.");
        } else if (prompt.equals("list")) {
            printList();
        } else if (prompt.length() >= 8 && prompt.substring(0,6).equals("unmark")) {
            //kind of ugly in my opinion, would like tips on how to avoid repeating code here but can't think of a way that
            //avoids magic numbers
            int taskNo;
            try {
                taskNo = Integer.parseInt(prompt.substring(7,8));
            } catch (NumberFormatException e) {
                taskNo = Integer.MAX_VALUE;
                System.out.println("This task number is not in your list");
            }
            if (taskNo <= totalTasks) {
                list.get(taskNo - 1).markAsUndone();
                printThis("OK, I've marked this task as not done yet: \n " + list.get(taskNo-1).type() + "[ ] " + list.get(taskNo - 1).getDescription());
            } else {
                printThis("This task number is not in your list");
            }
        } else if (prompt.length() >= 6 && prompt.substring(0,4).equals("mark")) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(prompt.substring(5));
            } catch (NumberFormatException e) {
                taskNo = Integer.MAX_VALUE;
                System.out.println("This task number is not in your list");
            }
            if (taskNo <= totalTasks) {
                list.get(taskNo - 1).markAsDone();
                printThis("Nice! I've marked this task as done: \n " + list.get(taskNo-1).type() + "[X] " + list.get(taskNo - 1).getDescription());
            } else {
                printThis("This task number is not in your list");
            }
        } else if (prompt.equals("mark") || prompt.equals("unmark")) {
            printThis("Please specify which task number to mark/unmark");
        } else {
            addToList(prompt);
        }

    }


    // Prints the tasks in the list as well as their status icon.
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list) {
            System.out.println(counter + "." + t.type() + t.toString());
            counter += 1;
        }
    }

    // Creates a new Task from the inputted string, and adds it to our list.
    public static void addToList(String str) {
        Task task;
        if (str.contains("deadline")) {
            try {
                String[] split = str.substring(9).split(" /by ");
                task = new Deadline(split[0], split[1]);
            } catch (Exception e) {
                printThis("Need to specify the deadline using /by");
                task = null;
            }

        } else if (str.contains("event")) {
            try {
                String[] split = str.substring(6).split(" /to | /from ");
                task = new Event(split[0], split[1], split[2]);
            } catch (Exception e) {
                printThis("Need to specify the time of the event using /to and /from");
                task = null;
            }
        } else if (str.contains("todo")) {
            try {
                task = new Todo(str.substring(5));
            } catch (Exception e) {
                printThis("Description of a todo cannot be empty!");
                task = null;
            }
        } else {
            //task = new Todo(str);
            printThis("Task is not in an accepted format.");
            task = null;
        }

        if (task != null) {
            list.add(task);
            totalTasks += 1;
            System.out.println(task.getStatusIcon());
            printThis("Got it. I've added this task: \n \t" + task.type() + task.toString() + "\nNow you have " + totalTasks +
                    " task" + pluralChecker() + " in the list.");
        }

    }

    public static String pluralChecker() {
        if (totalTasks == 1) {
            return "";
        } else {
            return "s";
        }
    }

    // Abstracts out the printing with line breaks.
    public static void printThis(String str) {
        printLine();
        System.out.println(str);
        printLine();
    }

    public static void printLine() {
        System.out.println("_____________________________________________________________");
    }
}
