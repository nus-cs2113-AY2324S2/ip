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
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FOLDER = "./data/";
    private static final String DATA_FILE = "tasks.txt";
    private static final String PARAM_SEPARATOR = " , ";
    private final File file;

    public Storage(){
        File folder = new File(DATA_FOLDER);
        file = new File(folder, DATA_FILE);

        try {
            if (!file.exists()) {
                if (!folder.exists() && !folder.mkdirs()) {
                    throw new IOException("Failed to create data folder.");
                }

                if (!file.createNewFile()) {
                    throw new IOException("Failed to create data file.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void writeToFile(TaskList taskList) throws IOException {
        clearFileContent();
        FileWriter fileWriter = new FileWriter(file, true);

        for (Task task : taskList.tasks) {
            if (task == null) {
                break;
            }

            String textToWrite = "";
            switch (task.getTaskType()) {
            case 'T':
                assert task instanceof Todo;
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

    private void clearFileContent() throws IOException {
        FileWriter fileClearer = new FileWriter(file);
        fileClearer.write("");
        fileClearer.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            readTask(taskList, s.nextLine());
        }
        return taskList.tasks;
    }

    private void readTask(TaskList taskList, String textLine) {
        String[] parsedInput = textLine.trim().split(" , ");
        switch (parsedInput[0]){
        case "T":
            taskList.addTodo(parsedInput[2]);
            break;
        case "D":
            String[] deadlineTask = {parsedInput[2], parsedInput[3]};
            taskList.addDeadline(deadlineTask);
            break;
        case "E":
            String[] eventTask = {parsedInput[2], parsedInput[3], parsedInput[4]};
            taskList.addEvent(eventTask);
        }

        if (parsedInput[1].equals("1")) {
            taskList.tasks.get(taskList.tasks.size() - 1).setDone(true);
        }
    }

    public void saveTask(TaskList taskList) {
        try {
            writeToFile(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
