package com.arriky.task;

import com.arriky.utilities.FileIO;
import com.arriky.exception.ErrorMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasklist = new ArrayList<Task>();

    private ErrorMessage errMsg = new ErrorMessage();

    public void listTasks() {
        int i = 1;
        for (Task task:tasklist) {
            System.out.println(" " + i + "." + task.getSummary());
            i++;
        }
    }

    public void addToDo(String taskName, boolean isCompleted) {
        tasklist.add(new ToDo(taskName, isCompleted));
        printInsertionAcknowledgement();
    }

    public void addEvent(String taskName, String startTime, String endTime, boolean isCompleted) {
        tasklist.add(new Event(taskName, startTime, endTime, isCompleted));
        printInsertionAcknowledgement();
    }

    public void addDeadline(String taskName, String dueTime, boolean isCompleted) {
        tasklist.add(new Deadline(taskName, dueTime, isCompleted));
        printInsertionAcknowledgement();
    }

    private void printInsertionAcknowledgement() {
        int size = tasklist.size();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasklist.get(size - 1).getSummary());
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void markDone(int index) {
        tasklist.get(index).isCompleted = true;
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasklist.get(index).getSummary());
    }

    public void unmarkDone(int index) {
        tasklist.get(index).isCompleted = false;
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + tasklist.get(index).getSummary());
    }

    public void delete(int index) {
        Task t = tasklist.get(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + tasklist.get(index).getSummary());
        tasklist.remove(index);
        System.out.println(" Now you have " + tasklist.size() + " tasks in the list.");
    }

    public void saveTaskList() {
        ArrayList<String> dataToWrite = new ArrayList<String>();
        for (Task t : tasklist) {
            String temp = t.getSerializable();

            // replace all space in the string to save with a special character
            temp = temp.replace(" ","~");
            dataToWrite.add(temp);
        }

        try {
            FileIO.writeLineToFile(dataToWrite);
        } catch (IOException e) {
            System.out.println("Unable to save data");
        }

    }

    public void importTaskList() {

        try {
            ArrayList<String> data = FileIO.readFileByLine();

            for (String s : data) {

                // replace the special character with space
                String temp = s.replace("~", " ");
                String[] arguments = temp.split(",");


                boolean isCompleted = Boolean.parseBoolean(arguments[1]);

                switch (arguments[0]) {
                case "T":
                    tasklist.add(new ToDo(arguments[2], isCompleted));
                    break;
                case "E":
                    tasklist.add(new Event(arguments[2], arguments[3], arguments[4], isCompleted));
                    break;
                case "D":
                    tasklist.add(new Deadline(arguments[2], arguments[3], isCompleted));
                    break;
                default:
                    break;
                }
            }

            System.out.println("Saved entries imported!");
        } catch (FileNotFoundException e) {
            System.out.println(errMsg.LOCAL_RECORD_NOT_EXIST);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(errMsg.LOCAL_RECORD_CORRUPTED);
        }

    }
}

