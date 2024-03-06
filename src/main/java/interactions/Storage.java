package interactions;

import interactions.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String writeLine(ToDo todo) {
        String taskType = todo.getTaskType();
        String checkbox = todo.isMarked() ? "[/]" : "[ ]";
        String additionalInfo = "";
        if (todo.haveDeadline()) {
            additionalInfo = todo.getDeadline();
        }
        else if (todo.isEvent()) {
            additionalInfo = todo.getEventFrom() + " -> " + todo.getEventTo();
        }
        return taskType + " | " + checkbox + " | " + todo.getTask() +
                (taskType.equals("T") ? "" : " | " + additionalInfo);
    }
    public void saveToFile(String filePath, TaskList taskList) throws IOException {
        ArrayList<ToDo> list = taskList.getList();
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        FileWriter fw = new FileWriter(filePath);
        for (ToDo todo : list) {
            fw.write(writeLine(todo) + System.lineSeparator());
        }
        fw.close();
    }
    public Storage() {

    }
}
