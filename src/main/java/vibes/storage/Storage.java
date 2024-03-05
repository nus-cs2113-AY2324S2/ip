package vibes.storage;

import vibes.task.TaskList;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String DATA_FOLDER = "./data/";
    public static final String DATA_FILE = "tasks.txt";
    public static final String PARAM_SEPARATOR = " | ";

    public static void writeToFile(TaskList taskList) throws IOException {
        // Clear the file content before writing
        FileWriter fileClearer = new FileWriter(DATA_FOLDER + DATA_FILE);
        fileClearer.write("");
        fileClearer.close();

        FileWriter fileWriter = new FileWriter(DATA_FOLDER + DATA_FILE, true);
        for (Task task : taskList.tasks) {
            if (task == null) {
                break;
            }

            String textToWrite = "";
            switch (task.getTaskType()) {
            case 'T':
                Todo todoTask = (Todo) task;
                textToWrite = todoTask.getTaskType() + PARAM_SEPARATOR + (todoTask.isDone() ? 1 : 0) + PARAM_SEPARATOR
                        + todoTask.getDescription();
                break;
            case 'D':
                assert task instanceof Deadline;
                Deadline deadlineTask = (Deadline) task;
                textToWrite = deadlineTask.getTaskType() + PARAM_SEPARATOR + (deadlineTask.isDone() ? 1 : 0) +
                        PARAM_SEPARATOR + deadlineTask.getDescription() + PARAM_SEPARATOR + deadlineTask.getBy();
                break;
            case 'E':
                assert task instanceof Event;
                Event eventTask = (Event) task;
                textToWrite = eventTask.getTaskType() + PARAM_SEPARATOR + (eventTask.isDone() ? 1 : 0) +
                        PARAM_SEPARATOR + eventTask.getDescription() + PARAM_SEPARATOR + eventTask.getFrom()
                        + PARAM_SEPARATOR + eventTask.getTo();
                break;
            }
            fileWriter.write(textToWrite + "\n");
        }
        fileWriter.close();
    }

    public static void loadTasks(TaskList taskList) throws FileNotFoundException {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.err.println("Failed to create data folder.");
                return;
            }
        }

        File file = new File(DATA_FOLDER + DATA_FILE);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.err.println("Failed to create data file.");
                    return;
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the data file: " + e.getMessage());
                return;
            }
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            readTask(taskList, s.nextLine());
        }
    }

    private static void readTask(TaskList taskList, String textLine) {
        char taskType = textLine.charAt(0);
        boolean isMarked = (textLine.charAt(4) == '1');
        String description;

        switch (taskType) {
        case 'T':
            description = textLine.substring(8).trim();
            taskList.addTodo(description);
            if (isMarked) {
                taskList.tasks.get(taskList.tasks.size() - 1).setDone(true);
            }
            break;
        case 'D':
            description = textLine.substring(8, textLine.indexOf('|', 8)).trim();
            String by = textLine.substring(textLine.indexOf('|', 8) + 1).trim();
            taskList.addDeadline(description, by);
            if (isMarked) {
                taskList.tasks.get(taskList.tasks.size() - 1).setDone(true);
            }
            break;
        case 'E':
            description = textLine.substring(8, textLine.indexOf('|', 8)).trim();
            String from = textLine.substring(textLine.indexOf('|', 8) + 1, textLine.lastIndexOf('|'))
                    .trim();
            String to = textLine.substring(textLine.lastIndexOf('|') + 1).trim();
            taskList.addEvent(description, from, to);
            if (isMarked) {
                taskList.tasks.get(taskList.tasks.size() - 1).setDone(true);
            }
            break;
        }
    }
}
