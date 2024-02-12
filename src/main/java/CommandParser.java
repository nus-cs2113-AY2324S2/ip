public class CommandParser {
    private String commandName = null;
    private int argumentCount = 0;
    private String[] argumentTokens = {};
    private boolean isGoodTokens = false;
    private String separator;

    public CommandParser(String userInput) {
        String commandToken = userInput.split(" ", 2)[0];
        String otherToken;
        if (!SyntaxChecker.validateCommandToken(commandToken)) {
            Formatter.printErrorWrongCommand();
        } else if (!SyntaxChecker.hasArgument(userInput)) {
            commandName = commandToken.toUpperCase();
            isGoodTokens = SyntaxChecker.isArgumentMatch(commandName, argumentCount);
        } else {
            commandName = commandToken.toUpperCase();
            otherToken = userInput.split(" ", 2)[1];
            separator = setSeparator();
            argumentTokens = otherToken.split(separator, CommandList.getMaxArgumentCount());
            argumentCount = argumentTokens.length;
            isGoodTokens = SyntaxChecker.validateTokens(commandName, argumentTokens, argumentCount);
        }
    }

    private String setSeparator() {
        boolean isDelimitedWithSpaces = CommandList.valueOf(commandName).ordinal() < 5;
        if (isDelimitedWithSpaces) {
            return " ";
        }
        return " /";
    }

    public String getCommandName() {
        return commandName;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public String[] getArgumentTokens() {
        return argumentTokens;
    }

    public boolean getIsGoodTokens() {
        return isGoodTokens;
    }

    public String getSeparator() {
        return separator;
    }
}
