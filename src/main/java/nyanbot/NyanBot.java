package nyanbot;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.List;
import nyanbot.task.*;
import nyanbot.tool.*;

public class NyanBot {
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    private static final String LIST_COMMAND = "LIST";
    private static final String MARK_COMMAND = "MARK";
    private static final String UNMARK_COMMAND = "UNMARK";
    private static final String TODO_COMMAND = "TODO";
    private static final String DEADLINE_COMMAND = "DEADLINE";
    private static final String EVENT_COMMAND = "EVENT";
    private static final String BYE_COMMAND = "BYE";
    private static final String HELP_COMMAND = "HELP";
    private static final String LINE = "____________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;


    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        Printer.printAddTaskSuccess(task);
    }

    private static void markTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks[index].markAsDone();
            Printer.printMarkSuccess();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Printer.printNullError();
        } catch (NumberFormatException e) {
            Printer.printMarkUsage();
        }
    }

    private static void unmarkTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks[index].markAsUndone();
            Printer.printUnmarkSuccess();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            Printer.printNullError();
        } catch (NumberFormatException e) {
            Printer.printUnmarkUsage();
        }
    }

    private static void addTodo(String input) {
        try {
            String description = input.substring(6);
            Todo todo = new Todo(description);
            addTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printTodoUsage();
        }
    }

    private static void addDeadline(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(10);
            String date = splitInputs[1];
            Deadline deadline = new Deadline(description, date);
            addTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printDeadlineUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printMissingDate();
            Printer.printDeadlineUsage();
        }
    }

    private static void addEvent(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(7);
            String start = splitInputs[1];
            String end = splitInputs[2];
            Event event = new Event(description, start, end);
            addTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            Printer.printMissingDescription();
            Printer.printEventUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printMissingStartEnd();
            Printer.printEventUsage();
        }
    }

    private static String parseCommand(String input) {
        String[] splitInputs = input.split(" ");
        return splitInputs[0].toUpperCase();
    }

    private static void startNyan() {
        readFile();
        Printer.printGreet();
    }
    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String[] splitInputs = new String[2];

        while (true) {
            String input = scanner.nextLine();
            splitInputs = input.split(" ");

            switch (parseCommand(input)) {
                case BYE_COMMAND:
                    scanner.close();
                    return;
                case LIST_COMMAND:
                    System.out.println(LINE);
                    Printer.printTasks(tasks, taskCount);
                    break;
                case MARK_COMMAND:
                    int markIndex;
                    markTask(input);
                    writeFile();
                    break;
                case UNMARK_COMMAND:
                    unmarkTask(input);
                    writeFile();
                    break;
                case TODO_COMMAND:
                    addTodo(input);
                    writeFile();
                    break;
                case DEADLINE_COMMAND:
                    addDeadline(input);
                    writeFile();
                    break;
                case EVENT_COMMAND:
                    addEvent(input);
                    writeFile();
                    break;
                case HELP_COMMAND:
                    Printer.printSike();
                    break;
                default:
                    Printer.printInvalidInput();
                    break;
            }
        }
    }

    private static void readFile() {
        try {
            List<String> lines = FileHandler.readFile("data/nyan.txt");
            for (String line : lines) {
                readLine(line);
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    private static void readLine(String line) {
        tasks[taskCount] = readCommand(line);
        if (readStatus(line)) {
            tasks[taskCount].markAsDone();
        }
        taskCount++;
    }

    private static Task readCommand(String line) {
        String[] tokens = line.split("/");
        String command = tokens[0].toUpperCase();
        String status = tokens[1].toUpperCase();
        Task task = new Task("");
        switch (command) {
            case TODO_COMMAND:
                task = new Todo(tokens[2]);
                break;
            case DEADLINE_COMMAND:
                task = new Deadline(tokens[2], tokens[3]);
                break;
            case EVENT_COMMAND:
                task = new Event(tokens[2], tokens[3], tokens[4]);
                break;
            default:
                return null; //TODO: throw exception
        }
        return task;
    }

    private static boolean readStatus(String line) {
        String[] tokens = line.split("/");
        String status = tokens[1].toUpperCase();
        if (status.equals(TRUE)) {
            return true;
        }
        else if (status.equals(FALSE)) {
            return false;
        }
        return false; //TODO: throw exception
    }

    private static void writeFile() {
        try {
            ArrayList<String> lines = new ArrayList<String>();
            for (int i = 0; i < taskCount; i++) {
                lines.add(tasks[i].toString());
            }
            FileHandler.writeFile("data/nyan.txt", "temp/temp.txt", lines);
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public static void endNyan() {
        writeFile();
        Printer.printBye();
    }

     public static void main(String[] args) {
        startNyan();
        getInput();
        endNyan();
    }
}
