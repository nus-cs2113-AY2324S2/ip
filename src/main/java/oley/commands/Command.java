package oley.commands;

import oley.tasks.Task;
import oley.tasks.Todo;
import oley.tasks.Deadline;
import oley.tasks.Event;
import oley.tasks.TaskList;
import oley.tasks.TimingNotFoundException;

import java.io.IOException;

public class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

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

    public static void deleteTask(TaskList tasks, String sentence) {
        int toBeDeleted = Parser.parseDeleteOrMark(sentence);
        String taskToBeDeleted = tasks.get(toBeDeleted).toString();
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

    public static void printTask(TaskList tasks) {
        Ui.printTasks();
        int i = 0;
        while (i < tasks.size()) {
            Ui.printTask((i + 1), tasks.get(i).toString());
            i++;
        }
    }

    public static void mark(TaskList tasks, String sentence, boolean isBegin) {
        int toBeMarked = Parser.parseDeleteOrMark(sentence);
        String instruction = Parser.parse(sentence);

        if (toBeMarked >= tasks.size()) {
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
            mark(tasks, message, false);
            Ui.lineBreaker();
            break;
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
