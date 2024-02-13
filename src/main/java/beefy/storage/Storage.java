package beefy.storage;

import java.io.File;
import beefy.BeefyException;
import beefy.task.*;
import beefy.ui.Ui;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String FILE_PATH = "data/beefy.txt";

    public static TaskList readDisk() throws FileNotFoundException, BeefyException {
        File f = new File(FILE_PATH;
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        TaskList userTasks = new TaskList();
        int numOfTasks = 0;
        while (s.hasNext()) {
            String currLine = s.nextLine();
            String[] currParams = currLine.split(",");
            switch(currParams[0]) {
                case "T":
                    userTasks.addTask(currParams[2], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks);
                    }
                    break;
                case "D":
                    userTasks.addTask(currParams[2], currParams[3], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks);
                    }
                    break;
                case "E":
                    userTasks.addTask(currParams[2], currParams[3], currParams[4], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks);
                    }
                    break;
                default:
                    throw new BeefyException("OH NO! beefy.txt FILE IS CORRUPTED!");
            }
        }
        return userTasks;
    }

    public static void deleteFromDisk(TaskList taskList, int taskId) throws IOException {
        File f = new File("data/beefy.txt");
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task currTask : taskList.getTasks()) {
            fw.write(currTask.toDiskFormat());
        }
        fw.write("");
        fw.close();
    }

    public static void addToDisk(String taskLine) throws IOException{
        File f = new File("data/beefy.txt");
        FileWriter fa = new FileWriter(FILE_PATH, true);
        fa.write(taskLine);
        fa.close();
    }
}
