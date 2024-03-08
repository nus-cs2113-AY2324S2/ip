package lovie.task;

import lovie.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList;
    private Ui ui;

    public TaskList() {
        tasksList = new ArrayList<>();
        ui = new Ui();
    }

    public TaskList(ArrayList<Task> oldList) {
        tasksList = oldList;
        ui = new Ui();
    }

    public void deleteTask(String input) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.size() || taskNumber < 0) {
            ui.noValidNumberPrinter(input);
        } else {
            Task selectedTask = tasksList.get(taskNumber);
            tasksList.remove(taskNumber);
            ui.deleteTaskPrinter(selectedTask, tasksList);
        }
    }

    public void addTask(Task newTask) {
        tasksList.add(newTask);
    }

    public int getSize() {
        return tasksList.size();
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public void printTasks() {
        if (tasksList.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            ui.listPrinter(tasksList);
        }
    }
    public void unmarkTask(int index) {
        Task selectedTask = tasksList.get(index);
        selectedTask.markAsUndone();
    }

    public void markTask(int index) {
        Task selectedTask = tasksList.get(index);
        selectedTask.markAsDone();
    }

    public void find(String keyword) {
        TaskList matchedList = new TaskList();
        if (tasksList.isEmpty()) {
            ui.emptyListPrinter();
        } else {
            for (int i = 0; i < tasksList.size(); i += 1) {
                if (tasksList.get(i).getDescription().contains(keyword)) {
                    matchedList.addTask(tasksList.get(i));
                }
            }
            ui.findPrinter(matchedList); 
        }
    }
}
