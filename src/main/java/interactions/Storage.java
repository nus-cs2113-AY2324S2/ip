package interactions;

import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Acts as storage handler that writes to a txt file based on the file path given.
 */
public class Storage {
    /**
     * Returns individual line that describes the task inputted, such as its type, if it's marked
     * and any other additional information, if any, all of which will be written to the file to
     * be saved.
     *
     * @param task Task such as Deadline, ToDo and Event.
     * @return Line to be written to file that describes task
     */
    protected String writeLine(Task task) {
        String taskType = task.getTaskType();
        String checkbox = task.isMarked() ? "[/]" : "[ ]";
        String additionalInfo;
        if (task.getTaskType().equals("D")) {
            additionalInfo = ((Deadline)task).getDeadline();
        }
        else if (task.getTaskType().equals("E")) {
            additionalInfo = ((Event)task).getEventFrom() + " -> " + ((Event)task).getEventTo();
        } else {
            additionalInfo = "";
        }
        return taskType + " | " + checkbox + " | " + task.getTaskDescription() +
                (taskType.equals("T") ? "" : " | " + additionalInfo);
    }

    /**
     * Saves all tasks in the task list to a txt file, given the file path.
     * If the file path does not exist, a new file path will be created
     *
     * @param filePath File path where the file being written is or will be located.
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @throws IOException If failed to perform file writing.
     */
    public void saveToFile(String filePath, TaskList taskList) throws IOException {
        ArrayList<Task> list = taskList.getList();
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : list) {
                fw.write(writeLine(task) + System.lineSeparator());
            }
        }
    }

    public Storage() {

    }
}
