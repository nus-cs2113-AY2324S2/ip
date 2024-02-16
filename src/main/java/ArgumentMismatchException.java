public class ArgumentMismatchException extends Exception{
    public String commandName;
    public int argumentCount;
    public ArgumentMismatchException(String commandName, int argumentCount) {
        this.commandName = commandName;
        this.argumentCount = argumentCount;
    }
}
