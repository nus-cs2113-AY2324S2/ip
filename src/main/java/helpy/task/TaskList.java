package helpy.task;

import helpy.Ui;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public int getListLength() {
        return taskList.toArray().length;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean markTask(String commandStart, int index, Ui ui) {
        Task taskToMark = taskList.get(index);
        if (commandStart.equals("mark") && taskToMark.isDone()) {
            ui.printMessage("You've already completed the selected task o.o");
            return false;
        } else if (commandStart.equals("unmark") && !taskToMark.isDone()) {
            ui.printMessage("The selected task isn't completed so you can't unmark it -.-\"");
            return false;
        }
        taskToMark.setDone(!taskToMark.isDone());
        return true;
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }
}
