import java.util.Scanner;

public class Task {
    private static int taskNumber;
    private static final String[] tasks = new String[100];
    private String taskName;
    private static boolean[] doneList = new boolean[100];

    public Task(String taskName) {
        setTaskName(taskName);
        tasks[taskNumber] = this.taskName;
        doneList[taskNumber] = false;
        taskNumber++;
    }

    public static int getTaskNumber() {
        return taskNumber;
    }

    public static String[] getTasks() {
        return tasks;
    }

    public static boolean[] getDoneList() {
        return doneList;
    }

    public static void setDoneList(boolean[] doneList) {
        Task.doneList = doneList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static void printTask(){
        System.out.println("    Here are the tasks in your list:");
        int i = 0;
        while (i < taskNumber) {
            if (doneList[i]){
                System.out.println("    " + "[🆗]" + (i + 1) + ". " + tasks[i]);
            } else {
                System.out.println("    " + "[  ]" + (i + 1) + ". " + tasks[i]);
            }
            i++;
        }
    }

    public static void mark(String sentence){
        String[] markInstructions =  sentence.split(" ");
        int toBeMarked = Integer.parseInt(markInstructions[1]) - 1;
        if (toBeMarked >= taskNumber){
            System.out.println("You have not created Task " + (toBeMarked + 1) + " yet. Jiayous. I will always support you. ฅ •ﻌ•♡");
        }
        else if (markInstructions[0].equals("mark")){
            doneList[toBeMarked] = true;
            System.out.println("    Good job! I've marked this task as done:");
            System.out.println("      [🆗]" + tasks[toBeMarked]);
        }
        else if (markInstructions[0].equals("unmark")){
            doneList[toBeMarked] = false;
            System.out.println("    Sure~ I've marked this task as not done yet:");
            System.out.println("      [  ]" + tasks[toBeMarked]);
        }
    }
}
