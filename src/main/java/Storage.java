import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;

/* public class Storage {
    public static final String FILE_PATH = "data/wongster.txt";

    public static void createFile() throws IOException {
        File folder = new File("data");
        folder.mkdir();
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    public static void loadTasks(ArrayList<Task> userList) throws IOException {
            // Attempt to read data from the file
            Scanner s = new Scanner(FILE_PATH);
            // Process each line of the file and add tasks to the list
            while (s.hasNextLine()) {
                String line  = s.nextLine();
                    String by = text.substring(text.indexOf("by:") + 3, text.length() - 1).trim();
                    String description = text.substring(0, text.indexOf("(by:")).trim();

                    Deadline newTask = new Deadline(description, by);
                    newTask.isDone = isDone;
                    userList.add(newTask);
                } else if (task.startsWith("[E]")) {
                    String to = text.substring(text.indexOf("to:") + 3, text.length() - 1).trim();
                    String from = text.substring(text.indexOf("from:") + 5, text.indexOf("to:")).trim();
                    String description = text.substring(0, text.indexOf("(from:")).trim();

                    Event newTask = new Event(description, from, to);
                    newTask.isDone = isDone;
                    userList.add(newTask);
                }

            }
            s.close();

    }

    public static void saveTasks(ArrayList<Task> userList) throws IOException {

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : userList) {
                // Construct the string representation of the task manually
                String taskString = "";  // Initialize an empty string
                // Use instanceof checks and specific logic for each Task type
                if (task instanceof ToDo) {
                    ToDo todo = (ToDo) task;  // Cast to specific Task type
                    taskString = "[T]" + (todo.isDone ? "[X]" : "[ ]") + todo.description;
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    taskString = "[D]" + (deadline.isDone ? "[X]" : "[ ]") + deadline.description + " (by: " + deadline.by + ")";
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    taskString = "[E]" + (event.isDone ? "[X]" : "[ ]") + event.description + " (from: " + event.from + ", to: " + event.to + ")";
                }
                // Add a newline character
                taskString += System.lineSeparator();
                // Write the task string to the file
                fileWriter.write(taskString);
            }
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }
    }



} */
public class Storage {
    public static final String FILE_PATH = "data/wongster.txt";

    public static void loadTasks(TaskList userList) throws IOException {
        File dataFolder = new File("data");
        File file = new File(FILE_PATH);

        // Check if "data" folder exists, create it if not
        if (!dataFolder.exists()) {
            System.out.println("Welcome new user!");
            dataFolder.mkdir();
        }

        // Check if the file exists and attempt to read tasks
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String taskLine = scanner.nextLine();
                    if(taskLine.startsWith("[T]")) {
                        String description = taskLine.substring(7).trim();
                        Task task = new ToDo(description);
                        userList.addTask(task);
                    } else if (taskLine.startsWith("[D]")) {
                        String[] parts = taskLine.substring(7).split("\\(by:");
                        String description = parts[0].trim();
                        String by = parts[1].trim().substring(0, parts[1].length() - 2);
                        Task task = new Deadline(description,by);
                        userList.addTask(task);
                    } else if (taskLine.startsWith("[E]")) {
                        String[] parts = taskLine.substring(7).split("\\(from:");
                        String description = parts[0].trim();
                        String[] remainingParts = parts[1].split("to:");
                        String from = remainingParts[0].trim();
                        String to = remainingParts[1].trim().substring(0, remainingParts[1].length() - 2);
                        Task task = new Event(description,from,to);
                        userList.addTask(task);
                    }
                }
            } catch (FileNotFoundException e) {
                // Shouldn't happen as we checked file existence before
                System.err.println("Unexpected error: File not found even after existence check.");
            }
        }
    }

    public static void saveTasks(TaskList userList) throws IOException {

        // Use FileWriter with append=true for appending mode
        FileWriter fileWriter = new FileWriter(FILE_PATH);

        for (int i = 0; i < userList.size(); i++) {
            fileWriter.write(userList.getTask(i).toFileString() + System.lineSeparator());
        }

        fileWriter.close();
    }

}
