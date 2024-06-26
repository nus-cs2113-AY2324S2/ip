package roleypoley.parser;

import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.task.Deadline;
import roleypoley.task.Event;
import roleypoley.task.Task;

/**
 * Parses user input
 */
public class Parser {

    /**
     * Parses user inputs for addition of tasks
     * Execute addition of task based on task type
     *
     * @param userInput full user input string
     * @return creates task based on task type
     * @throws RoleyPoleyParseException
     */
    public static Task parseAddCommand(String userInput) throws RoleyPoleyParseException {
        String[] splitString = userInput.split(" ");
        final String commandWord = splitString[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();
        if (splitString.length == 1) {
            switch (commandWord) {
            case "todo":
                throw new RoleyPoleyParseException("toDoError");
            case "deadline":
                throw new RoleyPoleyParseException("deadlineError");
            case "event":
                throw new RoleyPoleyParseException("eventError");
            }
        }
        switch (commandWord) {
        case "todo":
            return new Task(arguments, false);
        case "deadline":
            return new Deadline(arguments, false);
        case "event":
            return new Event(arguments, false);
        default:
            throw new RoleyPoleyParseException("defaultError");
        }
    }

    /**
     * Parses user inputs for editing of tasks
     * Returns index of task in ArrayList that is to be edited
     *
     * @param size_taskList size of ArrayList
     * @param userInput full user input string
     * @return index of task in ArrayList that is to be edited
     * @throws RoleyPoleyParseException if user inputs empty edit command
     */
    public static int parseEditCommand(int size_taskList, String userInput) throws RoleyPoleyParseException {
        String[] words;
        words = userInput.split(" ");
        if (words.length == 2) {
            int index = Integer.parseInt(words[1]);
            if (size_taskList < index) {
                throw new RoleyPoleyParseException("taskNotFoundError");
            }
            else {
                return index;
            }
        } else
            throw new RoleyPoleyParseException("taskNotFoundError");
    }
}

