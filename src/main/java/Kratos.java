import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Kratos {
    public static final String LINE = "----------------------------------------------------------------";
    static ArrayList<Task> tasksList = new ArrayList<>();
    private static final String FILE_PATH = "./data/tasks.txt";

    // Method to save tasks to a file
    public static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Create directories if they don't exist
            }
            file.createNewFile(); // Create the file if it doesn't exist
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < tasksList.size(); i++) {
                    writer.write(tasksList.get(i).toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            KratosException.handleException(e, "Error saving tasks to file: " + e.getMessage());
        }
    }

    // Method to load tasks from a file
    // Method to load tasks from a file
    public static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasksList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
            KratosException.handleException(e, "File not found. Creating a new file...");
            File file = new File(FILE_PATH);
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs(); // Create directories if they don't exist
                }
                file.createNewFile();
            } catch (IOException ioException) {
                KratosException.handleException(ioException, "Error creating a new file: " + ioException.getMessage());
            }
        } catch (IOException e) {
            KratosException.handleException(e, "Error loading tasks from file: " + e.getMessage());
        }
    }



    //public static int count = 0;

    // Method to greet the user
    public static void greet() {
        String logo = "──────────────────────────────\n" +
                "───────────────────────▓▓▓▓───\n" +
                "──────────────────────▓▓▓▓▓▓──\n" +
                "─────────────────────▓▓▓▓▓▓▓▓─\n" +
                "────────────────────▓▓▓▓▓▓▓▓──\n" +
                "───────────────────▓▓▓▓▓▓▓▓───\n" +
                "──────────────────▓▓▓▓▓▓▓▓────\n" +
                "─────────────────▓▓▓▓▓▓▓▓─────\n" +
                "─────────█──────▓▓▓▓▓▓▓▓──────\n" +
                "────────██─────▓▓▓▓▓▓▓▓───────\n" +
                "───────██──────▓▓▓▓▓▓▓▓▓──────\n" +
                "─▀██▄─██───────▓▓▓▓▓▓▓▓██──▄█─\n" +
                "─█████████▄─▄───▓▄▓▄████████──\n" +
                "──█▀▀▀███████────███████▀▀▀██─\n" +
                "─███▄▄██▄███▀─────███▄██▄▄███─\n" +
                "─█─██████▀█────────▓████████▀─\n" +
                "────██▀───▀─█────█──▓▓▓▓▓▓▓▓──\n" +
                "────▄█▀──────█──█────▓▓▓▓▓▓▓▓─\n" +
                "───▀█────────█──█─────▓▓▓▓▓▓▓─\n" +
                "───▄█▀────▄▄█▀──▀█▄▄───▓▓▓▓▓▓─\n" +
                "───█▄────█──█────█──█──▓▓▓▓▓▓─\n" +
                "───█─────▀──────────▀──▓▓▓▓▓▓─\n" +
                "────▀─────▀█──────█▀────▓▓▓───\n" +
                "────────────▀▄▄▄▄▀──────▓▓────\n" +
                "────────────────────────▓─────\n" +
                "────────▄█████████████▄───────\n" +
                "───────██▀▀▀▀▀▀▀▀▀▀▀▀▀██──────\n" +
                "───────▀───────────────▀──────\n" +
                "───────────█████████──────────\n" +
                "────────────███████───────────\n" +
                "──────────────███─────────────\n" +
                "──────██▄█─▄─█████─▄─█▄██─────\n" +
                "───────█████████████████──────\n" +
                "────────███████████████───────\n" +
                "─────────█████████████────────\n" +
                "──────────███████████─────────\n" +
                "──────────▀─███████─▀─────────\n" +
                "────────────▀─███─▀───────────\n" +
                "───────────────▀──────────────\n";
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Kratos commends you for your presence. Prepare for battle.\n" +
                        "Enter your commands with purpose.");
        System.out.println(LINE);
    }

    // Method to say goodbye
    public static void end() {
        System.out.println(LINE);
        System.out.println("            Until the next battle, mortal.\n" +
                "May your tasks be conquered with the ferocity of a god.");
        System.out.println(LINE);
    }

    // Method to display marking of tasks
    public static void displayMarking(int taskNumber, String mark) {
        String displayString;
        if (mark.equals("mark")) {
            displayString = "Task vanquished. Another notch on the blade of progress.\n" +
                    "What next, mortal?";
            tasksList.get(taskNumber).markTask();
        } else {
            displayString = "Task restored from the depths of completion.\n" +
                    "A twist of fate, mortal. What now?\n" +
                    "Reclaim victory or face the abyss once more.";
            tasksList.get(taskNumber).unmarkTask();
        }
        
        System.out.println(LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
        System.out.println(LINE);
    }

    // Method to display tasks
    public static void displayTasks(int count) {
        System.out.println(LINE);
        System.out.println("    Your list of Tasks");
        for (int i = 0; i < count; i++) {
            System.out.printf("     %d. %s%n", i + 1, tasksList.get(i).toString());
        }
        System.out.println(LINE);
    }

    // Main method
    public static void main(String[] args) {
        greet();
        loadTasksFromFile(); // Load tasks from file when the program boots up
        String userInput;
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                userInput = in.nextLine().trim();
                switch (userInput) {
                case "bye":
                    end();
                    return;
                case "list":
                    displayTasks(tasksList.size());
                    break;
                default:
                    handleCommand(userInput);
                    break;
                }
            }
        } finally {
            in.close();
            saveTasksToFile();
        }
    }

    private static void handleCommand(String userInput) {
        try {
            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]); //Extract task number
                displayMarking(taskNumber - 1, userInput.split(" ")[0]);
            } else if (userInput.startsWith("deadline")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("todo")) {
                addTodo(userInput);
            } else if (userInput.startsWith("event")) {
                addEvent(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteEvent(userInput);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            KratosException.handleException(e, userInput);
        }
    }



    private static void deleteEvent(String userInput) {
        String[] deleteCommand = userInput.split(" ");
        int taskNumber = Integer.parseInt(deleteCommand[1].trim()) - 1;
        if (taskNumber < 0 || taskNumber >= tasksList.size()) {
            throw new IndexOutOfBoundsException();
        }
        String displayString = "Task erased. Its existence now a whisper in the winds of fate.\n" +
                "What's your next decree?";
        System.out.println(LINE);
        System.out.println(displayString);
        System.out.printf("         %s%n",  tasksList.get(taskNumber).toString());
        tasksList.remove(taskNumber);
        System.out.println(tasksList.size() + " tasks linger, shadows yet unvanquished. How will you face them?");
        System.out.println(LINE);
    }

    private static void addEvent(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] eventParts = userInput.split("/from");

        // Extract the deadline, action, and item
        String eventAndName = eventParts[0].trim();  // "event project meeting"
        String date = eventParts[1].trim();  // "Mon 2pm /to 4pm"

        // Split the deadline into action and item
        String[] event = eventAndName.split("\\s+", 2);
        String action = event[1]; // project Meeting

        String[] timeline = date.split("/to");

        tasksList.add(new Event(action, timeline[0].trim(), timeline[1].trim()));
        System.out.println(LINE);
        System.out.println("Event recorded. Destiny's hourglass turns.\n" +
                "What now? Seize control or be swept by its sands?");
        System.out.println(LINE);
    }

    private static void addTodo(String userInput) {
        // Split line into action and type
        String[] actionAndType = userInput.split("\\s+", 2);
        String action = actionAndType[1];
        tasksList.add(new Todo(action));
        System.out.println(LINE);
        System.out.println("Task noted. A duty without a deadline? Dangerous.\n" +
                "What now? Forge ahead or risk oblivion?");
        System.out.println(LINE);
    }

    private static void addDeadline(String userInput) {
        // Split the string by "/by" to separate the deadline and date
        String[] deadlineParts = userInput.split("/by");

        // Extract the deadline, action, and item
        String deadline = deadlineParts[0].trim();  // "deadline return book"
        String date = deadlineParts[1].trim();  // "Sunday"

        // Split the deadline into action and item
        String[] actionAndItem = deadline.split("\\s+", 2);
        String action = actionAndItem[0];
        String item = actionAndItem[1];
        tasksList.add(new Deadline(item, date));
        System.out.println(LINE);
        System.out.println("Deadline acknowledged. Time ticks away, mortal.\n" +
                "What next? Embrace purpose or succumb to chaos?" );
        System.out.println(LINE);
    }
}
