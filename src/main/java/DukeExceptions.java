public class DukeExceptions {
    public static class InvalidMarkException extends Exception {
    }

    public static class NoDescriptionException extends Exception{
    }

    public static void throwInvalidMarkException() throws InvalidMarkException {
        throw new InvalidMarkException();
    }

    public static void throwNoDescriptionException() throws NoDescriptionException {
        throw new NoDescriptionException();
    }
}
