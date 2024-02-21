public class DukeExceptions {
    public static class InvalidItemException extends Exception {
    }

    public static class NoDescriptionException extends Exception{
    }

    public static void throwInvalidItemException() throws InvalidItemException {
        throw new InvalidItemException();
    }

    public static void throwNoDescriptionException() throws NoDescriptionException {
        throw new NoDescriptionException();
    }
}
