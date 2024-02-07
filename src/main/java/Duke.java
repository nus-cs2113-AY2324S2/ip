import java.util.Scanner;

public class Duke {

    public static void userMarkOrUnmark(String command, String line, Task[] list, int index, Task t) {
        // To mark
        if (command.equals("mark")) {
            try {
                index = Integer.parseInt(line.substring(5));
            } catch (NumberFormatException e) {
                System.out.println("Task number is not a valid number");
                return;
            }
            t = list[index - 1];
            t.isDone =  true;
            System.out.println("Nice! I've marked this task as done:");
        }

        // To unmark
        else {
            try {
                index = Integer.parseInt(line.substring(7));
            } catch (NumberFormatException e) {
                System.out.println("Task number is not a valid number");
                return;
            }
            index = Integer.parseInt(line.substring(7));
            t = list[index - 1];
            t.isDone =  false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(t);
        System.out.println("____________________________________________________________");
    }

    public static void userList(String line, Task[] list) {
            for (Task task : list) {
                if (task == null){
                    break;
                }
                System.out.println(task.index + ". " + task);
            }
            System.out.println("____________________________________________________________");
    }
    public static void userBye() {
        System.out.println("Bye human. Come back soon !");
    }

    //
    public static void userInput() {
        Scanner in = new Scanner(System.in); // Declared a scanner object
        String line; // Declared a string object to take in user input
        Task[] list; // Declared a list with string data type
        list = new Task[100]; // created a list of 100 elements
        int counter = 0;
        int index = 0;
        Task t = list[0];

        // Start of user input

        while (true) {
            line = in.nextLine().toLowerCase(); // Takes in user input
            String[] splitLine = line.split("\\s+"); // split if there is 1 or more whitespace
            String command = splitLine[0];


            // User wants to exit
            switch (command) {
                case "bye":
                    userBye();
                    break;

                // User wants to display the list of tasks
                case "list":
                    userList(line, list);
                    continue;

                    // User wants to mark or unmark tasks
                case "mark":
                case "unmark":
                    userMarkOrUnmark(splitLine[0], line, list, index, t);
                    continue;

                // Checks which type of task the user wants to do
                case "todo":
                    t = new Todo(line, counter + 1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                case "deadline":
                    t = new Deadline(line, counter + 1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                case "event":
                    t = new Event(line, counter + 1);
                    list[counter] = t;
                    counter += 1;
                    System.out.println("Now you have " + counter + " tasks in the list.");
                    continue;

                default:
                    System.out.println("No suitable command found. Please try again!");
                    continue;
            }
            return;
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        userInput();
    }
}