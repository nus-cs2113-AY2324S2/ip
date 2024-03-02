package chatbot.tasks;

import chatbot.ui.UI;

import java.util.ArrayList;

public class TaskList {
    private int listLength;
    private ArrayList<Task> tasks;
    public TaskList() {
        listLength = 0;
        tasks = new ArrayList<Task>();
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public int getListLength() {
        return listLength;
    }
    public void addTask(boolean isMarked, Task task) {
        if (isMarked) {
            task.markAsDone();
        }
        tasks.add(task);
        listLength += 1;
    }
    public void printAll() {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println((i + 1) + ". " + tasks.get(i).getData());
        }
    }
    public Task get(int taskNum) {
        return tasks.get(taskNum);
    }
    public void delete(int taskNum) {
        tasks.remove(taskNum);
        listLength -= 1;
    }
    public void markLast() {
        tasks.get(listLength - 1).markAsDone();
    public void find(String keyWords) {
        int numFound = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWords)) {
                task.printData();
                numFound += 1;
            }
        }
        if (numFound == 0) {
            UI.printNoMatch();
        }
    }
}
