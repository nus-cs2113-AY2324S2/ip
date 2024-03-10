package exceptions;

public class IllegalNumberOfArguments extends Exception {
    public IllegalNumberOfArguments(int numOfArgumentsSupplied, int numOfArgumentsExpected, String sampleInput) {
        super("You have supplied " + numOfArgumentsSupplied + " arguments, but " 
                + numOfArgumentsExpected + " arguments are expected."
                + System.lineSeparator()
                + sampleInput);
    }
}
