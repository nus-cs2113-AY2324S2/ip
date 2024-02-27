package brad.storage;

import brad.exceptions.dataCorruptedException;
import brad.tasks.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import brad.tasks.Tasks;
import brad.tasks.TaskList;

public class Storage {
    private final String FILE_PATH = "data/data.md";
    private final String FILE_HEADER = "|Task Type | Done | Description | Time |\n"
            + "|----------|------|-------------|------|\n";

    public void initializeFile(TaskList tasklist)
            throws FileNotFoundException, dataCorruptedException {
        File file = new File(FILE_PATH);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
                System.out.println("file exists?: " + file.exists());
                System.out.println("is Directory?: " + file.isDirectory());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] input = s.nextLine().split("\\|");
            if (input[1].strip().equals("Task Type") || input[1].strip().equals("----------")) {
                continue;
            }

            String type = input[1].strip();
            boolean isCompleted = Boolean.parseBoolean(input[2].strip());
            String description = input[3].strip();
            String time = input[4].strip();
            switch (type) {
                case "Event":
                    int toIndex = time.indexOf("to");
                    String beforeTo = time.substring(0, toIndex);
                    String afterTo = time.substring(toIndex);
                    String amendedTime = beforeTo + '/' + afterTo;
                    //System.out.println(description + " /from " + amendedTime);
                    tasklist.addToList(description + " /from " + amendedTime, TaskType.EVENT, isCompleted);
                    break;
                case "Deadline":
                    tasklist.addToList(description + " /by " + time, TaskType.DEADLINE, isCompleted);
                    break;
                case "Todo":
                    tasklist.addToList(description, TaskType.TODO, isCompleted);
                    break;
                default:
                    throw new dataCorruptedException();
            }
        }
    }

    public void addHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(FILE_HEADER);
        fw.close();
    }

    public void updateFile(TaskList tasklist) throws IOException {
        addHeader();
        for (Tasks task : tasklist.getTaskList()) {
            appendTaskToFile(task);
        }
    }

    public void appendTaskToFile(Tasks task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    private String toMarkdown(Tasks task) {
        String type = task.getClass().getSimpleName();
        boolean taskIsDone = task.getIsDone();
        String description = task.getTaskDescription();
        String time = "";
        if (!type.equals("Todo")) {
            time = task.getTime();
        }
        return "| " + type + " | " + taskIsDone + " | " + description + " | " + time + " |\n";
    }
}
