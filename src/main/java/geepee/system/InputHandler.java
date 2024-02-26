package geepee.system;

import geepee.exceptions.EmptyDescriptionException;
import geepee.exceptions.InvalidCommandException;
import geepee.exceptions.MissingDeadlineException;
import geepee.exceptions.MissingFromException;
import geepee.exceptions.MissingIndexException;
import geepee.exceptions.MissingToException;
import geepee.task.list.List;
import geepee.task.Todo;
import geepee.task.Deadline;
import geepee.task.Event;

public abstract class InputHandler {

    /** Index of user's command in a String array */
    private static final int COMMAND_INDEX = 0;

    /**
     * Adds a new todo task to a given list.
     * 
     * @param list List where the todo task is to be added.
     * @param line Line of user input.
     */
    private static void handleTodo(List list, String line) {
        try {
            String todoDescription = InputParser.getTodoDescription(line);
            list.addTask(new Todo(todoDescription));
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getEmptyTodoMessage());
        }
    }

    /**
     * Adds a new deadline task to a given list.
     * 
     * @param list List where the deadline task is to be added.
     * @param line Line of user input.
     */
    private static void handleDeadline(List list, String line) {
        try {
            int byIndex = line.indexOf("/by");
            String deadlineDescription = InputParser.getDeadlineDescription(line, byIndex);
            String deadlineBy = InputParser.getDeadlineBy(line, byIndex);
            list.addTask(new Deadline(deadlineDescription, deadlineBy));
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getEmptyDeadlineMessage());
        } catch (MissingDeadlineException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new event task to a given list.
     * 
     * @param list List where the event task is to be added.
     * @param line Line of user input.
     */
    private static void handleEvent(List list, String line) {
        try {
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to", fromIndex + 1);
            String eventDescription = InputParser.getEventDescription(line, fromIndex);
            String eventFrom = InputParser.getEventFrom(line, fromIndex, toIndex);
            String eventTo = InputParser.getEventTo(line, toIndex);
            list.addTask(new Event(eventDescription, eventFrom, eventTo));
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getEmptyEventMessage());
        } catch (MissingFromException | MissingToException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task from a given list.
     * 
     * @param list List where the task is to be deleted.
     * @param line Line of user input.
     */
    private static void handleDelete(List list, String line) {
        try {
            int taskIndex = InputParser.getTaskIndex(line);
            if (taskIndex >= 0 && taskIndex < list.getSize()) {
                list.deleteTask(taskIndex);
            }
        } catch (MissingIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleFind(List list, String line) {
        try {
            String keyword = InputParser.getKeyword(line);
            list.findTasksFromKeyword(keyword);
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getEmptyKeywordMessage());
        }
    }

    /**
     * Changes the completion status of a task in a given list.
     * 
     * @param list List where the task's completion status is to be changed.
     * @param line Line of user input.
     * @param command String to determine the task's new completion status.
     */
    private static void handleTaskStatusChange(List list, String line, String command) {
        try {
            int taskIndex = InputParser.getTaskIndex(line);
            if (taskIndex >= 0 && taskIndex < list.getSize()) {
                list.changeTaskStatus(taskIndex, (command.equals("mark") ? true : false));
            }
        } catch (MissingIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines an action to perform on a list based on given user input.
     * 
     * @param list List to perform the action on.
     * @param line Line of user input.
     * @throws InvalidCommandException If the command is not captured in the if-else blocks.
     */
    public static void handleUserInput(List list, String line) throws InvalidCommandException {
        String command = line.split(" ")[COMMAND_INDEX];
        if (command.equals("") || command.equals("bye")) {
            return;
        } else if (command.equals("list")) {
            list.getAllTasks();
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
        } else if (command.equals("find")) {
            handleFind(list, line);
        } else {
            throw new InvalidCommandException();
        }
    }
}
