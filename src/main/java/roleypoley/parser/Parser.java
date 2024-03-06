package roleypoley.parser;

import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.task.Deadline;
import roleypoley.task.Event;
import roleypoley.task.Task;

public class Parser {

    public static Task parseAddCommand(String userInput) throws RoleyPoleyParseException {
        String[] splitString = userInput.split(" ");
        final String commandWord = splitString[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        return switch (commandWord) {
            case "todo" -> new Task(arguments, false);
            case "deadline" -> new Deadline(arguments, false);
            case "event" -> new Event(arguments, false);
            default -> throw new RoleyPoleyParseException("defaultError");
        };
    }

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

