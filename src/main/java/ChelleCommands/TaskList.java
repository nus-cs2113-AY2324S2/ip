package ChelleCommands;

import ChelleExceptions.InvalidCommandFormatException;
import Common.Messages;
import java.util.ArrayList;

public class TaskList {
    private static final int MARK_COMMAND_LENGTH = 5;
    private static final int UNMARK_COMMAND_LENGTH = 7;
    private static final int TODO_COMMAND_LENGTH = 5;
    private static final int DEADLINE_COMMAND_LENGTH = 9;
    private static final int EVENT_COMMAND_LENGTH = 6;
    private static final int DELETE_COMMAND_LENGTH = 7;

    public static void TaskList(){
    }

    public static void handleCommand(String userInput, ArrayList<Task> tasks, CommandType command) throws InvalidCommandFormatException {
        switch (command) {
        case LIST:
            displayTasks(tasks);
            break;
        case MARK:
            handleMarkCommand(userInput, tasks);
            break;
        case UNMARK:
            handleUnmarkCommand(userInput, tasks);
            break;
        case TODO:
            handleToDoCommand(userInput, tasks);
            break;
        case DEADLINE:
            handleDeadlineCommand(userInput, tasks);
            break;
        case EVENT:
            handleEventCommand(userInput, tasks);
            break;
        case DELETE:
            handleDeleteCommand(userInput, tasks);
            break;
        default:
            System.out.println(Messages.MESSAGE_COMMAND_ERROR);
            break;
        }
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        System.out.println(Messages.MESSAGE_SYSTEM_PREFIX);
        if (tasks.isEmpty()) {
            System.out.println(Messages.MESSAGE_NO_TASKS);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void handleMarkCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= MARK_COMMAND_LENGTH) {
            throw new InvalidCommandFormatException(CommandType.MARK);
        } else {
            userInput = userInput.substring(MARK_COMMAND_LENGTH);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                tasks.get(taskIndex).markDone();
                System.out.println(Messages.MESSAGE_MARKED + tasks.get(taskIndex).toString());
            } else {
                System.out.println(Messages.MESSAGE_INVALID_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.MESSAGE_INVALID_INDEX);
        }
    }

    private static void handleUnmarkCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= UNMARK_COMMAND_LENGTH) {
            throw new InvalidCommandFormatException(CommandType.UNMARK);
        } else {
            userInput = userInput.substring(UNMARK_COMMAND_LENGTH);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                tasks.get(taskIndex).markUndone();
                System.out.println(Messages.MESSAGE_UNMARKED + tasks.get(taskIndex).toString());
            } else {
                System.out.println(Messages.MESSAGE_INVALID_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.MESSAGE_INVALID_INDEX);
        }
    }

    private static void handleToDoCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= TODO_COMMAND_LENGTH) {
            throw new InvalidCommandFormatException(CommandType.TODO);
        } else {
            tasks.add(new ToDo(userInput.substring(TODO_COMMAND_LENGTH)));
            Task.addMessage(tasks);
        }
    }

    private static void handleDeadlineCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= DEADLINE_COMMAND_LENGTH || !userInput.contains("/by")) {
            throw new InvalidCommandFormatException(CommandType.DEADLINE);
        } else {
            tasks.add(new Deadline(userInput.substring(DEADLINE_COMMAND_LENGTH)));
            Task.addMessage(tasks);
        }
    }

    private static void handleEventCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= EVENT_COMMAND_LENGTH || !(userInput.indexOf("/from") < userInput.indexOf("/to")) || !(userInput.contains("/from"))){
            throw new InvalidCommandFormatException(CommandType.EVENT);
        } else {
            tasks.add(new Event(userInput.substring(EVENT_COMMAND_LENGTH)));
            Task.addMessage(tasks);
        }
    }

    private static void handleDeleteCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= DELETE_COMMAND_LENGTH) {
            throw new InvalidCommandFormatException(CommandType.DELETE);
        } else {
            userInput = userInput.substring(DELETE_COMMAND_LENGTH);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                Task.delMessage(tasks, taskIndex);
                tasks.remove(taskIndex);
            } else {
                System.out.println(Messages.MESSAGE_INVALID_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.MESSAGE_INVALID_INDEX);
        }
    }

    private static boolean isValidTaskIndex(int index, ArrayList<Task> tasks) {
        return index >= 0 && index < tasks.size();
    }
}
