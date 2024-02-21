package lotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;
import lotes.task.TaskList;
import lotes.task.Task;

public class Storage {
    public static String directoryName = "./data/";
    public static String filename = "./data/storage.txt";

    // Creates the directory and file to store the tasks

    public static void createFile() {
        try {
            Path dirPath = Paths.get(directoryName);
            if (!Files.exists(dirPath)){
                Files.createDirectory(dirPath);
                FileWriter fw = new FileWriter(filename);

                fw.close();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // Reads the file containing the tasks and storing them into the array

    public static void readFile() {
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                TaskList.readFromStorage(scanner.nextLine());
            }

        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            createFile();
        }
    }

    // Update the current file with the updated task list

    public static void updateFile(TaskList taskList){
        try {
            FileWriter fw = new FileWriter(filename);
            StringBuilder sb = new StringBuilder();

//            for(int i = 0; i < TaskList.getTaskCount(); i++) {
//                sb.append(TaskList.taskList[i]).append(TaskList.separator);
//            }
            for (Task task : TaskList.taskList) {
                sb.append(task).append(TaskList.separator);
            }

            fw.write(String.valueOf(sb));
            fw.close();
        } catch (IOException e) {
            //System.out.println("Issues with updating the file");
            //e.printStackTrace();
            createFile();
        }
    }

}
