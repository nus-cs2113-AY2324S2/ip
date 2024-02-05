package Logic;

import Command.BaseCommand;
import Command.ByeCommand;
import Command.DeadlineCommand;
import Command.DeleteCommand;
import Command.EventCommand;
import Command.FindCommand;
import Command.FinddateCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.PostponeCommand;
import Command.ToDoCommand;
import Command.UnmarkCommand;

public class Parser {
    public static BaseCommand parse(String args) throws Exception {
        String firstWord = args.split(" ")[0].toLowerCase();
        String taskString = removeFirstWord(args);
        BaseCommand c = createCommand(firstWord, taskString);
        if (c == null) {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

    private static BaseCommand createCommand(String commandType, String taskString) throws Exception {
        switch (commandType) {
            case "list":
                return new ListCommand(taskString);
            case "bye":
                return new ByeCommand();
            case "deadline":
                checkTaskString(taskString);
                return new DeadlineCommand(taskString);
            case "delete":
                checkTaskString(taskString);
                return new DeleteCommand(taskString);
            case "event":
                checkTaskString(taskString);
                return new EventCommand(taskString);
            case "find":
                checkTaskString(taskString);
                return new FindCommand(taskString);
            case "find-date":
                checkTaskString(taskString);
                return new FinddateCommand(taskString);
            case "mark":
                checkTaskString(taskString);
                return new MarkCommand(taskString);
            case "unmark":
                checkTaskString(taskString);
                return new UnmarkCommand(taskString);
            case "todo":
                checkTaskString(taskString);
                return new ToDoCommand(taskString);
            case "postpone":
                checkTaskString(taskString);
                return new PostponeCommand(taskString);
            default:
                return null;
        }
    }

    private static String removeFirstWord(String input) {
        if (input != null && !input.isEmpty()) {
            int firstSpaceIndex = input.indexOf(' ');
            if (firstSpaceIndex != -1) {
                return input.substring(firstSpaceIndex + 1).trim();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static void checkTaskString(String args) throws Exception {
        if (args == null || args.isBlank()) {
            throw new Exception("Missing arguement!");
        }
    }
}
