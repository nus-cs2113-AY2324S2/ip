package beefy.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import beefy.BeefyException;
import beefy.storage.Storage;
import beefy.ui.Ui;

/**
 * Represents the lists of tasks of Beefy chatbot.
 * The taskList contains all the tasks managed by Beefy chatbot.
 */
public class TaskList {
    private int numberOfTasks;
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object with an empty list.
     */
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

    /**
     * Adds a new Todo task into taskList
     *
     * @param taskDescription Description of task to be added.
     * @param isInitialize Determines if function is executed when copying task data over from disk.
     * @return Todo task that is added.
     */
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

    /**
     * Adds a new Deadline task into taskList
     *
     * @param taskDescription Description of task to be added.
     * @param by Date that deadline task has to be done by.
     * @param isInitialize Determines if function is executed when copying task data over from disk.
     * @return Deadline task that is added.
     */
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

    /**
     * Adds a new Event task into taskList
     *
     * @param taskDescription Description of task to be added.
     * @param from Start date of event task.
     * @param to End date of event task.
     * @param isInitialize Determines if function is executed when copying task data over from disk.
     * @return Event task that is added.
     */
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

    /**
     * List out all task in taskList.
     */
    public void listOut() {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < numberOfTasks - 1; i++) {
            Task currTask = tasks.get(i);
            message = message.concat((i + 1) + ". " + currTask + "\n");
        }
        message = message.concat((numberOfTasks) + ". " + tasks.get(numberOfTasks - 1));
        Ui.printMessage(message);
    }

    /**
     * Mark a task in taskList as done.
     *
     * @param taskId Id of task to be marked.
     * @param isInitialize Determines if function is executed when copying task data over from disk.
     * @throws BeefyException if task has already been marked.
     */
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

    /**
     * Mark a task in taskList as not done.
     *
     * @param taskId Id of task to be marked as not done.
     * @param isInitialize Determines if function is executed when copying task data over from disk.
     * @throws BeefyException if task has already been marked.
     */
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

    /**
     * Delete a task from taskList.
     *
     * @param taskId Id of task to be deleted.
     */
    public void deleteTask(int taskId) {
        String delTaskDescription = tasks.get(taskId - 1).getDescription();
        tasks.remove(taskId - 1);
        numberOfTasks--;
        Ui.printMessage("---" + delTaskDescription + " has been removed from task list!---" + System.lineSeparator()
                + "---Number of Tasks in List: " + numberOfTasks + "---");
    }

    /**
     * Find all tasks that contain a certain phrase from taskList.
     *
     * @param taskDescription Phrase to be searched for.
     */
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
