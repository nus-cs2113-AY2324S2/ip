package vibes.parser;

import vibes.exception.InvalidArgumentException;

public class Parser {
    public static String parseTodo(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2) {
            return parts[1];
        } else {
            throw new InvalidArgumentException("\t Argument not found! The todo task cannot be empty.");
        }
    }

    public static String[] parseDeadline(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2) {
            String[] taskParts = parts[1].split(" /by ", 2);
            if(taskParts.length == 2) {
                String description = taskParts[0];
                String by = taskParts[1];
                return new String[]{description, by};
            } else {
                throw new InvalidArgumentException("\t Invalid input format. '/by' separator not found.");
            }
        } else {
            throw new InvalidArgumentException(("\t Argument not found! The deadline task cannot be empty"));
        }
    }

    public static String[] parseEvent(String userInput) throws InvalidArgumentException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2) {
            String[] taskParts = parts[1].split(" /from | /to ", 3);
            if (taskParts.length == 3) {
                String description = taskParts[0];
                String from = taskParts[1];
                String to = taskParts[2];
                return new String[]{description, from, to};
            } else {
                throw new InvalidArgumentException("\t Invalid input format. '/from' or '/to' separator not found.");
            }
        } else {
            throw new InvalidArgumentException("\t Argument not found! The event task cannot be empty");
        }
    }

    public static int parseTaskNumber(String userInput) throws InvalidArgumentException{
        String[] parts = userInput.split(" ", 2);
        if (parts.length == 2){
            return Integer.parseInt(parts[1]) - 1;
        } else {
            throw new InvalidArgumentException("\t Argument not found! Task number is required");
        }
    }

    public static String parseCommand(String userInput) {
        return userInput.split(" ")[0].toLowerCase();
    }
}
