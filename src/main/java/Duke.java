import java.util.Scanner;

public class Duke {

    public static void inputChecker() {
        Scanner in = new Scanner(System.in); // Declared a scanner object
        String line; // Declared a string object to take in user input
        Task[] list; // Declared a list with string data type
        list = new Task[100]; // created a list of 100 elements
        int counter = 0;
        int index;
        Task t;

        // Start of user input
        while (true) {
            line = in.nextLine(); // Takes in user input

            boolean mark = line.trim().toLowerCase().startsWith("mark");
            boolean unmark = line.trim().toLowerCase().startsWith("unmark");

            // User wants to exit
            if (line.equals("bye")) {
                System.out.println("Bye human. Come back soon !");
                break;
            }

            // User wants to display the list of tasks
            else if (line.equalsIgnoreCase("list")) {
                for (Task task : list) {
                    if (task == null){
                        break;
                    }
                    System.out.println(task.index + ". " + task);
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            // User wants to mark or unmark tasks
            else if (mark || unmark) {

                // To mark
                if (mark) {
                     index = Integer.parseInt(line.substring(5));
                     t = list[index - 1];
                     t.isDone =  true;
                    System.out.println("Nice! I've marked this task as done:");
                }

                // To unmark
                else {
                    index = Integer.parseInt(line.substring(7));
                    t = list[index - 1];
                    t.isDone =  false;
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println(t);
                System.out.println("____________________________________________________________");
                continue;
            }

            // Checks which type of task the user wants to do
                if (line.startsWith("todo")) {
                   t = new Todo(line, counter+1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                }

                else if (line.startsWith("deadline")) {
                    t = new Deadline(line, counter+1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");

                }
                else if (line.startsWith("event")) {
                    t = new Event(line, counter+1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                }

        }

    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        inputChecker();
    }
}


