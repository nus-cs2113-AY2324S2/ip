package soot.manager;

import soot.task.Deadline;
import soot.task.Event;
import soot.task.Task;
import soot.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SavedFileManager {
    public static ArrayList<String> readSavedFile(String path) throws FileNotFoundException {
        ArrayList<String> readFile = new ArrayList<>();
        File savedFile = new File(path);
        Scanner s = new Scanner(savedFile);
        while (s.hasNext()) {
            readFile.add(s.nextLine());
        }
        s.close();
        return readFile;
    }

    public static void loadSavedTasks(String line) {
        System.out.println("line:" + line);
        String[] taskInfo = line.split(" ; ", 0);
        boolean isTaskDone = !taskInfo[1].trim().equals("0");

        switch (taskInfo[0].trim()) {
        case ("T"):
            CommandManager.taskList.add(new Todo(taskInfo[2], isTaskDone));
            CommandManager.listCounter++;
            break;
        case ("D"):
            CommandManager.taskList.add(new Deadline(taskInfo[2], isTaskDone, taskInfo[3]));
            CommandManager.listCounter++;
            break;
        case ("E"):
            CommandManager.taskList.add(new Event(taskInfo[2], isTaskDone, taskInfo[3], taskInfo[4]));
            CommandManager.listCounter++;
            break;
        default:
            System.out.println("something went wrong, perhaps an invalid task type?");
        }
        System.out.println(Arrays.toString(taskInfo));
    }
}
