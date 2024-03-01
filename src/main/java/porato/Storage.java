package porato;

import porato.tasks.Task;
import porato.tasks.Events;
import porato.tasks.ToDos;
import porato.tasks.Deadlines;
import porato.tasks.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage of the task list
 * It contains methods to save and load the text file from a directory on the computer
 */
public class Storage {
    /** The file path of the storage file */
    private String filePath;

    public Storage(String filepath){
        this.filePath = filepath;
    }
    /**
     * Loads the file contents into the task list
     * @param tasklist The task list
     * @throws FileNotFoundException File is not found exception
     * @throws PoratoException File is corrupted exception
     */
    public void loadFile(TaskList tasklist) throws FileNotFoundException, PoratoException {
        // Creates a file object
        File poratoFile = new File(filePath);
        // Create a Scanner using the File as the source
        Scanner line = new Scanner(poratoFile);
        // Loads the file contents into the task list
        while (line.hasNext()) {
            String[] sentence = line.nextLine().split("/");
            switch (sentence[0]){
            case "T":
                Task todoTask = new ToDos(sentence[2]);
                tasklist.addTask(todoTask);
                if(sentence[1].equals(" 1 ")){
                    todoTask.setTaskStatus(true);
                }
                break;
            case "D":
                Task deadlineTask = new Deadlines(sentence[2], sentence[3]);
                tasklist.addTask(deadlineTask);
                if(sentence[1].equals(" 1 ")){
                    deadlineTask.setTaskStatus(true);
                }
                break;
            case "E":
                Task eventTask = new Events(sentence[2], sentence[3], sentence[4]);
                tasklist.addTask(eventTask);
                if(sentence[1].equals(" 1 ")){
                    eventTask.setTaskStatus(true);
                }
                break;
            default:
                throw new PoratoException("File is corrupted or has invalid format");
            }
        }
    }

    /**
     * Saves the current task list into the file in that file path
     * @throws IOException
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : taskList.getTasks()) {
            if (task instanceof ToDos) {
                fw.write("T/ ");
            } else if (task instanceof Events) {
                fw.write("E/ ");
            } else if (task instanceof Deadlines) {
                fw.write("D/ ");
            }
            fw.write((task.getTaskStatus() ? "1 /" : "0 /") + task.getTask() + System.lineSeparator());
        }
        fw.close();
    }
}
