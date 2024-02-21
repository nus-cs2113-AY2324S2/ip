import tasks.Task;
import tasks.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Save {

    protected static final Path FILE_PATH = Path.of("./data/duck.txt");

    /**
     * Checks if directory and file exists, creates new one if does not exist
     *
     * @throws IOException
     */
    public static void createNewFile() throws IOException {

        if (!Files.isDirectory(FILE_PATH.getParent())) {
            Files.createDirectories(FILE_PATH.getParent());
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
            System.out.println("File name: " + FILE_PATH.getFileName());
            System.out.println("Parent directory: " + FILE_PATH.getParent());
            System.out.println("Absolute path: " + FILE_PATH.toAbsolutePath());
        }
    }

    public static void writeFile(ArrayList<Task> tasks) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>();
        for (Task task: tasks) {
            fileContent.add(task.toString());
        }

        Files.write(FILE_PATH, fileContent);
    }

//    public static void readFile(ArrayList<Task> tasks) throws IOException {
//        List<String> fileContent = Files.readAllLines(FILE_PATH);
//        for (String line: fileContent) {
//            switch(line.trim()) {
//            case "[X":
//                ToDo.addToDo(tasks,);
//            }
//        }
//    }
}
