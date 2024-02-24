package Tasking;

import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void addTask(Task userTask) {
        list.add(userTask);
        Davvy.printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + userTask);
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        Davvy.printLine();
    }

    public static Task getTask(int taskNumber) {
        return list.get(taskNumber);
    }

    public static int listLength() {
        return list.size();
    }

    public static void printList() {
        Davvy.printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(" " + (i+1) + ".");
            System.out.println(list.get(i));
        }
        Davvy.printLine();
    }
}