package bean.command;

import bean.command.exception.NoValueException;

public class Parser {
    private final String[] fieldValuePairs;
    private final String command;

    public String getValue(String field) throws NoValueException {
        for (String item : fieldValuePairs) {
            if (item.startsWith(field) && item.contains(" ")) {
                int indexOfFirstSpace = item.indexOf(" ");
                return item.substring(indexOfFirstSpace + 1).trim();
            }
        }
        throw new NoValueException();
    }

    public String getArgument() throws NoValueException {
        int indexOfFirstSpace = command.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            throw new NoValueException();
        } else {
            return command.substring(indexOfFirstSpace + 1).trim();
        }
    }

    public String getCommand() {
        int indexOfFirstSpace = command.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            return command;
        } else {
            return command.substring(0, indexOfFirstSpace).trim();
        }
    }

    public Parser(String line) {
        int indexOfFirstSlash = line.indexOf('/');
        if (indexOfFirstSlash == -1) {
            command = line.trim();
            fieldValuePairs = new String[0];
        } else {
            command = line.substring(0, indexOfFirstSlash);
            fieldValuePairs = line.substring(indexOfFirstSlash + 1).split("/");
            for (String item : fieldValuePairs) {
                item = item.trim();
            }
        }
    }
}
