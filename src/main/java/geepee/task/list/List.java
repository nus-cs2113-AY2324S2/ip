package geepee.task.list;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import geepee.task.*;

public class List {

    protected ArrayList<Task> tasks;

    public List(String filePath) {
        try {
            tasks = new ArrayList<>();
            readFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file as file is not found!");
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
            if (taskType.equals("T")) {
                tasks.add(new Todo(words[1]));
            } else if (taskType.equals("D")) {
                tasks.add(new Deadline(words[1], words[2]));
            } else if (taskType.equals("E")) {
                tasks.add(new Event(words[1], words[2], words[3]));
            }
        }
    }

    public void addTodo(String description) {
        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        ListMessage.printAfterAddingTask(tasks.size(), newTodo);
    }

    public void addDeadline(String description, String by) {
        Task newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        ListMessage.printAfterAddingTask(tasks.size(), newDeadline);
    }

    public void addEvent(String description, String from, String to) {
        Task newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        ListMessage.printAfterAddingTask(tasks.size(), newEvent);
    }

    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ListMessage.printAfterRemovingTask(tasks.size(), deletedTask);
    }

    public void changeTaskStatus(int index, boolean isDone) {
        tasks.get(index).changeStatus(isDone);
        ListMessage.printTaskStatusMessage(isDone, tasks.get(index));
    }

    public int getSize() {
        return tasks.size();
    }
}
