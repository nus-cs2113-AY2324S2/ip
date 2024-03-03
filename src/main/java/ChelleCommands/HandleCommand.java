package ChelleCommands;

import ChelleExceptions.InvalidCommandFormatException;
import java.util.ArrayList;
public class HandleCommand {

    private static final int markCommandLength = 5;
    private static final int unmarkCommandLength = 7;
    private static final int toDoCommandLength = 5;
    private static final int deadlineCommandLength = 9;
    private static final int eventCommandLength = 6;
    private static final int deleteCommandLength = 7;

    public static void handleMarkCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= markCommandLength) {
            throw new InvalidCommandFormatException(CommandType.MARK);
        } else {
            userInput = userInput.substring(markCommandLength);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                tasks.get(taskIndex).markDone();
                System.out.println("Chelle: Nice! I've marked this task as done:\n        " +
                        tasks.get(taskIndex).toString());
            } else {
                System.out.println("Chelle: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chelle: Invalid task index.");
        }
    }

    public static void handleUnmarkCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= unmarkCommandLength) {
            throw new InvalidCommandFormatException(CommandType.UNMARK);
        } else {
            userInput = userInput.substring(unmarkCommandLength);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                tasks.get(taskIndex).markUndone();
                System.out.println("Chelle: OK, I've marked this task as not done yet:\n        " +
                        tasks.get(taskIndex).toString());
            } else {
                System.out.println("Chelle: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chelle: Invalid task index.");
        }
    }

    public static void handleToDoCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= toDoCommandLength) {
            throw new InvalidCommandFormatException(CommandType.TODO);
        } else {
            tasks.add(new ToDo(userInput.substring(toDoCommandLength)));
            Task.addMessage(tasks);
        }
    }

    public static void handleDeadlineCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= deadlineCommandLength || !userInput.contains("/by")) {
            throw new InvalidCommandFormatException(CommandType.DEADLINE);
        } else {
            tasks.add(new Deadline(userInput.substring(deadlineCommandLength)));
            Task.addMessage(tasks);
        }
    }

    public static void handleEventCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= eventCommandLength || !(userInput.indexOf("/from") < userInput.indexOf("/to")) || !(userInput.contains("/from"))){
            throw new InvalidCommandFormatException(CommandType.EVENT);
        } else {
            tasks.add(new Event(userInput.substring(eventCommandLength)));
            Task.addMessage(tasks);
        }
    }

    public static void handleDeleteCommand (String userInput, ArrayList<Task> tasks) throws InvalidCommandFormatException {
        if (userInput.length() <= deleteCommandLength) {
            throw new InvalidCommandFormatException(CommandType.DELETE);
        } else {
            userInput = userInput.substring(deleteCommandLength);
        }

        try {
            int taskIndex = Integer.parseInt(userInput) - 1;
            if (isValidTaskIndex(taskIndex, tasks)) {
                Task.delMessage(tasks, taskIndex);
                tasks.remove(taskIndex);
            } else {
                System.out.println("Chelle: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Chelle: Invalid task index.");
        }
    }

    private static boolean isValidTaskIndex(int index, ArrayList<Task> tasks) {
        return index >= 0 && index < tasks.size();
    }
}
