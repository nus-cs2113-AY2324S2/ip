package sayo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
    
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
