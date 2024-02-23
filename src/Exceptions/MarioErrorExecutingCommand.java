package exceptions;

public class MarioErrorExecutingCommand extends Exception {
    public MarioErrorExecutingCommand(Exception args) {
        super("Error occured when executing command!\nError:\t" + args.getMessage());
    }
}