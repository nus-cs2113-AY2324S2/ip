package Bobble;

import Bobble.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    public void markTask(int taskNumber) {
        taskList.get(taskNumber).setDone(true);
    }

    public void unmarkTask(int taskNumber) {
        taskList.get(taskNumber).setDone(false);
    }
}

