import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public void loadTasksFromFile(TaskList tasks) {
        try {
            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                // Handle the case where the file doesn't exist yet
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println("File created: " + Sunny.FILE_PATH);
            } else {
                File savedFile = new File(filePath);
                Scanner scanner = new Scanner(savedFile);

                System.out.println("Tasks loaded from file:");
                while (scanner.hasNext()) {
                    String fileLine = scanner.nextLine();
                    System.out.println("Read from file: " + fileLine);

                    char taskType = fileLine.charAt(1);
                    tasks.addTaskFromSaved(fileLine, taskType);
                }
                System.out.println("Tasks loaded successfully!" + System.lineSeparator());


                scanner.close();
            }
        } catch (IOException e) {
            System.out.printf("OH NO! No file found :/", e.getMessage());
        }
    }

    public void saveTasksToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            System.out.println("Saving tasks to file:");
            for (int i = 0; i < tasks.size(); i++){
                String taskString = tasks.get(i).toString();
                writer.write(taskString + System.lineSeparator());
                System.out.println("Saved to file: " + taskString);
            }
            writer.close();
            System.out.println("Tasks saved successfully!" + System.lineSeparator());
        } catch (IOException e) {
            System.out.printf("ERROR! Something went wrong...: %1$s", e.getMessage());
        }
    }
 }
