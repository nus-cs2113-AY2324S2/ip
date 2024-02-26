package brad.tasks;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import brad.exceptions.dataCorruptedException;

public class TaskList {
    private ArrayList<Tasks> taskList = new ArrayList<>();
    private final String FILE_PATH = "data/data.md";
    private final String FILE_HEADER = "|Task Type | Done | Description | Time |\n"
            + "|----------|------|-------------|------|\n";

    public void initializeFile()
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
                    addToList(description + " /from " + amendedTime, TaskType.EVENT, isCompleted, false);
                    break;
                case "Deadline":
                    addToList(description + " /by " + time, TaskType.DEADLINE, isCompleted, false);
                    break;
                case "Todo":
                    addToList(description, TaskType.TODO, isCompleted, false);
                    break;
                default:
                    throw new dataCorruptedException();
            }
        }
    }
    public void addToList(String input, TaskType type, boolean isDone, boolean canSave) {
        Tasks newTask;
        if (type == TaskType.EVENT) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String description = input.substring(0, fromIndex).strip();
            String startTime = input.substring(fromIndex + "/from ".length(), toIndex).trim();
            String endTime = input.substring(toIndex + "/to ".length()).trim();
            newTask = new Event(description, startTime, endTime, isDone);
        } else if (type == TaskType.DEADLINE) {
            int timeIndex = input.indexOf("/by");
            String description = input.substring(0, timeIndex).strip();
            String time = input.substring(timeIndex + "/by ".length()).strip();
            newTask = new Deadline(description, time, isDone);
        } else if (type == TaskType.TODO) {
            newTask = new Todo(input, isDone);
        } else {
            newTask = new Tasks(input, isDone);
        }
        taskList.add(newTask);

    }

    public String getList() {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output +=  i  + ". " + getTask(i) + "\n";
        }
        return output;
    }

    public String getTask(int n) {
        String output = taskList.get(n - 1).getFullDescription();
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        taskList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return taskList.size();
    }

    public void deleteTask(int n) {
        taskList.remove(n - 1);
    }

    public void addHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(FILE_HEADER);
        fw.close();
    }

    public void updateFile() throws IOException {
        addHeader();
        for (Tasks task : taskList) {
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
