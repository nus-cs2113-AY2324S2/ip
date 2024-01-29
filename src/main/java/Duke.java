import java.util.Scanner;

public class Duke {

    public static void inputChecker() {
        Scanner in = new Scanner(System.in); // declared a scanner object
        String line; // declared a string object to take in user input

        Task[] list; // declare a list with string data type
        list = new Task[100]; // create a list of 100 elements
        int counter = 0;
        int index;
        Task t;

        // start of user input
        while (true) {
            line = in.nextLine(); //takes in user input

            boolean mark = line.trim().toLowerCase().startsWith("mark");
            boolean unmark = line.trim().toLowerCase().startsWith("unmark");

            // when user exits
            if (line.equals("bye")) {
                System.out.println("Bye human. Come back soon !");
                break;
            }
            // if user wants to display a list
            else if (line.equalsIgnoreCase("list")) {
                for (Task task : list) {
                    if (task == null){
                        break;
                    }
                    System.out.println(task.index + ".[" + task.getStatusIcon() + "] " + task.description);

                }
                System.out.println("____________________________________________________________");
                continue;
            }

            else if (mark || unmark) {
                // to mark
                if (mark) {
                     index = Integer.parseInt(line.substring(5));
                     t = list[index - 1];
                     t.isDone =  true;

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
                    System.out.println("____________________________________________________________");
                }

                // to unmark
                else {
                    index = Integer.parseInt(line.substring(7));
                    t = list[index - 1];
                    t.isDone =  false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + t.getStatusIcon() + "] " + t.description);
                    System.out.println("____________________________________________________________");
                }
                continue;
            }

            // user adds in Task elements
            System.out.println("added: " + line);
            System.out.println("____________________________________________________________");
            t = new Task(line, counter+1); //put task in
            list[counter] = t;
            counter += 1;
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


