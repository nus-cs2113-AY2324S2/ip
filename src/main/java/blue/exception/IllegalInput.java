package blue.exception;

import blue.command.InputCommand;

public class IllegalInput extends Exception {
    private static final String WARNING_MESSAGE = "I'm sorry. I'm afraid I can't do that.";
    private InputCommand type;

    public IllegalInput() {
        this(InputCommand.undefined);
    }

    public IllegalInput(InputCommand type) {
        this.type = type;
    }

    public String getMessage() {
        switch (type) {
        case mark:
            return "Please specify a task index";
        default:
            return WARNING_MESSAGE;
        }
    }
}
