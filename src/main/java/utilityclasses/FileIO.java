package utilityclasses;
import drosstasks.DrossList;
import myexceptions.InvalidTodoException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.io.File;

public class FileIO {
    public static final String TASKS_FILE_PATH = constructTasksFilePath();

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

    // Method to save tasks to file
    public static void saveTasksToFile(DrossList list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_FILE_PATH))) {
            for (int i = 0; i < list.getSize(); i++) {
                writer.write(list.getTask(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    // Method to load tasks from file
    public static void loadTasksFromFile(DrossList list) {
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
                boolean isDone = line.substring(4,5).equals("x"); // Checks if the task is completed

                // Extract the task details based on type
                String taskDetails = line.substring(7).trim(); // Removes the prefix to get the details
                String description, by = null, start = null, end = null;

                switch (taskType) {
                    case "T": // For ToDo tasks
                        description = taskDetails;
                        list.addTask(description); // Assuming a method to add ToDo
                        break;
                    case "D": // For Deadline tasks
                        description = taskDetails.split(" \\(by: ")[0];
                        by = taskDetails.substring(taskDetails.indexOf("by: ") + 4, taskDetails.length() - 1);
                        list.addTask(description, by); // Assuming a method to add Deadline
                        break;
                    case "E": // For Event tasks
                        description = taskDetails.split(" \\(from: ")[0];
                        String times = taskDetails.substring(taskDetails.indexOf("from: ") + 6);
                        start = times.split(" to: ")[0];
                        end = times.split(" to: ")[1].replaceAll("\\)", "");
                        list.addTask(description, start, end); // Assuming a method to add Event
                        break;
                }

                // Mark the task as done if indicated
                if (isDone) {
                    list.markDoneByIndex(list.getSize());
                }
            }
        } catch (IOException | InvalidTodoException e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }
}
