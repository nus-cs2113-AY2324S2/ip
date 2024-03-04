package Ui;

import TaskList.TaskList;
import tasks.Task;


public class Ui {

    public Ui() {

    }

    public static void showLine(){
        System.out.println("\t____________________________________________________________");
    }

    public static void sayHi(){
        System.out.println("\t Hello! I'm PeeKay");
        System.out.println("\t What can I do for you?");
    }

    public static void closeApp(){
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public static void printDeleteMessage(String description){
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + description);
    }

    public static void printRemainingTaskNumber(int taskNumber){
        String task = (taskNumber == 1) ? "task " : "tasks ";
        System.out.println("\t Now you have " + taskNumber + " " + task + "in the list.");
    }
    public static void printTaskAddedMessage(Task task) {
        System.out.println("\t Got it. I've added this task: ");
        printSingleTask(task);
    }
    public static void printSingleTask(Task task) {
        System.out.println(("\t   " + task.toString()));
    }
    public static void printError(String errorMessage){
        System.out.println("\t Error: " + errorMessage);
    }

    public static void printUnknownInputMessage(){
        System.out.println("\t I've not seen this input before. Please tell me something else I can help you with.");
    }

    public static void printTaskList(TaskList tasks){
        System.out.println("\t Here are the tasks in your list:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks.get(x).toString());
        }
    }

    public static void printMarkedAsDoneMessage(Task task){
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t " + task.toString());
    }

    public static void printMarkedAsUndoneMessage(Task task){
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t " + task.toString());
    }

    public static void printLoadingErrorMessage(){
        System.out.println("\t Saved file not found.");
    }

    public static void printChangesNotSavedMessage(){
        System.out.println("\t Could not save changes to file.");
    }

    public static void printMatchingTasks(TaskList tasks){
        System.out.println("\t Here are the matching tasks in your list:");
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println("\t  " + (x + 1) + "." + tasks.get(x).toString());
        }
    }
}
