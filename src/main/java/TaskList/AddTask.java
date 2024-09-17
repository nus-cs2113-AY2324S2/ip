package TaskList;

import Parser.DukeExceptions;
import Ui.PrintText;

/**
 * Represents a command to add new task based on user input.
 */
public class AddTask {
    /**
     * Check if user input words contains any valid task description.
     *
     * @param userInputWords Words user input as an array.
     * @throws DukeExceptions.NoDescriptionException
     * If user did not put any valid task description.
     */
    public static void checkDescription(String[] userInputWords) throws
            DukeExceptions.NoDescriptionException {
        if (userInputWords.length == 1) {
            DukeExceptions.throwNoDescriptionException();
        }
    }

    /**
     * Returns a new TaskList.Task that is either of a special type (toDo, event, deadline)
     * or not.
     *
     * @param description Text input by the user.
     * @return A task with a special type or a task with description
     * "Not special task" but no special type.
     */
    public static Task addSpecialTask(String description) {
        String[] userInputWords = description.split(" ");
        try {
            checkDescription(userInputWords);
        } catch (DukeExceptions.NoDescriptionException e) {
            String taskType = userInputWords[0];
            PrintText.printWithLinebreak(taskType + " must have a description.");
            return new Task("Not special task");
        }
        char type = Character.toUpperCase(userInputWords[0].charAt(0));
        return new Task(description, type);
    }
}

