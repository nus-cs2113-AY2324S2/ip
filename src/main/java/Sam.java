import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Sam {
    private static final String FILE_PATH = "data/sam.txt";

    public static void main(String[] args) {
        // Load tasks from file when the program starts up
        Task[] records = loadTasksFromFile();
        int numItems = countTasks(records);

        // Printing the logo and greeting message
        String logo = "  _________   _____      _____   \n" +
                " /   _____/  /  _  \\    /     \\  \n" +
                " \\_____  \\  /  /_\\  \\  /  \\ /  \\ \n" +
                " /        \\/    |    \\/    Y    \\\n" +
                "/_______  /\\____|__  /\\____|__  /\n" +
                "        \\/         \\/         \\/ ";
        System.out.println("Hello! I'm SAM\n" + logo + "\n" + "What can I do for you?\n");

        System.out.println("Here's what you got saved:");
        for (int j = 0; j < numItems; j++) {
            System.out.println((j + 1) + "." + records[j]);
        }
        printLine();

        // Initializing scanner to read user input
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        // Array to store tasks
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
                    records[listIndex].setStatus(true);
                    System.out.println("Nice! I've marked this task as done:\n" + records[listIndex]);
                    saveTasksToFile(records, numItems);
                    break;
                case "unmark":
                    // Marking a task as not done
                    listIndex = Integer.parseInt(words[1]) - 1;
                    markCheck(listIndex, numItems);
                    records[listIndex].setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:\n" + records[listIndex]);
                    saveTasksToFile(records, numItems);
                    break;
                case "todo":
                    // Adding a new todo task
                    String todoDescription = line.substring(5); // Extracting description
                    records[numItems] = new Todo(todoDescription);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    saveTasksToFile(records, numItems);
                    break;
                case "deadline":
                    // Adding a new deadline task
                    String deadlineDescription = line.substring(9); // Extracting description
                    String[] deadlineParts = deadlineDescription.split(" /by ");
                    records[numItems] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    saveTasksToFile(records, numItems);
                    break;

                case "event":
                    // Adding a new event task
                    String eventDescription = line.substring(6); // Extracting description
                    String[] eventParts = eventDescription.split(" /from | /to ");
                    records[numItems] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    System.out.println("Got it. I've added this task:\n" + records[numItems]);
                    numItems++;
                    saveTasksToFile(records, numItems);
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

    private static void saveTasksToFile(Task[] records, int numItems) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < numItems; i++) {
                bw.write(records[i].saveTask());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Task[] loadTasksFromFile() {
        Task[] records = new Task[100];
        File file = new File(FILE_PATH);

        // Check if the directory and file exist, create them if they don't
        if (!file.exists()) {
            try {
                File directory = new File("data");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the file: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" \\| ");
                if (words[0].equals("E")) {
                    records[i] = new Event(words[2], words[3], words[4]);
                } else if (words[0].equals("D")) {
                    records[i] = new Deadline(words[2], words[3]);
                } else if (words[0].equals("T")) {
                    records[i] = new Todo(words[2]);
                }
                records[i].setStatus(words[1].equals("1"));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }


    static int countTasks(Task[] records) {
        int numItems = 0;
        for (Task record : records) {
            if (record != null) {
                numItems++;
            }
        }
        return numItems;
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
