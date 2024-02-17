package geepee.system;

import java.util.Scanner;
import geepee.exceptions.*;
import geepee.task.list.*;
import geepee.task.*;

public abstract class InputHandler {

    private static Scanner in = new Scanner(System.in);

    private static void handleTodo(List list, String line) {
        try {
            String todoDescription = InputParser.getTodoDescription(line);
            list.addTask(new Todo(todoDescription));
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyTodoDescriptionMessage();
        }
    }

    private static void handleDeadline(List list, String line) {
        try {
            int byIndex = line.indexOf("/by");
            String deadlineDescription = InputParser.getDeadlineDescription(line, byIndex);
            String deadlineBy = InputParser.getDeadlineBy(line, byIndex);
            list.addTask(new Deadline(deadlineDescription, deadlineBy));
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyDeadlineDescriptionMessage();
        } catch (MissingDeadlineException e) {
            SystemMessage.printMissingDeadlineMessage();
        }
    }

    private static void handleEvent(List list, String line) {
        try {
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to", fromIndex + 1);
            String eventDescription = InputParser.getEventDescription(line, fromIndex);
            String eventFrom = InputParser.getEventFrom(line, fromIndex, toIndex);
            String eventTo = InputParser.getEventTo(line, toIndex);
            list.addTask(new Event(eventDescription, eventFrom, eventTo));
        } catch (EmptyDescriptionException e) {
            SystemMessage.printEmptyEventDescriptionMessage();
        } catch (MissingFromException e) {
            SystemMessage.printMissingFromMessage();
        } catch (MissingToException e) {
            SystemMessage.printMissingToMessage();
        }
    }

    private static void handleDelete(List list, String line) {
        int taskIndex = InputParser.getTaskIndex(line);
        if (taskIndex >= 0 && taskIndex < list.getSize()) {
            list.deleteTask(taskIndex);
        }
    }

    private static void handleTaskStatusChange(List list, String line, String command) {
        int taskIndex = InputParser.getTaskIndex(line);
        if (taskIndex >= 0 && taskIndex < list.getSize()) {
            list.changeTaskStatus(taskIndex, (command.equals("mark") ? true : false));
        }
    }

    public static void handleUserInput(List list, String line) throws InvalidCommandException {
        String command = line.split(" ")[0];
        if (command.equals("") || command.equals("bye")) {
            return;
        } else if (command.equals("list")) {
            ListMessage.printAllTasks(list);
        } else if (command.equals("mark") || command.equals("unmark")) {
            handleTaskStatusChange(list, line, command);
        } else if (command.equals("todo")) {
            handleTodo(list, line);
        } else if (command.equals("deadline")) {
            handleDeadline(list, line);
        } else if (command.equals("event")) {
            handleEvent(list, line);
        } else if (command.equals("delete")) {
            handleDelete(list, line);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static String receiveUserInput() {
        return in.nextLine().trim();
    }
}
