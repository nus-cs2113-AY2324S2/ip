package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createFile() {
        try {
            File dataFile = new File(filePath);
            if (!dataFile.getParentFile().exists()) {
                Files.createDirectories(dataFile.getParentFile().toPath());
            }
        } catch (IOException e) {
            System.out.println("⊙﹏⊙ Not able to create a file for the tasks. " + e.getMessage());
        }
    }

    protected void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    protected void saveTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            try {
                this.writeToFile("", false);
                return;
            } catch (IOException e) {
                System.out.println("⊙﹏⊙ Not able to save your task.");
            }
        }

        try {
            this.writeToFile(tasks.get(0).toString(), false);
        } catch (IOException e) {
            System.out.println("⊙﹏⊙ Not able to save your task.");
        }
        int i = 1;
        while (i < tasks.size()) {
            try {
                this.writeToFile(tasks.get(i).toString(), true);
                i += 1;
            } catch (IOException e) {
                System.out.println("⊙﹏⊙ Not able to save your task.");
            }
        }
    }

    protected void loadFile(ArrayList<Task> tasks) {
        File dataFile = new File(filePath);
        try {
            Scanner s = new Scanner(dataFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                loadTask(s.nextLine(), tasks);
            }
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    protected void loadTask(String line, ArrayList<Task> tasks) {
        line = line.replace("[", "");
        line = line.replace("]", "");

        char type = line.charAt(0);
        boolean isCompleted = (line.charAt(1) == ('X'));

        line = line.substring(3);
        if (type == 'T') {
            tasks.add(new ToDo(line, isCompleted));
        } else if (type == 'D') {
            String[] taskAndDate = changeTaskFormat(line, type);
            tasks.add(new Deadline(taskAndDate[0], taskAndDate[1], isCompleted));
        } else if (type == 'E') {
            String[] taskAndDate = changeTaskFormat(line, type);
            tasks.add(new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2], isCompleted));
        }
    }

    protected String[] changeTaskFormat(String line, char type) {
        String[] taskAndDate;
        if (type == 'D') {
            taskAndDate = line.split("\\(by:");
            taskAndDate[1] = "by" + taskAndDate[1];
            taskAndDate[1] = taskAndDate[1].substring(0, taskAndDate[1].length() - 1);
        } else {
            taskAndDate = line.split("\\(from:|to:");
            taskAndDate[1] = "from" + taskAndDate[1];
            taskAndDate[2] = "to" + taskAndDate[2];
            taskAndDate[2] = taskAndDate[2].substring(0, taskAndDate[2].length() - 1);
        }
        return taskAndDate;
    }
}
