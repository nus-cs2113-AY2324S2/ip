package BobBot;

import java.util.ArrayList;
import java.util.Scanner;

import BobBot.exceptions.BobBotExceptions;
import BobBot.storage.Storage;
import BobBot.tasks.Task;
import taskList.TaskList;

public class BobBot {

    private enum TaskStatus {
        MARK, UNMARK, DELETE
    }

    private static void performTaskOperation(String line, TaskStatus status) {
        int taskNumber = Integer.parseInt(line.replaceAll("\\D", "").trim()) - 1;
        ArrayList<Task> allTasks = TaskList.getTaskList();
        int numberOfTasks = TaskList.getNumberOfTasks();
        
        try {
            Task task = allTasks.get(taskNumber);
            if (status == TaskStatus.MARK) {
                task.markAsDone();
                printTaskOperationMessage(task, "Marking this task as done:");
            } else if (status == TaskStatus.UNMARK) {
                task.markAsUndone();
                printTaskOperationMessage(task, "Unmarking this task:");
            } else if (status == TaskStatus.DELETE) {
                allTasks.remove(taskNumber);
                printTaskOperationMessage(task, "Deleting this task:");
                numberOfTasks -= 1;
            } else {
                System.out.println("Oh no!");
            }
        } catch (IndexOutOfBoundsException e) {
            printNonExistentTaskErrorMessage(taskNumber);
        }
    }

    private static void printTaskOperationMessage(Task task, String operationMessage) {
        drawLine(true);
        System.out.println("\tGot it! " + operationMessage);
        System.out.println("\t  " + task.toString());
        drawLine(true);
    }

    private static void printNonExistentTaskErrorMessage(int taskNumber) {
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

    private static void printHelpMessage() {
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

    private static void bidFarewell() {
        drawLine(true);
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine(true);
    }

    private static void runTaskManager() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            try {
                if (line.equalsIgnoreCase("help")) {
                    printHelpMessage();
                } else if (line.equalsIgnoreCase("list")) {
                    TaskList.displayList();
                } else if (line.startsWith("mark")) {
                    performTaskOperation(line, TaskStatus.MARK);
                } else if (line.startsWith("unmark")) {
                    performTaskOperation(line, TaskStatus.UNMARK);
                } else if (line.startsWith("delete")) {
                    performTaskOperation(line, TaskStatus.DELETE);
                } else {
                    boolean isLoad = false;
                    TaskList.addTask(line, isLoad);
                }
            } catch (NullPointerException | NumberFormatException e) {
                printStandardExceptionMessage(e);
            }
            storage.saveFile();
            line = in.nextLine();
        }
    }

    private static void printStandardExceptionMessage(Exception e) {
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

    private static Storage storage;
    private static TaskList tasks;
    
    // adapted from https://nus-cs2113-ay2324s2.github.io/website/schedule/week7/project.html
    public BobBot() {
        storage = new Storage();
        tasks = new TaskList();
    }

    public void run() {
        greet();
        runTaskManager();
        bidFarewell();
    }

    public static void main(String[] args) {
        new BobBot().run();
    }

}
