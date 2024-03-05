import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : TaskList.getList()) {
                fw.write(taskToLine(task) + "\n");
            }
            fw.close();
            System.out.println("Saved to file");
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasks() throws FileNotFoundException, InvalidInputException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Parser.parseFileLine(line);
        }
        scanner.close();
    }
    private static String taskToLine(Task task) {
        if (task instanceof ToDo) {
            ToDo todo = (ToDo) task;
            return "T | " + (todo.isDone() ? "1" : "0") + " | " + todo.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (deadline.isDone() ? "1" : "0") + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Events) {
            Events event = (Events) task;
            return "E | " + (event.isDone() ? "1" : "0") + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }

}
