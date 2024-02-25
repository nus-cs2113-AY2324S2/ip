import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DataManager {

    private static void writeToFile(String filePath, String textToAppend, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAppend);
        fw.close();
    }

    protected static void saveTasksToFile(String filepath, ArrayList<Task> taskList) {
        try {
            writeToFile(filepath, "", false);           // Clear Old content
        } catch (IOException e) {
            System.out.println("IOException occured: " + e.getMessage());
        }

        for (Task task : taskList) {
            char taskType = task.toString().charAt(1);
            String taskToAdd = taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.description;
            if (taskType == 'T') {
              taskToAdd += "\n";
            } else if (taskType == 'D') {
                String by = task.toString().split("\\(by: ")[1].replace(')', '\n');
                taskToAdd += " | " + by;
            } else if (taskType == 'E') {
                String[] duration = task.toString().split("\\(from: ")[1].split(" to: ");
                String from = duration[0];
                String to = duration[1].replace(')', '\n');
                taskToAdd += " | " + from + "-" + to;
            } else {
                taskToAdd = "\n";
            }

            try {
                writeToFile(filepath, taskToAdd, true);
            } catch (IOException e) {
                System.out.println("IOException occured: " + e.getMessage());
            }
        }
    }

    protected static void loadTasksFromFile(String filePath, ArrayList<Task> taskList) {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (s != null) {
            while (s.hasNext()) {
                taskList.add(interpretTask(s.nextLine()));
            }
        }
    }

    private static Task interpretTask(String task) {
        String[] taskInfo = task.split(" \\| ");
        String taskType = taskInfo[0];
        String taskStatus = taskInfo[1];
        String taskDescription = taskInfo[2].trim();

        if (taskType.equals("T")) {
            Todo todo = new Todo(taskDescription);
            if (taskStatus.equals("1")) {
                todo.markAsDone();
            }
            return todo;
        }
        if (taskType.equals("D")) {
            String by = taskInfo[3].trim();
            Deadline deadline = new Deadline(taskDescription, by);
            if (taskStatus.equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        }
        if (taskType.equals("E")) {
            String[] duration = taskInfo[3].split("-");
            String from = duration[0].trim();
            String to = duration[1].trim();
            Event event = new Event(taskDescription, from, to);
            if (taskStatus.equals("1")) {
                event.markAsDone();
            }
            return event;
        }

        return null;
    }
}
