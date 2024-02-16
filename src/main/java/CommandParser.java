public class CommandParser {
    private String commandName = null;
    private int argumentCount = 0;
    private String[] argumentTokens = {};
    private String separator;
    public CommandParser(String userInput) throws IllegalCommandException, ArgumentMismatchException, BadTokenException{
        String commandToken = userInput.split(" ", 2)[0];
        String otherToken;
        if (!SyntaxChecker.validateCommandToken(commandToken)) {
            throw new IllegalCommandException();
        }
        else if (!SyntaxChecker.hasArgument(userInput)) {
            commandName = commandToken.toUpperCase();
            if (!SyntaxChecker.isArgumentMatch(commandName,argumentCount)) {
                throw new ArgumentMismatchException(commandName, argumentCount);
            }
        }
        else {
            commandName = commandToken.toUpperCase();
            otherToken = userInput.split(" ", 2)[1];
            separator = setSeparator();
            argumentTokens = otherToken.split(separator, CommandList.getMaxArgumentCount());
            argumentCount = argumentTokens.length;
            if (!SyntaxChecker.validateTokens(commandName, argumentTokens, argumentCount)) {
                throw new BadTokenException();
            }
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
    public String getSeparator() {
        return separator;
    }
}
