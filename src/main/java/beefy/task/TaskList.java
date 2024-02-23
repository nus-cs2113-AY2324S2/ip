package beefy.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import beefy.BeefyException;
import beefy.storage.Storage;
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public Task addTask(String taskDescription, boolean isInitialize) {
        ToDo userTask = new ToDo(taskDescription);
        tasks.add(userTask);
        numberOfTasks++;
        if (!isInitialize){
            Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
                    + "---Number of Tasks in List: " + numberOfTasks + "---");
        }
        return userTask;
    }

    public Task addTask(String taskDescription, LocalDateTime by, boolean isInitialize) {
        beefy.task.Deadline userTask = new beefy.task.Deadline(taskDescription, by);
        tasks.add(userTask);
        numberOfTasks++;
        if (!isInitialize) {
            Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
                    + "---Number of Tasks in List: " + numberOfTasks + "---");
        }
        return userTask;
    }

    public Task addTask(String taskDescription, LocalDateTime from, LocalDateTime to, boolean isInitialize) {
        beefy.task.Event userTask = new beefy.task.Event(taskDescription, from, to);
        tasks.add(userTask);
        numberOfTasks++;
        if (!isInitialize) {
            Ui.printMessage("---" + taskDescription + " has been added to task list!---" + System.lineSeparator()
                    + "---Number of Tasks in List: " + numberOfTasks + "---");
        }
        return userTask;
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

    public void markTask(int taskId, boolean isInitialize) throws BeefyException {
        Task selectedTask = tasks.get(taskId - 1);
        if (selectedTask.getStatus()) {
            throw new BeefyException("Are you blind mate?");
        } else {
            selectedTask.setMark();
            if (isInitialize) {
                Ui.printMessage("Nice one mate! I've marked this task as done:" + System.lineSeparator()
                        + selectedTask);
            }
        }
    }

    public void unmarkTask(int taskId, boolean isInitialize) throws BeefyException {
        Task selectedTask = tasks.get(taskId - 1);
        if (!selectedTask.getStatus()) {
            throw new BeefyException("Are you blind mate?");
        } else {
            selectedTask.setUnmark();
            if (isInitialize) {
                Ui.printMessage("WHY?! I've marked this task as not done:" + System.lineSeparator()
                        + selectedTask);
            }
        }
    }

    public void deleteTask(int taskId) throws BeefyException {
        String delTaskDescription = tasks.get(taskId - 1).getDescription();
        tasks.remove(taskId - 1);
        numberOfTasks--;
        Ui.printMessage("---" + delTaskDescription + " has been removed from task list!---" + System.lineSeparator()
                + "---Number of Tasks in List: " + numberOfTasks + "---");
    }

    public void findTask(String taskDescription) {
        String message = "Here are the matching tasks in your list: " + System.lineSeparator();
        int taskCount = 1;
        for (Task userTask : tasks) {
            if (userTask.getDescription().contains(taskDescription)) {
                message += taskCount + ". " + userTask + System.lineSeparator();
                taskCount += 1;
            }
        }
        Ui.printMessage(message);
    }
}
