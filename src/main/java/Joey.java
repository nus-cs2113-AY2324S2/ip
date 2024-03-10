import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;

/**
 * The Joey Class is a task management application.
 * the following are the features of Joey
 * adding ToDos, Deadlines and Events
 * Marking Tasks as Done and Not Done
 * Deleting Tasks from a list
 * Saving tasks to file
 * Finding Tasks from a list
 *
 *
 */
public class Joey {
    private static final String DASHED_LINE = "____________________________________________________________";



    /**
     * Loads tasks from a file
     * if a file does not exist, it create a file in the project directory
     * @return An ArrayList of Task objects
     */
    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("./data/tasks.txt");

        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (JoeyException e) {
                    System.out.println("Error parsing task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Save tasks to a file in the defined file directory
     * @param tasks The ArrayList of tasks to be saved
     * @throws IOException if an error occurs while saving tasks
     */
    private static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        File file = new File("./data/tasks.txt");
        file.getParentFile().mkdirs();
        try(PrintWriter writer = new PrintWriter(file)){
            for(Task task : tasks){
                if (task != null) {
                    writer.println(task.toSaveFormat());
                }
            }
        }
    }

    /**
     * Parses a task from a string representation
     * @param line string representation of the task
     * @return the parsed task object
     * @throws JoeyException if an error occurs while parsing tasks
     */
    private static Task parseTask(String line) throws JoeyException {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (parts[0]) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                if (parts.length < 4) throw new JoeyException("Invalid deadline format.");
                String by = parts[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                if (parts.length < 5) throw new JoeyException("Invalid event format.");
                String from = parts[3].trim();
                String to = parts[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new JoeyException("Unknown task type: " + parts[0]);
        }
    }


    /**
     * The main method serves as the entrypoint to the Joey application
     * initialises a task list, UI, and handles user commands.
     * @param args Command line arguements (not used in this application)
     */
    public static void main(String[] args) {
        //Used Java Collection class.ArrayList<Task>
        ArrayList<Task> tasks = loadTasksFromFile();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(tasks);

        //followed the Coding Standard to simplify the main class
        ui.showWelcomeMessage();
        ui.showTaskList(taskList.getTasks());

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String userCommand = scanner.nextLine();
                System.out.println(DASHED_LINE);

                if (userCommand.equalsIgnoreCase("bye")) {
                    saveTasksToFile(taskList.getTasks());
                    ui.showByeMessage();
                    break;
                } else {
                    Parser.parseUserCommand(userCommand, taskList, ui);
                }
            }
        } catch (Exception e) {
            ui.showError("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
