package exceptions;

public class MarioMissingDateTime extends Exception{
    public MarioMissingDateTime(){
        super("No date and time detected!");
    }
}
