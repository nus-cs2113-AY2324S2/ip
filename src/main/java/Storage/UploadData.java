package Storage;

import TaskList.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

import static Storage.AppendData.*;

public class UploadData {

    private static final String ERROR_MESSAGE = "Error saving file.";
    private static boolean isFirstWrite = true;
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
