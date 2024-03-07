package BobBot.parser;

import java.util.Scanner;

import BobBot.exceptions.InvalidDeadlineException;
import BobBot.exceptions.InvalidEventException;
import BobBot.exceptions.InvalidTodoException;
import BobBot.storage.Storage;
import BobBot.tasks.Deadline;
import BobBot.tasks.Event;
import BobBot.tasks.Task;
import BobBot.tasks.Todo;
import BobBot.ui.Ui;
import taskList.TaskList;

/**
 * Implements a parser that interprets user input and performs the necessary 
 * operations.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class Parser {

    /**
     * Parses the general commands for handling tasks and performs the 
     * necessary operations.
     * 
     * <p>Operations include adding tasks, marking tasks as done, deleting tasks, 
     * and listing tasks.</p>
     */
    public static void runTaskManager() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {

            try {
                if (line.equalsIgnoreCase("help")) {
                    Ui.printHelpMessage();
                } else if (line.equalsIgnoreCase("list")) {
                    TaskList.displayList();
                } else if (line.startsWith("mark")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.MARK);
                } else if (line.startsWith("unmark")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.UNMARK);
                } else if (line.startsWith("delete")) {
                    TaskList.performTaskOperation(line, TaskList.TaskStatus.DELETE);
                } else {
                    boolean isLoad = false;
                    TaskList.addTask(line, isLoad);
                }
            } catch (NullPointerException | NumberFormatException e) {
                Ui.printStandardExceptionMessage(e);
            }
            Storage.saveFile();
            line = in.nextLine();
        }
    }

    /**
     * Parses the task commands and creates the respective task objects.
     * 
     * @param line the command to parse
     * @param taskToParse the task to parse
     * @return the task object created
     */
    public static Task parseTaskCommands(String line, Task taskToParse) {
        try {
            if (line.startsWith("todo")) {
                taskToParse = new Todo(line);
            } else if (line.startsWith("deadline")) {
                taskToParse = new Deadline(line);
            } else if (line.startsWith("event")) {
                taskToParse = new Event(line);
            } else {
                Ui.handleInvalidCommand();
                return null;
            }
        } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
            Ui.printCustomExceptionMessage(e);
            return null;
        }

        return taskToParse;
    }
}
