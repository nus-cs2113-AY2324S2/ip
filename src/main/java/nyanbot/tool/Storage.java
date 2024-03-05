package nyanbot.tool;

import nyanbot.task.Deadline;
import nyanbot.task.Event;
import nyanbot.task.Task;
import nyanbot.task.Todo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String STATUS_COMPLETED = "TRUE";
    private static final String TODO_COMMAND = "TODO";
    private static final String EVENT_COMMAND = "EVENT";
    private static final String DEADLINE_COMMAND = "DEADLINE";
    protected static final String fileName = "/nyan.txt";
    private static String directoryPath;
    private static String filePath;
    public Storage(String dataDirectory) {
        directoryPath = dataDirectory;
        filePath = dataDirectory + fileName;
    }

    public ArrayList<Task> readFile() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
                UI.printDirectoryCreated();
            }
            File dataFile = new File(filePath);
            if (!dataFile.exists()) {
                return tasks;
            }
            else {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    Task newTask = readLine(line);
                    tasks.add(newTask);
                }
                s.close();
                return tasks;
            }
        } catch (IOException e) {
            UI.printIOException();
            return null;
        }
    }

    public void writeFile(ArrayList<Task> tasks) {
        try {
            File dataFile = new File(filePath);
            if (dataFile.createNewFile()) {
                UI.printFileCreated();
            }
            FileWriter fw = new FileWriter(dataFile);
            BufferedWriter writer = new BufferedWriter(fw);
            for (Task task : tasks) {
                String line = task.toString();
                writer.write(line + "\n");
            }
            writer.close();
            fw.close();
        } catch (IOException e) {
            UI.printIOException();
        }
    }

    public Task readLine(String line) {
        try {
            String[] tokens = line.split("//");
            String command = tokens[0].toUpperCase();
            String status = tokens[1].toUpperCase();
            Task task = processTask(command, tokens, status);
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printNyanException("corrupted line");
        }
        return null;
    }

    private Task processTask(String command, String[] tokens, String status) {
        Task task = new Task("");
        switch (command) {
            case TODO_COMMAND:
                task = new Todo(tokens[2]);
                break;
            case DEADLINE_COMMAND:
                task = new Deadline(tokens[2], tokens[3]);
                break;
            case EVENT_COMMAND:
                task = new Event(tokens[2], tokens[3], tokens[4]);
                break;
        }
        if (status.equals(STATUS_COMPLETED)) {
            task.markAsDone();
        }
        return task;
    }
}