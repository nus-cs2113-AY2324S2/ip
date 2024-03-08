import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading from and saving tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/Loopy.txt"; // File path
/**
 * Loads tasks from the file.
 *      * @return TaskList loaded from the file. If file does not exist, returns an empty TaskList.
 */
 public static TaskList loadFile(){
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new TodoTask(parts[2]));
                        break;
                    case "D":
                        tasks.add(new DeadlineTask(parts[2], parts[3]));
                        break;
                    case "E":
                        tasks.add(new EventTask(parts[2], parts[3], parts[4]));
                        break;
                }
                if (parts[1].equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found! Starting a new list\n  ❀•°❀°•❀ ");
        }
        TaskList taskList = new TaskList(tasks);
        taskList.displayTaskList();
        return taskList;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveFile(ArrayList<Task> tasks ) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // Make the directory if it doesn't exist
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }
}
