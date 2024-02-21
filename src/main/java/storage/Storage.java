package storage;

import commandexceptions.JingHaoExceptions;

import tasktype.TaskList;
import tasktype.Task;
import tasktype.Todo;
import tasktype.Deadline;
import tasktype.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/tasklist.txt";

    public static TaskList readFile() throws JingHaoExceptions, IOException{
        TaskList currentList = new TaskList();
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] informations = currentLine.split(",");
                String taskType = informations[0];
                switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(informations[2]);
                    updateCheck(newTodo, informations[1], currentList);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(informations[2], informations[3]);
                    updateCheck(newDeadline, informations[1], currentList);
                    break;
                case "E":
                    Event newEvent = new Event(informations[2], informations[3], informations[4]);
                    updateCheck(newEvent, informations[1], currentList);
                    break;
                default:
                    throw new JingHaoExceptions();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please restart the bot.");
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        return currentList;
    }

    public static void updateCheck(Task task, String information, TaskList list) throws FileNotFoundException {
        if(information.equalsIgnoreCase("TRUE")) {
            task.check();
        }
        list.add(task);
    }

    public static void updateDisk(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task item: taskList) {
            fw.write(item.toDiskFormat());
        }
        fw.close();
    }
}
