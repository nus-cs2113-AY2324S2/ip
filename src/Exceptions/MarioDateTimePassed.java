package exceptions;

public class MarioDateTimePassed extends Exception {
    public MarioDateTimePassed() {
        super("Input date has already passed!");
    }
}