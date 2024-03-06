package bobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private Ui ui = new Ui();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFile() {
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (IOException e) {
            ui.showTextFileError();
        }

    }

    private static void readFile(String line, ArrayList<Task> tasks) {
        String[] parts = line.split("\\|");
        String label = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        switch (label) {
        case "T":
            tasks.add(new Todo(description,isDone));
            break;
        case "D":
            String by = parts[3].trim();
            tasks.add(new Deadline(description, isDone, by));
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            tasks.add(new Event(description, isDone, from, to));
            break;
        default:
        }
    }

    public void loadFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            readFile(line, tasks);
        }
    }

    public static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + "\n";
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getBy() + "\n";
        } else if (task instanceof Event) {
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + task.getFrom() + " | "
                    + task.getBy() + "\n";
        }
        return "";
    }

    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(taskToFileFormat(task));
        }
        fw.close();
    }
}


