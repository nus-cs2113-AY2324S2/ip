public class Command {
    private String command = "";
    private String argument = "";

    public Command(String input) {
        parseCommand(input);
    }

    /**
     * Splits user input into command and arguments.
     *
     * @param input User input.
     */
    public void parseCommand(String input) {
        String parts[] = input.split(" ", 2);
        this.command = parts[0].toLowerCase();
        if (parts.length > 1) {
            this.argument = parts[1];
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getArgument() {
        return this.argument;
    }
}
