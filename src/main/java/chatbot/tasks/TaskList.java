package chatbot.tasks;

import chatbot.ui.UI;

import java.util.ArrayList;

/**
 * Represents a list of tasks of the chatbot.
 */
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
    /**
     * Adds a task to the list.
     *
     * @param isMarked If the task had been marked as done or not.
     * @param task The task to be added.
     */
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
    /**
     * Deletes a selected task.
     *
     * @param taskNum The index of the task to delete.
     */
    public void delete(int taskNum) {
        tasks.remove(taskNum);
        listLength -= 1;
    }
    /**
     * Marks the last item in the list.
     */
    public void markLast() {
        tasks.get(listLength - 1).markAsDone();
    }

    /**
     * Finds and prints tasks containing a keyword.
     *
     * @param keyWords The keyword/s to look for.
     */
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
