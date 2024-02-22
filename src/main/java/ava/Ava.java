package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Ava {

    public static void main(String[] args) {
        greet();
        mainProcess();
        exit();
    }

    public static void mainProcess() {
        boolean isExit = false;
        ArrayList<Task> tasks = new ArrayList<>();
        DataFile.loadFile(tasks);
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            String task = in.nextLine();
            if (task.equals("bye")) {
                isExit = true;
                continue;
            } else if (task.equals("list")) {
                listTask(tasks);
                continue;
            } else if (task.contains("mark")) {
                markTask(tasks, task);
                DataFile.saveTasks(tasks);
                continue;
            } else if (task.startsWith("todo")) {
                try {
                    addTask(tasks, task, "todo");
                } catch (EmptyDescriptionException e) {
                    dealWithEmptyDescriptionException("todo");
                    continue;
                }
            } else if (task.startsWith("deadline")) {
                try {
                    try {
                        addTask(tasks, task, "deadline");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        dealWithFormatException();
                        continue;
                    }
                } catch (EmptyDescriptionException e) {
                    dealWithEmptyDescriptionException("deadline");
                    continue;
                }
            } else if (task.startsWith("event")) {
                try {
                    try {
                        addTask(tasks, task, "event");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        dealWithFormatException();
                        continue;
                    }
                } catch (EmptyDescriptionException e) {
                    dealWithEmptyDescriptionException("event");
                    continue;
                }
            } else if (task.startsWith("delete")) {
                deleteTask(tasks, task);
                DataFile.saveTasks(tasks);
                continue;
            } else {
                dealWithUnknownException();
                continue;
            }
            printAfterAddingTask(tasks);
            DataFile.saveTasks(tasks);
        }
    }

    private static void dealWithUnknownException() {
        printLine();
        System.out.println("(⊙_⊙)? I'm sorry!!! But I don't know what that means.");
        printLine();
    }

    private static void dealWithFormatException() {
        printLine();
        System.out.println("(⊙_⊙)? You need to specify the date after '/'");
        printLine();
    }

    private static void dealWithEmptyDescriptionException(String type) {
        printLine();
        switch (type) {
        case "todo":
            System.out.println("Please tell me what needs todo (＾＿－)");
            break;
        case "deadline":
            System.out.println("Please tell me the deadline is for? (＾＿－)");
            break;
        case "event":
            System.out.println("Please tell me what is the event? (＾＿－)");
            break;
        }
        printLine();
    }

    public static void addTask(ArrayList<Task> tasks, String task, String type) throws EmptyDescriptionException {
        task = task.replace(type, "");
        if (task.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String[] taskAndDate = task.split("/");
        taskAndDate[0] = taskAndDate[0].trim();
        switch (type) {
        case "todo":
            tasks.add(new ToDo(taskAndDate[0]));
            break;
        case "deadline":
            tasks.add(new Deadline(taskAndDate[0], taskAndDate[1]));
            break;
        case "event":
            tasks.add(new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]));
            break;
        }
    }

    public static void printAfterAddingTask(ArrayList<Task> tasks) {
        String addedTask = tasks.get(tasks.size() - 1).toString();
        printLine();
        System.out.println("Got it! I've added this task:");
        System.out.println(addedTask);
        if (Task.getNumberOfTasks() == 1) {
            System.out.println("Now you have " + 1 + " task in the list~~~");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list~~~");
        }
        printLine();
    }

    public static int extractNumber(String type, String command) {
        command = command.replace(type, "");
        return Integer.parseInt(command) - 1;
    }

    public static void deleteTask(ArrayList<Task> tasks, String command) {
        int taskDeleted;
        try {
            try {
                taskDeleted = extractNumber("delete ", command);
                Task deletedTask = tasks.get(taskDeleted);
                printLine();
                System.out.println("Noted!!! I've removed this task:");
                System.out.println(deletedTask);
                tasks.remove(deletedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list!!!");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                System.out.println(" ⊙﹏⊙ Hey! You cannot delete a task that does not exist!");
                printLine();
            }
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("Please tell me which one to delete? (＾＿－)");
            printLine();
        }
    }

    public static void markTask(ArrayList<Task> tasks, String command) {
        printLine();
        boolean isMark = true;
        int taskChanged;
        if (command.startsWith("unmark")) {
            isMark = false;
        }
        try {
            try {
                if (isMark) {
                    taskChanged = extractNumber("mark ", command);
                    tasks.get(taskChanged).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskChanged = extractNumber("unmark ", command);
                    tasks.get(taskChanged).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(" ⊙﹏⊙ Hey! You cannot mark a task that does not exist!");
                printLine();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please tell me which one to mark? (＾＿－)");
            printLine();
            return;
        }
        System.out.println(tasks.get(taskChanged));
        printLine();
    }

    public static void listTask(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int noOfTask = 0;
        while (noOfTask < tasks.size()) {
            System.out.println((noOfTask + 1) + "." + tasks.get(noOfTask));
            noOfTask += 1;
        }
        printLine();
    }


    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        System.out.println(" Let's have a relaxing and happy chat together!!!");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }
}
