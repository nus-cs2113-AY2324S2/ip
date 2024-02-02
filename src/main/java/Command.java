public class Command {
    public CommandType commandType;
    public CommandArguments commandArguments;

    public Command(CommandType commandType, CommandArguments commandArguments) {
        this.commandType = commandType;
        this.commandArguments = commandArguments;
    }
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.commandArguments = null;
    }
}
