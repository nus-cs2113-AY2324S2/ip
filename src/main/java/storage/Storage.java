package storage;
import tasklist.TaskList;
import tasklist.todo.Todo;
import ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    protected String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;
    }

    /**
     * Returns number of task loaded from the data and add tasks from data into ArrayList
     * @param list ArrayList storing Todo type
     * @return taskNum to initialize TaskList in main
     * @throws IOException If Scanner is unable detect file
     */
    public int loadData(ArrayList<Todo> list) throws IOException {
        Ui ui = new Ui();
        int taskNum = 0;
        TaskList tasklist = new TaskList(list, taskNum);
        File storedFile = new File(pathName);
        if (!storedFile.exists()) {
            new File(storedFile.getParent()).mkdirs();
            ui.showMakeDir();
        } else {
            Scanner dataInput = new Scanner(storedFile);
            while (dataInput.hasNext()) {
                String currTask = dataInput.nextLine();
                String type = currTask.substring(1,2);
                currTask = currTask.substring(6); //trim off [T][ ]
                switch (type) {
                case "T":
                    tasklist.addTodo(list, currTask, taskNum);
                    break;
                case "D":
                    tasklist.addDeadline(list, currTask, taskNum);
                    break;
                case "E":
                    tasklist.addEvent(list, currTask, taskNum);
                    break;
                default:
                    //if it is none of the valid types, do not add task
                    taskNum--;
                }
                taskNum++;
            }
        }
        return taskNum;
    }

    /**
     * Adds tasks added into file to be saved.
     * @param task Task to be written into the file.
     */
    public void writeTask(Todo task) throws IOException {
        FileWriter file = new FileWriter(pathName, true);
        file.write(task.getWriteFormat() + System.lineSeparator());
        file.close();
    }
}
