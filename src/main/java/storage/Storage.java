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

/**
 * Represents the file storage of the JingHao chatbot.
 * Responsible for saving and loading of the task data to and fro the text file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a new Storage object with the specified filepath.
     *
     * @param file The filepath of the saved text file containing the tasks.
     */
    public Storage(String file) {
        filePath = file;
    }

    /**
     * Loads the task list from the text file and returns the TaskList.
     *
     * @return The taskList with the loaded tasks.
     * @throws JingHaoExceptions If there is an error loading the tasks.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList readFile() throws JingHaoExceptions, IOException{
        TaskList currentList = new TaskList();
        boolean isFromFile = true;
        try {
            File f = new File(filePath);
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
                    String date = informations[3].replace("T", " ").replace(":","");
                    Deadline newDeadline = new Deadline(informations[2], date, isFromFile);
                    updateCheck(newDeadline,informations[1], currentList);
                    break;
                case "E":
                    String start = informations[3].replace("T", " ").replace(":","");
                    String end = informations[4].replace("T", " ").replace(":","");
                    Event newEvent = new Event(informations[2], start, end, isFromFile);
                    updateCheck(newEvent, informations[1], currentList);
                    break;
                default:
                    String corruptedFile= "~~~~~~~~~~~~~~~~Error!! Corrupted datafile~~~~~~~~~~~~~~~~~~\n" +
                            "\n             Deleting corrupted data file :(\n";
                    throw new JingHaoExceptions(corruptedFile);
                }
            }
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        return currentList;
    }

    /**
     * Updates the status of the task based on the loaded data before adding it to the list.
     *
     * @param task The type of task (Todo/Deadline/Event).
     * @param information The details of the task.
     * @param list The taskList containing the loaded tasks from text file.
     */
    public void updateCheck(Task task, String information, TaskList list) {
        if(information.equalsIgnoreCase("TRUE")) {
            task.check();
        }
        list.add(task);
    }

    /**
     * Updates the list of tasks to the text file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If there is an error appending the new task.
     */
    public void updateDisk(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task item: taskList) {
            fw.write(item.toDiskFormat());
        }
        fw.close();
    }
}
