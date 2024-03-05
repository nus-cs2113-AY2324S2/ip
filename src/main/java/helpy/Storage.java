package helpy;

import helpy.exceptions.IllegalDescriptionException;
import helpy.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected File savedTasks;
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        savedTasks = new File(filePath);
        try {
            if (savedTasks.getParentFile().mkdirs() && savedTasks.createNewFile()) {
                return;
            }
        } catch (IOException e) {
            System.out.println("Error creating save file.");
            return;
        }
        try {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNext()) {
                String taskInfo = scanner.nextLine();
                loadTask(taskInfo, taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found.");
        }


    }

    public void loadTask(String taskInfo, TaskList taskList) {
        String[] details = taskInfo.split(" \\| ");
        if (details[0].equals("T")) {
            try {
                Todo newTodo = new Todo("todo " + details[2]);
                if (details[1].equals("1")) {
                    newTodo.setDone(true);
                }
                taskList.addTask(newTodo);
            } catch (IllegalDescriptionException ignored) {} // Ignore corrupted lines
        } else if (details[0].equals("E")) {
            try {
                Event newEvent = new Event("event " + details[2]);
                if (details[1].equals("1")) {
                    newEvent.setDone(true);
                }
                taskList.addTask(newEvent);
            } catch (ArrayIndexOutOfBoundsException ignored) {} // Ignore corrupted lines
        } else if (details[0].equals("D")) {
            try {
                Deadline newDeadline = new Deadline("deadline " + details[2]);
                if (details[1].equals("1")) {
                    newDeadline.setDone(true);
                }
                taskList.addTask(newDeadline);
            } catch (ArrayIndexOutOfBoundsException ignored) {} // Ignore corrupted lines
        }
    }

    public void update(TaskList taskList) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath);
        for (Task task : taskList.getTasks()) {
            task.saveToFile(filePath);
        }
        helpyWriter.close();
    }
}
