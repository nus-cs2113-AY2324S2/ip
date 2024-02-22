package BobBot;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import BobBot.exceptions.BobBotExceptions;
import BobBot.exceptions.InvalidDeadlineException;
import BobBot.exceptions.InvalidEventException;
import BobBot.exceptions.InvalidTodoException;
import BobBot.tasks.Deadline;
import BobBot.tasks.Event;
import BobBot.tasks.Task;
import BobBot.tasks.Todo;

public class BobBot {

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int numTasks = 0;

    private enum TaskStatus {
        MARK, UNMARK, DELETE
    }

    private static final String saveFilePath = "src/storage/saveFile.txt";
    private static final File file = new File(saveFilePath);

    private static void performTaskOperation(String line, TaskStatus status) {
        int taskNumber = Integer.parseInt(line.replaceAll("\\D", "").trim()) - 1;

        try {
            Task task = allTasks.get(taskNumber);
            switch (status) {
                case MARK:
                    task.markAsDone();
                    printTaskOperationMessage(task, "Marking this task as done:");
                    break;
                case UNMARK:
                    task.markAsUndone();
                    printTaskOperationMessage(task, "Unmarking this task:");
                    break;
                case DELETE:
                    allTasks.remove(taskNumber);
                    printTaskOperationMessage(task, "Deleting this task:");
                    numTasks -= 1;
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            printErrorMessage(taskNumber);
        }
    }

    private static void printTaskOperationMessage(Task task, String operationMessage) {
        drawLine(true);
        System.out.println("\tGot it! " + operationMessage);
        System.out.println("\t  " + task.toString());
        drawLine(true);
    }

    private static void printErrorMessage(int taskNumber) {
        drawErrorLine();
        System.out.println("\tOperation failed.");
        System.out.println("\tTask index " + (taskNumber + 1) + " does not exist! Try another number instead.");
        drawErrorLine();
    }

    private static void displayList() {
        drawLine(true);
        printTaskList();
        drawLine(true);
    }

    private static void printTaskList() {

        System.out.printf("\tYour task list currently has %d items!\n\n", numTasks);
        int taskNumberToDisplay;

        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            taskNumberToDisplay = taskIndex + 1;
            System.out.printf("\t%d. %s\n", taskNumberToDisplay, allTasks.get(taskIndex).toString());
        }
    }

    public static void addTask(String line, boolean isLoad) {

        Task newTask = null;

        try {
            if (line.startsWith("todo")) {
                newTask = new Todo(line);
            } else if (line.startsWith("deadline")) {
                newTask = new Deadline(line);
            } else if (line.startsWith("event")) {
                newTask = new Event(line);
            } else {
                handleInvalidCommand();
                return;
            }
        } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
            printCustomExceptionMessage(e);
            return;
        }

        allTasks.add(newTask);
        numTasks += 1;

        if (!isLoad) {
            echoCommand(line, newTask);
        }
    }

    private static void printCustomExceptionMessage(BobBotExceptions e) {
        drawErrorLine();
        e.displayExceptionMessage();
        drawErrorLine();
    }

    private static void handleInvalidCommand() {
        drawErrorLine();
        System.out.println(
                "\tI did not understand that. Refer to the help manual for information on \n\tkeying in the right commands!");
        printHelpMessage();
        drawErrorLine();
    }

    public static void echoCommand(String lineString, Task newTask) {
        drawLine(true);
        System.out.println("\tGot it! I've added this task:\n\t  " + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list\n", numTasks);
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
                    displayList();
                } else if (line.startsWith("mark")) {
                    performTaskOperation(line, TaskStatus.MARK);
                } else if (line.startsWith("unmark")) {
                    performTaskOperation(line, TaskStatus.UNMARK);
                } else if (line.startsWith("delete")) {
                    performTaskOperation(line, TaskStatus.DELETE);
                } else {
                    boolean isLoad = false;
                    addTask(line, isLoad);
                }
            } catch (NullPointerException | NumberFormatException e) {
                printStandardExceptionMessage(e);
            }
            saveFile();
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

        System.out.printf("\tYour task list currently has %d items!\n\n", numTasks);
        System.out.println("\tUsage: mark {task number}");
        System.out.println("\tUsage: unmark {task number}");
        System.out.println("\tUsage: delete {task number}");
        System.out.println("\tPlease enter a valid number within the range of your list.");

        drawErrorLine();
    }

    // rewrite the whole file
    private static void saveFile() {
        StringBuilder fileContents = new StringBuilder();
        String lineToAdd = new String();

        for (int taskIndex = 0; taskIndex < numTasks; taskIndex += 1) {
            lineToAdd = createFileLine(taskIndex);
            fileContents.append(lineToAdd);
        }

        try {
            FileWriter fw = new FileWriter(saveFilePath);
            fw.write(fileContents.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file: " + e);
        }
    }

    private static String createFileLine(int taskIndex) {
        int taskNumberToDisplay = taskIndex;
        Task taskToSave = allTasks.get(taskIndex);
        int taskMarkedStatus = (taskToSave.getMarkedStatus()) ? 1 : 0;
        String taskDescription = taskToSave.getDescription();
        String textToAdd = String.format("%d|%d|%s\n", taskNumberToDisplay, taskMarkedStatus, taskDescription);

        return textToAdd;
    }

    private static void loadMarkings(int taskNum, boolean isMarked) {
        if (isMarked) {
            allTasks.get(taskNum).markAsDone();
        }
    }

    private static void loadToList(String nextLine) {
        String[] taskItems = nextLine.split("\\|", -1);
        int taskNum = Integer.parseInt(taskItems[0]);
        boolean isMarked = (Integer.parseInt(taskItems[1]) == 1) ? true : false;
        String taskDetails = taskItems[2].toString();

        boolean isLoad = true;

        addTask(taskDetails, isLoad);
        loadMarkings(taskNum, isMarked);

    }

    // adapted from
    // https://nus-cs2113-ay2324s2.github.io/website/schedule/week6/topics.html#w6-3-java-file-access
    private static void loadFileContents(String filePath) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            loadToList(fileScanner.nextLine());
        }

        displayList();
    }

    private static void loadFileFromStorage() {

        try {
            loadFileContents(saveFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("No save file found. Creating new task list ...");
        }
    }

    public static void main(String[] args) {
        greet();
        loadFileFromStorage();
        runTaskManager();
        bidFarewell();
    }

}
