/**
 * Represent the error handling class for the chatbot Hachi.
 */

public class HachiException extends Exception{
    public static final String INVALID_INPUT_MESSAGE = "Not marvelous! I'm not sure what you're saying here...";

    public HachiException (String errorMessage) {
        super(errorMessage);
    }

    public static void invalidInput() throws HachiException {
        throw new HachiException(INVALID_INPUT_MESSAGE);
    }
}
