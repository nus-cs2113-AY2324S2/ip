package bobby;

public class Parser {
    public String parseCommand(String input) {
        String command;
        if (input.indexOf(' ') > 0) {
            command = input.substring(0, input.indexOf(' '));
        } else {
            command = input;
        }
        return command;
    }

    public String parseTodoDescription(String input) throws BobbyException {
        if (input.length() < 5 || input.substring(5).trim().isEmpty()) {
            throw new BobbyException();
        }
        return input.substring(5);
    }

    public String parseDeadlineDescription(String input) {
        return input.substring(9, input.indexOf("/by") - 1);
    }

    public String parseDeadlineBy(String input) {
        return input.substring(input.indexOf("/by") + 4);
    }

    public String parseEventDescription(String input) {
        return input.substring(6, input.indexOf("/from") - 1);
    }

    public String parseEventBy(String input) {
        return input.substring(input.indexOf("/to") + 4);
    }

    public String parseEventFrom(String input) {
        return input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
    }
}
