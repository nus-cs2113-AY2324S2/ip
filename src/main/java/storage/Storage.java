package storage;

import commandexceptions.JingHaoExceptions;
import tasktype.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/tasklist.txt";

    public static TaskList readFile() throws JingHaoExceptions{
        TaskList currentList = new TaskList();
        try{
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                String currentLine = s.nextLine();
                String[] informations = currentLine.split(",");
                String taskType = informations[0];
                switch (taskType){
                case "T":
                    Todo newTodo = new Todo(informations[2]);
                    if(informations[1].equalsIgnoreCase("TRUE")){
                        newTodo.check();
                    }
                    currentList.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(informations[2], informations[3]);
                    if(informations[1].equalsIgnoreCase("TRUE")){
                        newDeadline.check();
                    }
                    currentList.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(informations[2], informations[3], informations[4]);
                    if(informations[1].equalsIgnoreCase("TRUE")){
                        newEvent.check();
                    }
                    currentList.add(newEvent);
                    break;
                default:
                    throw new JingHaoExceptions();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please restart the bot.");
        }
        return currentList;
    }

    public static void updateDisk(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task item: taskList){
            fw.write(item.toDiskFormat());
        }
        fw.close();
    }
}
