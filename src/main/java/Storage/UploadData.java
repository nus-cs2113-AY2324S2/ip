package Storage;

import TaskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

import static Storage.AppendData.*;


/**
 * Represents a utility class which updates the storage file with the current TaskList.
 */
public class UploadData {

    private static final String ERROR_MESSAGE = "Error saving file.";
    private static boolean isFirstWrite = true;

    /**
     * Updates the storage file with the tasks from the TaskList.
     * @param filepath The path to the storage file
     * @param tasks The TaskList containing the tasks to be updated in the file
     * @throws IOException If an I/O error occurs while updating the file
     */
    public static void updateFile(String filepath,TaskList tasks) throws IOException {
        for (Task task : tasks.getAll()) {
            FileWriter fw = new FileWriter(filepath, !isFirstWrite);
            if (task.getTaskType().equals("T")){
                try {
                    appendToDo((ToDo) task, fw);
                } catch (IOException e){
                    System.out.println(ERROR_MESSAGE);
                }
            } else if (task.getTaskType().equals("E")){
                try {
                    appendEvent((Event) task,fw);
                } catch (IOException e) {
                    System.out.println(ERROR_MESSAGE);
                }
            } else {
                try {
                    appendDeadline((Deadline) task,fw);
                } catch (IOException e) {
                    System.out.println(ERROR_MESSAGE);
                }
            }
            isFirstWrite = false;
            fw.close();
        }
    }
}
