package beefy.task;

import java.util.ArrayList;

import beefy.BeefyException;
import beefy.ui.Ui;

public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    public TaskList() {
        numberOfTasks = 0;
        tasks = new ArrayList<Task>();
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void addTask(String taskDescription) {
        ToDo userTask = new ToDo(taskDescription);
        tasks.add(userTask);
        numberOfTasks++;
        Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
                + "---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void addTask(String taskDescription, String by) {
        Deadline userTask = new Deadline(taskDescription, by);
        tasks.add(userTask);
        numberOfTasks++;
        Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
               + "---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void addTask(String taskDescription, String from, String to) {
        Event userTask = new Event(taskDescription, from, to);
        tasks.add(userTask);
        numberOfTasks++;
        Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
                + "---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void listOut() {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < numberOfTasks - 1; i++) {
            Task currTask = tasks.get(i);
            message = message.concat((i + 1) + ". " + currTask + "\n");
        }
        message = message.concat((numberOfTasks) + ". " + tasks.get(numberOfTasks - 1));
        Ui.printMessage(message);
    }

    public void markTask(int taskId) throws BeefyException {
            Task selectedTask = tasks.get(taskId - 1);
            if (selectedTask.getStatus()) {
                throw new BeefyException("Are you blind mate?");
            } else {
                selectedTask.setMark();
                Ui.printMessage("Nice one mate! I've marked this task as done:" + System.lineSeparator()
                        + selectedTask);
            }
    }

    public void unmarkTask(int taskId) throws BeefyException {
        Task selectedTask = tasks.get(taskId - 1);
        if (!selectedTask.getStatus()) {
            throw new BeefyException("Are you blind mate?");
        } else {
            selectedTask.setUnmark();
            Ui.printMessage("WHY?! I've marked this task as not done:" + System.lineSeparator()
                    + selectedTask);
        }
    }
}
