public class Command {
    public CommandType commandType;
    public int inputIndex;

    public Command(CommandType commandType, int inputIndex) {
        this.commandType = commandType;
        this.inputIndex = inputIndex;
    }
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }
}
