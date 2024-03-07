package BobBot.ui;

import java.util.ArrayList;

import BobBot.exceptions.BobBotExceptions;
import BobBot.tasks.Task;
import taskList.TaskList;

public class Ui {
    public static void printTaskOperationMessage(Task task, String operationMessage) {
        drawLine(true);
        System.out.println("\tGot it! " + operationMessage);
        System.out.println("\t  " + task.toString());
        drawLine(true);
    }

    public static void printNonExistentTaskErrorMessage(int taskNumber) {
        drawErrorLine();
        System.out.println("\tOperation failed.");
        System.out.println("\tTask index " + (taskNumber + 1) + " does not exist! Try another number instead.");
        drawErrorLine();
    }

    public static void printCustomExceptionMessage(BobBotExceptions e) {
        drawErrorLine();
        e.displayExceptionMessage();
        drawErrorLine();
    }

    public static void handleInvalidCommand() {
        drawErrorLine();
        System.out.println(
                "\tI did not understand that. Refer to the help manual for information on \n\tkeying in the right commands!");
        printHelpMessage();
        drawErrorLine();
    }

    public static void echoCommand(String lineString, Task newTask) {
        drawLine(true);
        System.out.println("\tGot it! I've added this task:\n\t  " + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list\n", TaskList.getNumberOfTasks());
        drawLine(true);
        System.out.println();
    }

    public static void drawErrorLine() {
        System.out.println("\t********************************ERROR*****************************************");
    }

    public static void drawLine(Boolean isIncludeIndentation) {
        if (isIncludeIndentation) {
            System.out.print("\t");
        } else {
            System.out.print("________");
        }
        System.out.println("______________________________________________________________________________");
    }

    public static void greet() {
        drawLine(false);
        System.out.println("Hello! I'm BobBot, your TODO list creator");
        System.out.println("Simply type in any task and I will store them for you!");
        drawLine(false);
    }

    public static void printHelpMessage() {
        drawLine(true);
        System.out.println("\tI see you require some help. Fear not, I shall come to your assistance.\n");
        System.out.println("\tHere are the options available to you:");
        System.out.println("\t\thelp - Display this help menu");
        System.out.println("\t\ttodo ... - State something you want to add to the TODO list");
        System.out.println("\t\tdeadline ... - Tell me about an important deadline you need to meet");
        System.out.println("\t\tevent ... - Let me know what event you have coming up");
        System.out.println("\t\tbye - We bid our farewells (sob)");
        drawLine(true);
    }

    public static void bidFarewell() {
        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }

    public static void printStandardExceptionMessage(Exception e) {
        drawErrorLine();

        if (e instanceof NullPointerException) {
            System.out.println("\tIndex is out of range.");
        } else if (e instanceof NumberFormatException) {
            System.out.println("\tMissing task number!");
        } else {
            System.out.println("There was an error: " + e);
        }

        System.out.printf("\tYour task list currently has %d items!\n\n", TaskList.getNumberOfTasks());
        System.out.println("\tUsage: mark {task number}");
        System.out.println("\tUsage: unmark {task number}");
        System.out.println("\tUsage: delete {task number}");
        System.out.println("\tPlease enter a valid number within the range of your list.");

        drawErrorLine();
    }

    public static void printTaskList() {
        
        int numberOfTasks = TaskList.getNumberOfTasks();
        System.out.printf("\tYour task list currently has %d items!\n\n", numberOfTasks);
        int taskNumberToDisplay;
        
        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            taskNumberToDisplay = taskIndex + 1;
            Task taskToDisplay = TaskList.getTaskList().get(taskIndex);
            System.out.printf("\t%d. %s\n", taskNumberToDisplay, taskToDisplay.toString());
        }
    }
    
    public static void displayList() {
        drawLine(true);
        printTaskList();
        drawLine(true);
    }
    
    public static void printFilteredTaskList(ArrayList<Task> tasksFound) {
        
        System.out.println("\tHere are the matching tasks in your list:");
        int taskNumberToDisplay;

        for (int taskIndex = 0; taskIndex < tasksFound.size(); taskIndex += 1) {
            taskNumberToDisplay = taskIndex + 1;
            Task taskToDisplay = tasksFound.get(taskIndex);
            System.out.printf("\t%d. %s\n", taskNumberToDisplay, taskToDisplay.toString());
        }
    }

    public static void displayTasksFound(ArrayList<Task> tasksFound) {
        drawLine(true);
        printFilteredTaskList(tasksFound);
        drawLine(true);
    }
}
