package exceptions;

public class ArrayListOutOfBoundsException extends Exception {

    @Override
    public String getMessage() {
        return "\t This item does not exist in your list. Try a different item.";
    }
}
