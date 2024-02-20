package utils;

import exceptions.EmptyIndexException;
import exceptions.EmptyTaskException;
import exceptions.InvalidCommandException;

public class Command{
    public enum Commands {
        list, mark, unmark, add, delete, bye
    }

    public static Commands getCommands(String input, String[] inputs) throws EmptyIndexException, EmptyTaskException, InvalidCommandException {
        if (inputs.length == 1) {
            if ((inputs[0].equalsIgnoreCase("todo")) || (inputs[0].equalsIgnoreCase("deadline")) || (inputs[0].equalsIgnoreCase("event"))) {
            throw new EmptyTaskException();
            } else if ((inputs[0].equalsIgnoreCase("mark")) || (inputs[0].equalsIgnoreCase("unmark"))) {
            throw new EmptyIndexException();
            }
        } else if (inputs.length >= 2) {
            if ((inputs[0].equalsIgnoreCase("todo")) || (inputs[0].equalsIgnoreCase("deadline")) || (inputs[0].equalsIgnoreCase("event"))) {
                return Commands.add;
            }
        }
        if (inputs.length == 2) {
            if (inputs[0].equalsIgnoreCase("mark") && Integer.parseInt(inputs[1]) <= constants.MAX_TASKS && Integer.parseInt(inputs[1]) > 0) {
                return Commands.mark;
            } else if (inputs[0].equalsIgnoreCase("unmark") && Integer.parseInt(inputs[1]) <= constants.MAX_TASKS && Integer.parseInt(inputs[1]) > 0) {
                return Commands.unmark;
            } else if (inputs[0].equalsIgnoreCase("delete") && Integer.parseInt(inputs[1]) <= constants.MAX_TASKS && Integer.parseInt(inputs[1]) > 0) {
                return Commands.delete;
            } else {
                throw new InvalidCommandException();
            }
        }
        if (input.equalsIgnoreCase("bye")) {
            return Commands.bye;
        } else if (input.equalsIgnoreCase("list")) {
            return Commands.list;
        }
        throw new InvalidCommandException();
    }
}

