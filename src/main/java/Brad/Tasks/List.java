package Brad.Tasks;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Brad.Exceptions.dataCorruptedException;

public class List {
    private ArrayList<Task> inputList = new ArrayList<Task>();
    private final String FILE_PATH = "data/data.md";

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
                    addToList(description + "/from " + time, TaskType.EVENT, isCompleted, false);
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
        Task newTask;
        if (type == TaskType.EVENT) {
            int timeIndex = input.indexOf("/from");
            String description = input.substring(0, timeIndex).strip();
            String time = input.substring(timeIndex + "/from ".length()).strip();
            time = time.replace("/", "");
            newTask = new Event(description, time, isDone);
        } else if (type == TaskType.DEADLINE) {
            int timeIndex = input.indexOf("/by");
            String description = input.substring(0, timeIndex).strip();
            String time = input.substring(timeIndex + "/by ".length()).strip();
            newTask = new Deadline(description, time, isDone);
        } else if (type == TaskType.TODO) {
            newTask = new Todo(input, isDone);
        } else {
            newTask = new Task(input, isDone);
        }
        inputList.add(newTask);

    }
    public String getList() {
        String output = "";
        for (int i = 1; i <= inputList.size(); i++) {
            output +=  i  + ". " + getTask(i) + "\n";
        }
        return output;
    }

    public String getTask(int n) {
        String output = inputList.get(n - 1).getFullDescription();
        return output;
    }

    public void markAsDone(int n, boolean isDone) {
        inputList.get(n - 1).setIsDone(isDone);
    }

    public int listSize() {
        return inputList.size();
    }

    public void deleteTask(int n) {
        inputList.remove(n - 1);
    }

    public void addHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("|Task Type | Done | Description | Time |\n"
                + "|----------|------|-------------|------|\n");
        fw.close();
    }

    public void updateFile() throws IOException {
        addHeader();
        for (Task task : inputList) {
            appendTaskToFile(task);
        }
    }

    public void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    private String toMarkdown(Task task) {
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
