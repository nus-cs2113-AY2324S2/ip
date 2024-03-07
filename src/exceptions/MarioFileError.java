package exceptions;

public class MarioFileError extends Exception{
    public MarioFileError(){
        super("Error while parsing file!");
    }
}
