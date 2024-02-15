public class IllegalInput extends Exception {
    private static final String WARNING_MESSAGE = "I'm sorry. I'm afraid I can't do that.";
    private InputCommand type;
    IllegalInput() {
        this(InputCommand.undefined);
    }
    IllegalInput(InputCommand type) {
        this.type = type;
    }
    @Override
    public String toString() {
        switch (type) {
        case mark:
            return "Please specify a task index";
        default:
            return WARNING_MESSAGE;
        }
    }
}