package interactions;

import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
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
    public void saveToFile(String filePath, TaskList taskList) throws IOException {
        ArrayList<Task> list = taskList.getList();
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(writeLine(task) + System.lineSeparator());
        }
        fw.close();
    }
    public Storage() {

    }
}
