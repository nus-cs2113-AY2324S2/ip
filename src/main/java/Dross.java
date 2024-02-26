import drosstasks.List;
import drosstasks.Task;
import myexceptions.InvalidTodoException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Scanner;
import java.io.File;

public class Dross {
    private static List drossTaskList = new List();
    private static Scanner in = new Scanner(System.in);

    public static final String TASKS_FILE_PATH = constructTasksFilePath();

    //Method to print a line of _ characters
    public static void printLine() {
        System.out.println("_".repeat(55));
    }

    //Method to read in a line of text from standard input
    public static String readLine() {
        return in.nextLine().trim();
    }

    //Method to exit Dross bot
    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    //Method to construct filepath
    private static String constructTasksFilePath() {
        String userHome = System.getProperty("user.home");
        String separator = FileSystems.getDefault().getSeparator();
        // Construct the directory path separately
        String directoryPath = userHome + separator + "Desktop" + separator + "dross";
        File drossDirectory = new File(directoryPath);

        // Ensure the directory exists
        if (!drossDirectory.exists()) {
            boolean wasSuccessful = drossDirectory.mkdirs(); // Create the directory if it doesn't exist
            if (wasSuccessful) {
                System.out.println("The 'dross' directory was successfully created on the Desktop.");
            } else {
                System.out.println("Failed to create the 'dross' directory on the Desktop.");
            }
        }

        String filePath = directoryPath + separator + "tasks.txt";
        return filePath;
    }


    //Method to list all tasks
    public static void listAllTasks() {
        printLine();
        drossTaskList.printAllTasks();
        printLine();
    }

    //Method to toggle tasks as marked or unmarked
    public static void toggleMark(String instruction) {
        String[] tokens = instruction.split(" ");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (tokens[0].equals("mark")) {
                drossTaskList.markDoneByIndex(index);
                saveTasksToFile();
            } else {
                drossTaskList.markUndoneByIndex(index);
                saveTasksToFile();
            }
            listAllTasks();
        } catch (ArrayIndexOutOfBoundsException e){
            printLine();
            System.out.println("Yeah sure go ahead and mark that invisible task sir!");
            printLine();
        }
    }


    // Method to save tasks to file
    public static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FILE_PATH))) {
            for (int i = 0; i < drossTaskList.getSize(); i++) {
                writer.write(drossTaskList.getTask(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    //Method to handle task creation and parse input to appropriately construct the correct object
    public static void handleTaskCreation(String line) {
        if (line.startsWith("todo")) {
            try {
                drossTaskList.addTask(line.substring("todo".length()).trim());
                saveTasksToFile();
            } catch (InvalidTodoException e){
                System.out.println("You want to do nothing? Be my guest... Type it this way todo [task] if you are kind enough to stop wasting my time");
            }
        } else if (line.startsWith("deadline")) {
            String[] parts = line.substring("deadline".length()).trim().split(" /by ", 2);
            try {
                drossTaskList.addTask(parts[0], parts[1]);
                saveTasksToFile();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Go ahead, live life with no deadlines. Procrastinate forever. deadline /by [time] is what you need to type, genius.");
            }
        } else if (line.startsWith("event")) {
            String[] parts = line.substring("event".length()).trim().split(" /from ", 2);
            try {
                String[] timeParts = parts[1].split(" /to ", 2);
                drossTaskList.addTask(parts[0], timeParts[0], timeParts[1]);
                saveTasksToFile();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("An event without a name, a start or end... What do you think you are, a philosopher?? event [name] /from [time1] /to [time2] is the way for goodness sakes...");
            }
        }
        drossTaskList.printLastTask();
        printLine();
    }

    //Method to determine what to do based on the input entered
    public static void receiveUserInput() {
        String line = readLine();
        while (!line.equals("bye")) {
            if (line.startsWith("list")) {
                listAllTasks();
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                toggleMark(line);
            } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
                handleTaskCreation(line);
            }
            else if (line.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    drossTaskList.deleteTask(index);
                    System.out.println("Task " + index + " successfully deleted boss");
                    printLine();
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Delete what lah");
                    printLine();
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Delete simi delete");
                    printLine();
                }
            }
            else {
                printLine();
                System.out.println("Please enter a valid command");
                printLine();
            }
            line = readLine();
        }
        goodbye();
    }

    // Method to load tasks from file
    public static void loadTasksFromFile() {
        File file = new File(TASKS_FILE_PATH);
        // Check if the file exists
        if (!file.exists()) {
            System.out.println("The file does not exist.");
            return; // Exit the method if the file doesn't exist
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(TASKS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Determine the task type and completion status
                String taskType = line.substring(1, 2); // Extracts "T", "D", or "E"
                boolean isDone = line.substring(5, 6).equals("X"); // Checks if the task is completed

                // Extract the task details based on type
                String taskDetails = line.substring(7).trim(); // Removes the prefix to get the details
                String description, by = null, start = null, end = null;

                switch (taskType) {
                    case "T": // For ToDo tasks
                        description = taskDetails;
                        drossTaskList.addTask(description); // Assuming a method to add ToDo
                        break;
                    case "D": // For Deadline tasks
                        description = taskDetails.split(" \\(by: ")[0];
                        by = taskDetails.substring(taskDetails.indexOf("by: ") + 4, taskDetails.length() - 1);
                        drossTaskList.addTask(description, by); // Assuming a method to add Deadline
                        break;
                    case "E": // For Event tasks
                        description = taskDetails.split(" \\(from: ")[0];
                        String times = taskDetails.substring(taskDetails.indexOf("from: ") + 6);
                        start = times.split(" to: ")[0];
                        end = times.split(" to: ")[1].replaceAll("\\)", "");
                        drossTaskList.addTask(description, start, end); // Assuming a method to add Event
                        break;
                }

                // Mark the task as done if indicated
                if (isDone) {
                    drossTaskList.markDoneByIndex(drossTaskList.getSize());
                }
            }
        } catch (IOException | InvalidTodoException e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Dross");
        System.out.println("What can I do for you?");
        printLine();
        loadTasksFromFile();
        receiveUserInput();
    }
}
