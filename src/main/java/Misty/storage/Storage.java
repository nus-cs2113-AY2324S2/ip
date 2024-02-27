package misty.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import misty.data.TaskList;
import misty.data.exception.*;
import misty.data.task.*;

public class Storage {
    private final static  String DEFAULT_DATA_FOLDER_NAME = "data";
    private final static String DEFAULT_DATA_FILE_NAME = "misty.txt";
    private String filePath;
    private File dir = new File(DEFAULT_DATA_FOLDER_NAME);
    private File dataFile;

    public Storage() {
        this.filePath = DEFAULT_DATA_FOLDER_NAME + "/" + DEFAULT_DATA_FILE_NAME;
        dataFile = new File(filePath);
    }

    public void createFiles() throws IOException, SecurityException {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
    }

    private void writeToFile(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile, true);
        fileWriter.write(data);
        fileWriter.close();
    }

    public void loadData(TaskList taskList) throws FileNotFoundException, CorruptedFileException {
        Scanner scanner = new Scanner(dataFile);
        int taskCount = 0;
        String[] parameters;

        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            parameters = taskData.split("\\|");
            if (parameters[0].contains("T")) {
                taskList.loadTodo(parameters[2].trim());
                taskCount++;

                if (parameters[1].contains("1")) {
                    taskList.loadMark(taskCount);
                }
            } else if (parameters[0].contains("D")) {
                taskList.loadDeadline(parameters[2].trim(), parameters[3].trim());
                taskCount++;

                if (parameters[1].contains("1")) {
                    taskList.loadMark(taskCount);
                }
            } else if (parameters[0].contains("E")) {
                taskList.loadEvent(parameters[2].trim(), parameters[3].trim(), parameters[4].trim());
                taskCount++;

                if (parameters[1].contains("1")) {
                    taskList.loadMark(taskCount);
                }
            } else {
                throw new CorruptedFileException();
            }
        }
    }

    public void saveTodo(Todo todo) throws IOException {
        String data = String.format("T | %s | %s\n",(todo.getIsDone() ? "1" : "0") ,todo.getTaskName());
        writeToFile(data);
    }

    public void saveDeadLine(Deadline deadline) throws IOException {
        String data = String.format("D | %s | %s | %s\n",(deadline.getIsDone() ? "1" : "0")
                ,deadline.getTaskName(), deadline.getBy());
        writeToFile(data);
    }

    public void saveEvent(Event event) throws IOException {
        String data = String.format("E | %s | %s | %s | %s\n",(event.getIsDone() ? "1" : "0")
                ,event.getTaskName(), event.getFrom(), event.getTo());
        writeToFile(data);
    }

    public void refreshSave(ArrayList list) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write("");

        for (int i = 0 ; i < list.size() ; i++ ) {
            if (list.get(i) instanceof Todo) {
                saveTodo((Todo)list.get(i));
            } else if (list.get(i) instanceof Deadline) {
                saveDeadLine((Deadline)list.get(i));
            } else if (list.get(i) instanceof Event) {
                saveEvent((Event)list.get(i));
            }
        }
    }
}
