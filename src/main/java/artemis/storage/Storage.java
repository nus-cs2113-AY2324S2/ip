package artemis.storage;

import artemis.errors.Errors;
import artemis.processing.Parser;
import artemis.tasks.Deadline;
import artemis.tasks.Event;
import artemis.tasks.Task;
import artemis.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    save format:
    first line: username
    following lines: task data
    taskData format: [T/D/E] | [M/U] | TaskName | Other Info (due, from/to)
 */

public class Storage {
    private static final String DEFAULT_SAVE_FILEPATH = "./save.txt";

    private final String path;

    private String username;
    private ArrayList<Task> taskList;

    public Storage() throws Errors.CorruptedSaveException {
        this(DEFAULT_SAVE_FILEPATH);
    }

    public Storage(String filePath) throws Errors.CorruptedSaveException {
        this.path = filePath;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void loadSave() throws Errors.CorruptedSaveException, FileNotFoundException {
        this.taskList = new ArrayList<>();
        File saveFile = new File(this.path);

        if (!saveFile.exists() || !saveFile.isFile()) {
            throw new FileNotFoundException();
        }

        Scanner fileScan = new Scanner(saveFile);

        boolean usernameFound = false;
        while (fileScan.hasNext()) {
            if (!usernameFound) {
                this.username = fileScan.nextLine();
                usernameFound = true;
            }

            try {
                String[] currentTaskArray = fileScan.nextLine().split(" \\| ");
                String taskName = currentTaskArray[2];
                boolean isDone = currentTaskArray[1].equals("M");
                Task currentTask = Parser.parseSaveData(currentTaskArray, taskName, isDone);

                this.taskList.add(currentTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Errors.CorruptedSaveException();
            }
        }

        System.out.println("[artemis]: saved data successfully retrieved!");
    }

    public void saveData(String username, ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(DEFAULT_SAVE_FILEPATH, false)) {
            fw.write(username + System.lineSeparator());

            for (Task currentTask : taskList) {
                Class<? extends Task> currentClass = currentTask.getClass();
                String currentOutput = formatSaveData(currentTask, currentClass);

                fw.write(currentOutput);
            }

            System.out.println("[artemis]: successfully saved data!");

        } catch (IOException e) {
            System.out.println("IO ERROR" + e);
        }

    }

    private String formatSaveData(Task currentTask, Class<? extends Task> currentClass) {
        String currentOutput = "";
        String currentTaskName = currentTask.getTaskName();
        String isDoneString = currentTask.getIsDone() ? "M" : "U";

        if (currentClass.equals(ToDo.class)) {
            currentOutput = String.format("T | %s | %s\n", isDoneString, currentTaskName);
        } else if (currentClass.equals(Deadline.class)) {
            currentOutput = String.format("D | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Deadline) currentTask).getDueDate());
        } else if (currentClass.equals(Event.class)) {
            currentOutput = String.format("E | %s | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Event) currentTask).getStartDateTime(), ((Event) currentTask).getEndDateTime());
        }
        return currentOutput;
    }
}
