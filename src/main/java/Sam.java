import java.util.Scanner;

public class Sam {
    public static void main(String[] args) {
        // Printing the logo and greeting message
        String logo = "  _________   _____      _____   \n" +
                " /   _____/  /  _  \\    /     \\  \n" +
                " \\_____  \\  /  /_\\  \\  /  \\ /  \\ \n" +
                " /        \\/    |    \\/    Y    \\\n" +
                "/_______  /\\____|__  /\\____|__  /\n" +
                "        \\/         \\/         \\/ ";
        System.out.println("Hello! I'm SAM\n" + logo + "\n" + "What can I do for you?\n");

        // Initializing scanner to read user input
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        // Array to store tasks
        Task[] records = new Task[100];
        int numItems = 0; // Number of tasks
        int listIndex; // Index for tasks

        // Main loop
        while (!line.equals("bye")) {
            String[] words = line.split(" ");
            printLine();
            try {
                // Checking user input
                switch (words[0]) {
                case "list":
                    // Listing tasks
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < numItems; j++) {
                        System.out.println((j + 1) + "." + records[j]);
                    }
                    System.out.println("Now you have " + numItems + (numItems == 1 ? " task " : " tasks ") + "in the list.");
                    break;
                case "mark":
                    // Marking a task as done
                    listIndex = Integer.parseInt(words[1]) - 1;
                    markCheck(listIndex, numItems);
                    records[listIndex].isDone = true;
                    System.out.println("Nice! I've marked this task as done:\n" + records[listIndex]);
                    break;
                case "unmark":
                    // Marking a task as not done
                    listIndex = Integer.parseInt(words[1]) - 1;
                    markCheck(listIndex, numItems);
                    records[listIndex].isDone = false;
                    System.out.println("OK, I've marked this task as not done yet:\n" + records[listIndex]);
                    throw new InvalidInputException();
                    //break;
                case "todo":
                    // Adding a new todo task
                    String todoDescription = line.substring(5); // Extracting description
                    records[numItems] = new Todo(todoDescription);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    break;
                case "deadline":
                    // Adding a new deadline task
                    String deadlineDescription = line.substring(9); // Extracting description
                    String[] deadlineParts = deadlineDescription.split(" /by ");
                    records[numItems] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    break;

                case "event":
                    // Adding a new event task
                    String eventDescription = line.substring(6); // Extracting description
                    String[] eventParts = eventDescription.split(" /from | /to ");
                    records[numItems] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    break;
                    case "delete":
                        // Deleting a task
                        listIndex = Integer.parseInt(words[1]) - 1;
                        if (listIndex < 0 || listIndex >= numItems) {
                            System.out.println("That was outta range. Use list to see the current tasks.");
                        } else {
                            System.out.println("Noted. I've removed this task:\n" + records[listIndex]);
                            // Shift remaining tasks to fill the gap
                            for (int i = listIndex; i < numItems - 1; i++) {
                                records[i] = records[i + 1];
                            }
                            numItems--;
                            System.out.println("Now you have " + numItems + (numItems == 1 ? " task " : " tasks ") + "in the list.");
                        }
                        break;
                default:
                    // Invalid command
                    System.out.println("No valid command detected, please try again.");
                }
                printLine();
                line = in.nextLine();
            } catch (InvalidInputException e) {
                System.out.println("Try again!");
                printLine();
                line = in.nextLine(); // Prompt for new input
            } catch (NumberFormatException e) {
                System.out.println("That's not a number!");
                printLine();
                line = in.nextLine(); // Prompt for new input
            } catch (IndexOutOfBoundsException e) {
                switch (words[0]) {
                case "todo":
                    System.out.println("I am expecting: todo <description>");
                    break;
                case "deadline":
                    System.out.println("I am expecting: deadline <description> /by <date>");
                    break;
                case "event":
                    System.out.println("I am expecting: event <description> /from <date> /to <date>");
                    break;
                default:
                    break;
                }
                System.out.println("Try again!");
                printLine();
                line = in.nextLine();
            }
        }
        // Exiting message
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void markCheck(int listIndex, int numItems) throws InvalidInputException {
        if (listIndex < 0 || listIndex >= numItems) {
            if (listIndex < 0) {
                System.out.println("No can do, the list starts at 1.");
            } else {
                System.out.println("Hey lil bro, you have only " + numItems + " things in the list.");
            }
            throw new InvalidInputException();
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
