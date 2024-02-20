package com.arriky.task;

import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasklist = new ArrayList<Task>();

    public void listTasks() {
        int i = 1;
        for (Task task:tasklist) {
            System.out.println(" " + i + "." + task.getSummary());
            i++;
        }
    }

    public void addToDo(String taskName) {
        tasklist.add(new ToDo(taskName));
        printInsertionAcknowledgement();
    }

    public void addEvent(String taskName, String startTime, String endTime) {
        tasklist.add(new Event(taskName, startTime, endTime));
        printInsertionAcknowledgement();
    }

    public void addDeadline(String taskName, String dueTime) {
        tasklist.add(new Deadline(taskName, dueTime));
        printInsertionAcknowledgement();
    }

    private void printInsertionAcknowledgement() {
        int size = tasklist.size();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasklist.get(size - 1).getSummary());
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void markDone(int index) {
        tasklist.get(index).completed = true;
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasklist.get(index).getSummary());
    }

    public void unmarkDone(int index) {
        tasklist.get(index).completed = false;
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
}

