package com.arriky.task;
import com.arriky.exception.ArrikyRuntimeException;
import com.arriky.exception.ErrorMessage;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> tasklist = new ArrayList<Task>();

    public TaskList() {}

    public TaskList(ArrayList<String> loadedData) throws ArrikyRuntimeException {
        importTaskList(loadedData);
    }

    public int getTaskCount() {
        return tasklist.size();
    }

    public String getSummaryByIndex(int index) {
        return tasklist.get(index).getSummary();
    }

    public ArrayList<String> find(String keywords) {
        ArrayList<String> results = new ArrayList<String>();
        for (Task t : tasklist) {
            if (t.taskName.indexOf(keywords) != -1) {
                results.add(t.getSummary());
            }
        }
        return results;
    }

    public void addToDo(String taskName, boolean isCompleted) {
        tasklist.add(new ToDo(taskName, isCompleted));
    }

    public void addEvent(String taskName, String startTime, String endTime, boolean isCompleted) throws ArrikyRuntimeException {
        tasklist.add(new Event(taskName, startTime, endTime, isCompleted));
    }

    public void addDeadline(String taskName, String dueTime, boolean isCompleted) throws ArrikyRuntimeException {
        tasklist.add(new Deadline(taskName, dueTime, isCompleted));
    }

    public void markDone(int index) {
        tasklist.get(index).isCompleted = true;
    }

    public void unmarkDone(int index) {
        tasklist.get(index).isCompleted = false;
    }

    public void delete(int index) {
        tasklist.remove(index);
    }

    public ArrayList<String> getSerializableStrings() {
        ArrayList<String> dataToWrite = new ArrayList<String>();
        for (Task t : tasklist) {
            String temp = t.getSerializable();

            // replace all space in the string to save with a special character
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

