package Tasking;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void addTask(Task userTask, boolean isInitMode) throws IOException {
        list.add(userTask);
        if (!isInitMode) {
            // Used for normal task adding, printing not needed when initialising
            System.out.println(" Got it. mark I've added this task:");
            System.out.println(" " + userTask);
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            Davvy.writeData(userTask, true);
        }
    }

    public static void deleteTask(int index) {
        Task task = list.get(index - 1);
        list.remove(index - 1);
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
    }

    public static Task getTask(int taskNumber) {
        return list.get(taskNumber);
    }

    public static int listLength() {
        return list.size();
    }

    public static void printList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(" " + (i+1) + ".");
            System.out.println(list.get(i));
        }
    }
}