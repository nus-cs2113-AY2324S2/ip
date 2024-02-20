package Misty;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import Misty.Exception.*;
import Misty.Task.*;

public class SaveFile {
    public static void createFiles() throws IOException, SecurityException {
        File dir = new File("data");
        File dataFile = new File("data/misty.txt");

        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
    }

    public static void loadData(List taskList) throws FileNotFoundException, EmptyTaskNameException,
            IllegalListIndexException, EmptyByException, EmptyFromException, EmptyToException,
            UnknownTaskException {
        File dataFile = new File("data/misty.txt");
        Scanner scanner = new Scanner(dataFile);
        int taskCount = 0;

        String [] parameters;

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
                throw new UnknownTaskException();
            }
        }
    }

    public static void saveTodo(Todo todo) throws IOException {
        File dataFile = new File("data/misty.txt");
        FileWriter fileWriter = new FileWriter(dataFile, true);
        String data = String.format("T | %s | %s\n",(todo.getIsDone() ? "1" : "0") ,todo.getDescription());
        fileWriter.write(data);
        fileWriter.close();
    }

    public static void saveDeadLine(Deadline deadline) throws IOException {
        File dataFile = new File("data/misty.txt");
        FileWriter fileWriter = new FileWriter(dataFile, true);
        String data = String.format("D | %s | %s | %s\n",(deadline.getIsDone() ? "1" : "0")
                ,deadline.getDescription(), deadline.getBy());
        fileWriter.write(data);
        fileWriter.close();
    }

    public static void saveEvent(Event event) throws IOException {
        File dataFile = new File("data/misty.txt");
        FileWriter fileWriter = new FileWriter(dataFile, true);
        String data = String.format("E | %s | %s | %s | %s\n",(event.getIsDone() ? "1" : "0")
                ,event.getDescription(), event.getFrom(), event.getTo());
        fileWriter.write(data);
        fileWriter.close();
    }

    public static void refreshSave(Task[] list) throws IOException {
        File dataFile = new File("data/misty.txt");
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write("");

        for (int i = 0 ; i < list.length ; i++ ) {
            if (list[i] instanceof Todo) {
                saveTodo((Todo)list[i]);
            } else if (list[i] instanceof Deadline) {
                saveDeadLine((Deadline)list[i]);
            } else if (list[i] instanceof Event) {
                saveEvent((Event)list[i]);
            } else {
            }
        }

    }

}
