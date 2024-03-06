package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

import task.*;
import ui.Ui;

import exception.DukeException;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    private String filePath =  "./data/duke.txt";
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui("aoliba");
    }

    public TaskList loadTasksFromFile() throws DukeException{
        TaskList tasks = new TaskList();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Task task = createTaskFromString(line);
                if (task != null){
                    tasks.addTask(task);
                }
            }
            bufferedReader.close();
            System.out.println("Tasks loaded from file: " + filePath + " already!");
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    private Task createTaskFromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Invalid task format from file.");
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "D":
                String deadline = parts[3];
                task = new Deadline(description, deadline);
                break;
            case "E":
                String start = parts[3].split(" - ")[0];
                String end = parts[3].split(" - ")[1];
                task = new Event(description, start, end);
                break;
            case "T":
                task = new Todo(description);
                break;
        }

        if (isDone && task != null){
            task.markAsDone();
        }

        return task;
    }

    public void saveTasksToFile(List<Task> tasks) {
        try {
            File folder = new File("./data");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                bufferedWriter.write(task.saveString()); // saveString!
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Tasks saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
