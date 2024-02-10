public class CommandExecutor {
    public static void executeCommand(CommandParser readUserCommand) {
        if (!readUserCommand.getIsGoodTokens()) {
            System.out.println("CommandExecutor: Command could not be executed");
            System.out.println("Try again");
        }
        else {
            CommandList selectedCommand = CommandList.valueOf(readUserCommand.getCommandName());
            switch (selectedCommand) {
            case BYE:
                CommandList.executeBye();
                break;
            case LIST:
                CommandList.executeList();
                break;
            case MARK:
                CommandList.executeMark(readUserCommand);
                break;
            case TODO:
                CommandList.executeTodo(readUserCommand);
                break;
            case EVENT:
                CommandList.executeEvent(readUserCommand);
                break;
            case UNMARK:
                CommandList.executeUnmark(readUserCommand);
                break;
            case DEADLINE:
                CommandList.executeDeadline(readUserCommand);
                break;
            default:
                System.out.println("Unexpected error");
            }
        }
    }
}
