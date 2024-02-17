package geepee.task.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import geepee.system.SystemMessage;
import geepee.task.*;

public class List {

    protected Task[] tasks;
    protected int size;

    public List(String filePath) {
        try {
            tasks = new Task[100];
            size = 0;
            readFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("    No file found, current task list will be empty!");
        }
    }

    private void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String taskType = words[0];
            boolean taskStatus = words[1].equals("X") ? true : false;
            if (taskType.equals("T")) {
                tasks[size] = new Todo(words[2], taskStatus);
                size++;
            } else if (taskType.equals("D")) {
                tasks[size] = new Deadline(words[2], words[3], taskStatus);
                size++;
            } else if (taskType.equals("E")) {
                tasks[size] = new Event(words[2], words[3], words[4], taskStatus);
                size++;
            }
        }
    }

    public void addTodo(String description) {
        tasks[size] = new Todo(description);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void addDeadline(String description, String by) {
        tasks[size] = new Deadline(description, by);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void addEvent(String description, String from, String to) {
        tasks[size] = new Event(description, from, to);
        size++;
        ListMessage.printAfterAddingTask(size, tasks[size - 1]);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks[index].changeStatus(isDone);
        SystemMessage.printHorizontalLine();
        ListMessage.printTaskStatusMessage(isDone, tasks[index]);
        SystemMessage.printHorizontalLine();
    }

    public int getSize() {
        return size;
    }

    public Task[] getTasks() {
        return tasks;
    }
}
