package suv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

import static suv.TaskList.tasksList;

public class FileStorage {
    public static void ensureDirectoryExists(String filePath) {
        File file = new java.io.File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while creating file: " + e.getMessage());
        }
    }

    public static void saveTasksToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < tasksList.size(); i++) {
                String out = tasksList.get(i).toString();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println("     An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public static void fetchData()  {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            createFile(filePath);
        }
        try{
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String task = s.nextLine();
                boolean isDone = task.contains("[X]");
                String text = task.substring(7);
                if(task.startsWith("[T]")){
                    Todo newTask = new Todo(task.split("]")[1].trim());

                    newTask.isDone = isDone;
                    tasksList.add(newTask);
                }else if (task.startsWith("[D]")){
                    String by = text.substring(text.indexOf("by:") + 3 , text.length() - 1).trim();
                    String description =text.substring(0, text.indexOf("(by:")).trim();

                    Deadline newTask = new Deadline(description, by);
                    newTask.isDone = isDone;
                    tasksList.add(newTask);
                } else if (task.startsWith("[E]")) {
                    String to = text.substring(text.indexOf("to:") + 3 , text.length() - 1).trim();
                    String from = text.substring(text.indexOf("from:") + 5, text.indexOf("to:")).trim();
                    String description =text.substring(0, text.indexOf("(from:")).trim();

                    Event newTask = new Event(description, from, to);
                    newTask.isDone = isDone;
                    tasksList.add(newTask);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

    }
}
