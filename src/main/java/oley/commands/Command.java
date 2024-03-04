package oley.commands;

import oley.tasks.Task;
import oley.tasks.Todo;
import oley.tasks.Deadline;
import oley.tasks.Event;
import oley.tasks.TaskList;
import oley.tasks.TimingNotFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents all acceptable valid commands and the way of processing them.
 */
public class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns whether the iteration of reading commands from users should stop.
     *
     * @return Whether continue reading in commands or not.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Add a new task to the task list.
     * If it is initialising, the message of tasks being successfully added will not be printed and tasks will not
     * be added to the data file.
     *
     * @param tasks Task list that contains existing tasks.
     * @param sentence Sentence entered by the user as a command.
     * @param isBegin Boolean attribute that determines whether the program is initialising automatically from
     *                the existing data file or following newly entered commands by users.
     * @throws InputNotRecognizedException If the first word entered is not valid.
     */
    public static void addTask(TaskList tasks, String sentence, boolean isBegin) throws InputNotRecognizedException {
        String instruction = Parser.parse(sentence);
        switch (instruction) {
        case "todo":
            tasks.add(new Todo(sentence.substring(5)));
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(sentence.substring(9)));
            } catch (TimingNotFoundException e) {
                Ui.printError();
                Ui.printDeadlineNotSpecified();
                return;
            }
            break;
        case "event":
            try {
                tasks.add(new Event(sentence.substring(6)));
            } catch (TimingNotFoundException e) {
                Ui.printError();
                Ui.printEventNotSpecified();
                return;
            }
            break;
        default:
            throw new InputNotRecognizedException();
        }

        if (!isBegin) {
            String file = "./data/Oley.txt";
            Task taskName = tasks.get(tasks.size() - 1);
            try {
                Storage.appendToFile(file, taskName.format());
            } catch (IOException e) {
                Ui.printError();
                Ui.printFailToWrite();
            }
            Ui.printTaskAdded(taskName);
            Ui.printTaskNumber(tasks.size());
        }
    }

    /**
     * Delete a task from the current task list.
     * Deleting from data file is achieved by clearing the file and re-write the current task list to the file.
     *
     * @param tasks Task list that contains existing tasks.
     * @param sentence Sentence entered by the user as a command.
     */
    public static void deleteTask(TaskList tasks, String sentence) {
        int toBeDeleted = -1;
        String taskToBeDeleted;
        try {
            toBeDeleted = Parser.parseDeleteOrMark(sentence);
            taskToBeDeleted = tasks.get(toBeDeleted).toString();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Ui.printError();
            Ui.printMissingNumber();
            return;
        } catch (IndexOutOfBoundsException e) {
            Ui.printError();
            Ui.printMarkExceedRange(toBeDeleted + 1);
            return;
        }
        
        tasks.remove(toBeDeleted);
        Ui.printTaskDeleted(taskToBeDeleted);
        Ui.printTaskNumber(tasks.size());
        String file = "./data/Oley.txt";
        if (!tasks.isEmpty()) {
            try {
                Storage.changeFile(file, tasks);
            } catch (IOException e) {
                Ui.printError();
                Ui.printFailToWrite();
            }
        } else {
            try {
                Storage.clearFile(file);
            } catch (IOException e) {
                Ui.printError();
                Ui.printFailToClear();
            }
        }
    }

    /**
     * Print all the tasks in the current list in the format of "No. [TYPE][ISDONE] TaskName ([TIMING])",
     * e.g. 1.[T][] Sleep
     *
     * @param tasks Task list that contains existing tasks.
     */
    public static void printTask(TaskList tasks) {
        Ui.printTasks();
        int i = 0;
        while (i < tasks.size()) {
            Ui.printTask((i + 1), tasks.get(i).toString());
            i++;
        }
    }

    /**
     * Mark a task in the current task list as done or unmark it.
     * If the index of task to be marked or unmarked is larger than the total number of tasks, a warning will be
     * printed.
     * If the task to be marked is already marked as done, or the task to be unmarked is not yet to be marked,
     * a warning will be printed.
     *
     * @param tasks Task list that contains existing tasks.
     * @param sentence Sentence entered by the user as a command.
     * @param isBegin Boolean attribute that determines whether the program is initialising automatically from
     *                the existing data file or following newly entered commands by users.
     */
    public static void mark(TaskList tasks, String sentence, boolean isBegin) {
        int toBeMarked;
        try {
            toBeMarked = Parser.parseDeleteOrMark(sentence);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Ui.printError();
            Ui.printMissingNumber();
            return;
        }
        String instruction = Parser.parse(sentence);

        if (toBeMarked >= tasks.size() || toBeMarked <= -1) {
            Ui.printError();
            Ui.printMarkExceedRange(toBeMarked + 1);
            return;
        }

        if (instruction.equals("mark")) {
            if (tasks.get(toBeMarked).checkDone()) {
                Ui.printMarkedAlready();
                return;
            }
            tasks.get(toBeMarked).setDone();
            if (!isBegin) {
                Ui.printMarked(tasks.get(toBeMarked));
            }
        } else if (instruction.equals("unmark")) {
            if (!tasks.get(toBeMarked).checkDone()) {
                Ui.printUnmarkFailed();
                return;
            }
            tasks.get(toBeMarked).setNotDone();
            if (!isBegin) {
                Ui.printUnmarked(tasks.get(toBeMarked));
            }
        }

        if (!isBegin) {
            String file = "./data/Oley.txt";
            try {
                Storage.changeFile(file, tasks);
            } catch (IOException e) {
                Ui.printError();
                Ui.printFailToWrite();
            }
        }
    }

    /**
     * Print tasks that end before the given time.
     * "todo" are not involved as it does not involve time.
     * For "deadline", the ones with due time (by) before given time will be included.
     * For "event", the ones with end time (to) before given time will be included.
     *
     * @param tasks Task list that contains existing tasks.
     * @param sentence Sentence entered by the user as a command.
     */
    public void tasksBeforeTime(TaskList tasks, String sentence) {
        TaskList inDuration = new TaskList();
        LocalDateTime d;
        try {
            d = Parser.parseTiming(sentence);
        } catch (DateTimeParseException e) {
            Ui.printError();
            Ui.printCorrectFormat("");
            return;
        }
        for (Task task : tasks) {
            if (task.getClass() == Event.class && task.getTime().isBefore(d)) {
                inDuration.add(task);
            }
            if (task.getClass() == Deadline.class && task.getTime().isBefore(d)) {
                inDuration.add(task);
            }
        }
        Ui.printTasksWithinTime(inDuration);
    }

    /**
     * Print tasks that include the given single keyword.
     *
     * @param tasks Task list that contains existing tasks.
     * @param sentence Sentence entered by the user as a command.
     */
    public void findTask(TaskList tasks, String sentence) {
        String keyWord = Parser.parseFind(sentence);
        TaskList tasksContainingKeyWord = new TaskList();
        for (Task task: tasks) {
            if (task.getTaskName().contains(keyWord)) {
                tasksContainingKeyWord.add(task);
            }
        }
        Ui.printTasksWithKeyword(tasksContainingKeyWord);
    }

    /**
     * Execute commands based on the first word entered by the user.
     * If the first word is not recognised, there will be InputNotRecognizedException.
     *
     * @param tasks Task list that contains existing tasks.
     */
    public void execute(TaskList tasks) {
        String message = Ui.readCommand();
        String instruction = Parser.parse(message);
        switch (instruction) {
        case "bye":
            Ui.exit();
            isExit = true;
            break;
        case "list":
            printTask(tasks);
            Ui.lineBreaker();
            break;
        case "mark":
        case "unmark":
            mark(tasks, message, false);
            Ui.lineBreaker();
            break;
        case "delete":
            try {
                deleteTask(tasks, message);
            } catch (IndexOutOfBoundsException e) {
                Ui.printError();
                Ui.printFailToDelete();
            }
            Ui.lineBreaker();
            break;
        case "time":
            try {
                tasksBeforeTime(tasks, message);
            } catch (IndexOutOfBoundsException e) {
                Ui.printError();
                Ui.printFailToFindTiming();
            }
            Ui.lineBreaker();
            break;
        case "find":
            try {
                findTask(tasks, message);
            } catch (IndexOutOfBoundsException e) {
                Ui.printError();
                Ui.printFailToFindTasks();
            }
            Ui.lineBreaker();
            break;
        default:
            try {
                addTask(tasks, message, false);
            } catch (IndexOutOfBoundsException e) {
                Ui.printError();
                Ui.printMissingDescription();
            } catch (InputNotRecognizedException e) {
                Ui.printInstructionNotUnderstood();
            }
            Ui.lineBreaker();
        }
    }
}
