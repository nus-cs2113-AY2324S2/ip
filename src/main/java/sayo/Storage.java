package sayo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Handles storage operations for Sayo application tasks.
 * This includes loading from and saving tasks to a file.
 */
public class Storage {
    
    private String filePath;
    
    /**
     * Constructs a new Storage object to handle file operations.
     * 
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file into a list of strings.
     * 
     * @return A list of strings representing tasks.
     * @throws SayoException If an I/O error occurs reading from the file.
     */
    public List<String> load() throws SayoException {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new SayoException("An error occurred while loading tasks from file.");
        }
    }
    
    /**
     * Saves the current list of tasks to the file.
     * 
     * @param items The list of tasks to be saved.
     */
    public void save(ArrayList<Task> items){
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : items) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving tasks to file.");
            e.printStackTrace();
        }
    }


}
