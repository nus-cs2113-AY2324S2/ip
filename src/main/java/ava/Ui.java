package ava;

import ava.task.Deadline;
import ava.task.Event;
import ava.task.Task;
import ava.task.ToDo;
import ava.Ava;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public void printUnknownCommandExceptionMessage() {
        printLine();
        System.out.println("(⊙_⊙)? I'm sorry!!! But I don't know what that means.");
        printLine();
    }

    public void printDateFormatExceptionMessage() {
        printLine();
        System.out.println("(⊙_⊙)? You need to specify the date after '/'");
        printLine();
    }

    public void printEmptyTaskNameExceptionMessage(String type) {
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



    public void printAfterAddingTask(ArrayList<Task> tasks) {
        String addedTask = tasks.get(tasks.size() - 1).toString();
        printLine();
        System.out.println("Got it! I've added this task:");
        System.out.println(addedTask);
        if (tasks.size() == 1) {
            System.out.println("Now you have " + 1 + " task in the list~~~");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list~~~");
        }
        printLine();
    }

    public void greet() {
        printLine();
        System.out.println(" Hello!!! AvavaAVA!!! Here is Ava!!!");
        System.out.println(" Let's have a relaxing and happy chat together!!!");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public void exit() {
        printLine();
        System.out.println(" Bye!!! Hope to see you again soon!!!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
