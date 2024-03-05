package winter;

import winter.task.Task;

import java.util.ArrayList;


public class TaskList {
    private static int currentTaskIndex;
    private static ArrayList<Task> taskList;
    public TaskList() {
        currentTaskIndex = 0;
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        currentTaskIndex = taskArrayList.size()-1;
        taskList = taskArrayList;
    }


    public void addNewTask(Task task) {
        taskList.add(task);
        currentTaskIndex++;
    }


    public ArrayList<Task> deleteTask(int taskNumber) {
        taskList.remove(taskNumber-1);
        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setOrder(i);
        }
        currentTaskIndex--;

        return taskList;

    }

    public ArrayList<Task> getTaskArrayList() {
        return taskList;
    }

    public void markTask(int taskNumber) {
        taskList.get(taskNumber-1).mark();
    }

    public void unmarkTask(int taskNumber) {
        taskList.get(taskNumber-1).unmark();
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber-1);
    }

    public void increaseLastTaskIndex() {
        currentTaskIndex++;
    }
    public void decreaseLastTaskIndex() {
        currentTaskIndex--;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }
}
