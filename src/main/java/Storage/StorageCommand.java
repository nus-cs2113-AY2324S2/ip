package Storage;

import Parser.CommandParse;
import Sinep.Sinep;
import TaskList.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageCommand {
    public static void loadTaskFile(ArrayList<Task> taskList) throws IOException {
        File taskFile = new File(Sinep.FILE_PATH);
        if (!taskFile.exists()) {
            try {
                File parentDir = taskFile.getParentFile();
                if (!parentDir.exists() && parentDir.mkdirs()) {
                     // Create the directory if it doesn't exist
                    throw new IOException("Failed to create directory " + parentDir.getAbsolutePath());
                }
                File dataFile = new File(Sinep.FILE_PATH);
                if (!dataFile.getParentFile().exists()) {
                    Files.createDirectories(dataFile.getParentFile().toPath());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while trying to create the file: " + e.getMessage());
                return; // Exit the method if file creation fails
            }
        }
        try {
            Scanner scanner = new Scanner(taskFile);
            int counter = 0;
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (nextLine.startsWith("[T]")) {
                    String description = CommandParse.getTodoString(nextLine);
                    taskList.add(new Todo(description));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                } else if (nextLine.startsWith("[D]")) {
                    String command = CommandParse.getDeadlineCommand(nextLine);
                    System.out.println(command);
                    taskList.add(new Deadline(command));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                } else {
                    String command = CommandParse.getEventCommand(nextLine);
                    taskList.add(new Event(command));
                    if (nextLine.charAt(4) == 'X') {
                        Task markingTask = taskList.get(counter);
                        markingTask.markAsDone();
                    }
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            File parentDir = taskFile.getParentFile();
            if (!parentDir.exists() && parentDir.mkdirs()) {
                throw new IOException("File not found.");
            }
        }
    }

    protected static void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        File taskFile = new File(Sinep.FILE_PATH);
        if (!taskFile.getParentFile().exists() && taskFile.getParentFile().mkdirs()) {
            throw new IOException("File not found."); // This will create the directory if it doesn't exist
        }
        FileWriter fw = new FileWriter(Sinep.FILE_PATH, isAppend);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void saveTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            try {
                writeToFile("", false);
            } catch (IOException e) {
                System.out.println("Error saving your file. IO issues.");
            }
        } else {
            try {
                writeToFile(taskList.get(0).toString(), false);
            } catch (IOException e) {
                System.out.println("Error saving your file. IO issues.");
            }
            int i = 1;
            while (i < taskList.size()) {
                try {
                    writeToFile(taskList.get(i).toString(), true);
                    i += 1;
                } catch (IOException e) {
                    System.out.println("Error saving your file. IO issues.");
                }
            }
        }
    }
}
