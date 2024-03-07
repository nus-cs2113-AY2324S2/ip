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

    public void findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        Task task;

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println(Ui.LINE_WRAP + "No matching tasks found.\n" + Ui.LINE_WRAP);
        } else {
            System.out.println(Ui.LINE_WRAP + "Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
            System.out.println(Ui.LINE_WRAP);
        }
    }
}

