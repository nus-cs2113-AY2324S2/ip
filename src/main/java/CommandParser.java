public class CommandParser{
    private String commandName = null;
    private int argumentCount = 0;
    private String[] argumentTokens = {};
    private boolean isGoodTokens = false;
    public CommandParser(String userInput) {
        String commandToken = userInput.split(" ", 2)[0];
        String otherToken;
        if (!SyntaxChecker.validateCommandToken(commandToken)) {
            System.out.println("CommandParser: Command not found");
        } else if (!SyntaxChecker.hasArgument(userInput)){
            commandName = commandToken.toUpperCase();
            isGoodTokens = true;
        } else {
            commandName = commandToken.toUpperCase();
            otherToken = userInput.split(" ", 2)[1];
            if (SyntaxChecker.isDelimitedWithSpaces(commandName)) {
                argumentTokens = otherToken.split(" ",3);
            } else {
                argumentTokens = otherToken.split(" /", 3);
            }
            isGoodTokens = SyntaxChecker.validateTokens(commandName, argumentTokens);
        }
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
}
