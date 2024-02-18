package bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import bean.command.Parser;
import bean.command.exception.NoValueException;
import bean.task.Task;
import bean.task.TaskList;

public class Bean {
    public static final Path FILE_PATH = Path.of("src", "main", "java", "bean", "data", "tasks.txt");
    public static final String MESSAGE_TASK_UNDONE = "    Oops, looks like you're still not done with this:\n   ";
    public static final String MESSAGE_TASK_ADDED = "    Hey, I've added:\n    ";
    public static final String MESSAGE_TASK_DELETED = "    Alright, this task has been removed:\n    ";
    private static final String SEPARATOR = "   -------------------------------------------------";
    public static final String MESSAGE_WELCOME = "    Hello! I'm Bean.\n    What can I do for you?";
    public static final String MESSAGE_LOADING_TASKS = "    Loading tasks...";
    public static final String MESSAGE_SAVING_TASKS = "    Saving tasks...";
    public static final String MESSAGE_LIST_HEADER = "    These are the tasks in your list:";
    public static final String MESSAGE_TASK_DONE = "    Hey, looks like you're done with this task:\n   ";
    public static final String MESSAGE_CURRENT_NUMTASKS = "    You currently have ";
    public static final String EXCEPTION_INVALID_TASK_NUMBER = "    Sorry, that was an invalid task number.";
    public static final String EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS = "    Exception: no value for required fields";
    public static final String EXCEPTION_NO_SUCH_COMMAND = "    Sorry, I don't understand that command";
    public static final String EXCEPTION_IO = "     IO exception occurred: ";
    public static final String MESSAGE_GOODBYE = "    Bean will take a nap now. Bye!";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        System.out.println(SEPARATOR);
    }

    private static void printLoadingTasks() {
        System.out.println(MESSAGE_LOADING_TASKS);
        System.out.println(SEPARATOR);
    }

    private static void printSavingTasks() {
        System.out.println(MESSAGE_SAVING_TASKS);
        System.out.println(SEPARATOR);
    }

    private static void printIOException(Exception e) {
        System.out.println(EXCEPTION_IO + e.getMessage());
        System.out.println(SEPARATOR);
    }

    private static void printTaskList(TaskList tasks) {
        System.out.println(MESSAGE_LIST_HEADER);
        System.out.println(tasks.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskDone(Task task) {
        System.out.print(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskUndone(Task task) {
        System.out.print(MESSAGE_TASK_UNDONE);
        System.out.println(task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printTaskAdded(Task task, int numTasks) {
        System.out.println(MESSAGE_TASK_ADDED + task.toString());
        String singularOrPlural = numTasks == 1 ? " task." : " tasks.";
        System.out.println(MESSAGE_CURRENT_NUMTASKS + numTasks + singularOrPlural);
        System.out.println(SEPARATOR);
    }

    private static void printTaskDeleted(Task task) {
        System.out.println(MESSAGE_TASK_DELETED + task.toString());
        System.out.println(SEPARATOR);
    }

    private static void printInvalidTaskNo() {
        System.out.println(EXCEPTION_INVALID_TASK_NUMBER);
        System.out.println(SEPARATOR);
    }

    private static void printNoValueForFields() {
        System.out.println(EXCEPTION_NO_VALUE_FOR_REQUIRED_FIELDS);
        System.out.println(SEPARATOR);
    }

    private static void printNoSuchCommand() {
        System.out.println(EXCEPTION_NO_SUCH_COMMAND);
        System.out.println(SEPARATOR);
    }

    private static void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(SEPARATOR);
    }

    private static void processAndExecute(String line, TaskList listOfTasks, boolean isForLoading) {
        Parser userLine = new Parser(line);
        switch (userLine.getCommand()) {
        case "list": {
            printTaskList(listOfTasks);
            break;
        }
        case "mark": {
            try {
                int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
                Task markedTask = listOfTasks.markTask(taskIndex, true);
                printTaskDone(markedTask);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printInvalidTaskNo();
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        case "unmark": {
            try {
                int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
                Task unmarkedTask = listOfTasks.markTask(taskIndex, false);
                printTaskUndone(unmarkedTask);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printInvalidTaskNo();
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        case "todo": {
            boolean taskIsDone;
            try {
                taskIsDone = userLine.getValue("isDone").equals("true");
            } catch (NoValueException e) {
                taskIsDone = false;
            }

            try {
                String description = userLine.getArgument();
                Task newTask = listOfTasks.addTask(description);
                listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
                if (!isForLoading) {
                    printTaskAdded(newTask, listOfTasks.getNumTasks());
                }
            } catch (NoValueException e) {
                if (!isForLoading) {
                    printNoValueForFields();
                }
            }
            break;
        }
        case "deadline": {
            boolean taskIsDone;
            try {
                taskIsDone = userLine.getValue("isDone").equals("true");
            } catch (NoValueException e) {
                taskIsDone = false;
            }

            try {
                String description = userLine.getArgument();
                String by = userLine.getValue("by");
                Task newTask = listOfTasks.addTask(description, by);
                listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
                if (!isForLoading) {
                    printTaskAdded(newTask, listOfTasks.getNumTasks());
                }
            } catch (NoValueException e) {
                if (!isForLoading) {
                    printNoValueForFields();
                }
            }
            break;
        }
        case "event": {
            boolean taskIsDone;
            try {
                taskIsDone = userLine.getValue("isDone").equals("true");
            } catch (NoValueException e) {
                taskIsDone = false;
            }

            try {
                String description = userLine.getArgument();
                String start = userLine.getValue("start");
                String end = userLine.getValue("end");
                Task newTask = listOfTasks.addTask(description, start, end);
                listOfTasks.markTask(listOfTasks.getNumTasks() - 1, taskIsDone);
                if (!isForLoading) {
                    printTaskAdded(newTask, listOfTasks.getNumTasks());
                }
            } catch (NoValueException e) {
                if (!isForLoading) {
                    printNoValueForFields();
                }
            }
            break;
        }
        case "delete": {
            try {
                int taskIndex = Integer.parseInt(userLine.getArgument()) - 1;
                Task deletedTask = listOfTasks.removeTask(taskIndex);
                printTaskDeleted(deletedTask);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                printInvalidTaskNo();
            } catch (NoValueException e) {
                printNoValueForFields();
            }
            break;
        }
        default:
            if (!isForLoading) {
                printNoSuchCommand();
            }
            break;
        }
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine.trim();
    }

    private static TaskList loadTaskList() {
        TaskList listOfTasks = new TaskList();
        File taskArchive = FILE_PATH.toFile();
        printLoadingTasks();

        try {
            if (taskArchive.createNewFile()) {
                return listOfTasks;
            }
        } catch (IOException e) {
            printIOException(e);
        }

        try {
            Scanner taskArchiveScanner = new Scanner(taskArchive);
            while (taskArchiveScanner.hasNext()) {
                String line = taskArchiveScanner.nextLine();
                if (!line.trim().isEmpty()) {
                    processAndExecute(line, listOfTasks, true);
                }
            }
        } catch (FileNotFoundException e) {
            printIOException(e);
        }

        return listOfTasks;
    }

    private static void saveTaskList(TaskList listOfTasks) {
        printSavingTasks();
        try {
            FileWriter taskListArchiver = new FileWriter(FILE_PATH.toFile());
            taskListArchiver.write(listOfTasks.toCommand());
            taskListArchiver.close();
        } catch (IOException e) {
            printIOException(e);
        }
    }

    public static void main(String[] args) {
        TaskList listOfTasks = loadTaskList();
        printWelcomeMessage();

        String line = getUserInput();
        while (!line.equals("bye")) {
            processAndExecute(line, listOfTasks, false);
            line = getUserInput();
        }

        saveTaskList(listOfTasks);
        printGoodbyeMessage();
    }
}
