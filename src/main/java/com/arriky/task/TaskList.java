package com.arriky.task;
import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;

import java.util.ArrayList;

/**
 * A class that implements that tasklist and facilitates task list operations (e.g. find, remove, mark/unmark as done and insertion).
 * @author Songyue Wang
 */
public class TaskList {
    private final ArrayList<Task> tasklist = new ArrayList<Task>();

    /**
     * Constructor of tasklist.
     * Used when there is no entries to be imported.
     */
    public TaskList() {}

    /**
     * Constructor of tasklist.
     * @param loadedData Arraylist of task entries (in string format) to be imported.
     * @throws ArrikyRuntimeException If there is an error in importing.
     */
    public TaskList(ArrayList<String> loadedData) throws ArrikyRuntimeException {
        importTaskList(loadedData);
    }

    /**
     * Get the number of tasks in tasklist.
     * @return Number of existing tasks.
     */
    public int getTaskCount() {
        return tasklist.size();
    }

    /**
     * Get summary of member task in tasklist.
     * @param index Index of arraylist member.
     * @return Summary string of a specific task.
     */
    public String getSummaryByIndex(int index) {
        return tasklist.get(index).getSummary();
    }

    /**
     * Search for tasks with keywords in task name.
     * @param keywords Keywords in the task name to be searched for.
     * @return An arraylist of the summaries of matching tasks.
     */
    public ArrayList<String> find(String keywords) {
        ArrayList<String> results = new ArrayList<String>();
        for (Task t : tasklist) {
            if (t.getTaskName().contains(keywords)) {
                results.add(t.getSummary());
            }
        }
        return results;
    }

    /**
     * Insert new to-do into tasklist.
     * @param taskName Name of new to-do.
     * @param isCompleted Completion status of new to-do.
     */
    public void addToDo(String taskName, boolean isCompleted) {
        tasklist.add(new ToDo(taskName, isCompleted));
    }

    /**
     * Insert new event into tasklist.
     * @param taskName Name of new event.
     * @param startTime Start time of new event.
     * @param endTime End time of new event.
     * @param isCompleted Completion status of new event.
     * @throws ArrikyRuntimeException If there is an error in adding the event.
     */
    public void addEvent(String taskName, String startTime, String endTime, boolean isCompleted) throws ArrikyRuntimeException {
        tasklist.add(new Event(taskName, startTime, endTime, isCompleted));
    }

    /**
     * Insert new deadline into tasklist.
     * @param taskName Name of new deadline.
     * @param dueTime Due time of new deadline.
     * @param isCompleted Completion status of new deadline.
     * @throws ArrikyRuntimeException If there is an error in adding the deadline.
     */
    public void addDeadline(String taskName, String dueTime, boolean isCompleted) throws ArrikyRuntimeException {
        tasklist.add(new Deadline(taskName, dueTime, isCompleted));
    }

    /**
     * Mark a task as done.
     * @param index Index of the task to be marked as done.
     */
    public void markDone(int index) {
        tasklist.get(index).setAsCompleted();
    }

    /**
     * Undo marking a task as done.
     * @param index Index of the task to be unmarked done.
     */
    public void unmarkDone(int index) {
        tasklist.get(index).setAsIncomplete();
    }

    /**
     * Delete a task from the tasklist.
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        tasklist.remove(index);
    }

    /**
     * Generate string representations of tasklist entries to save those to local file.
     * @return An arraylist with formatted string representation of all task entries in tasklist.
     */
    public ArrayList<String> getSerializableStrings() {
        ArrayList<String> dataToWrite = new ArrayList<String>();
        for (Task t : tasklist) {
            String temp = t.getSerializable();

            // replace all space in the string with a special character before saving to file
            temp = temp.replace(" ","~");
            dataToWrite.add(temp);
        }

        return dataToWrite;
    }

    private void importTaskList(ArrayList<String> data) throws ArrikyRuntimeException {
        try {
            for (String s : data) {
                // replace the special character with space
                String temp = s.replace("~", " ");
                String[] arguments = temp.split(",");
                boolean isCompleted = Boolean.parseBoolean(arguments[1]);

                switch (arguments[0]) {
                case "T":
                    addToDo(arguments[2], isCompleted);
                    break;
                case "E":
                    addEvent(arguments[2], arguments[3], arguments[4], isCompleted);
                    break;
                case "D":
                    addDeadline(arguments[2], arguments[3], isCompleted);
                    break;
                default:
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrikyRuntimeException(ErrorMessage.LOCAL_RECORD_CORRUPTED);
        }

    }
}

