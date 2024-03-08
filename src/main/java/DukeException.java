/**
 * DukeException class is to throw an excpetion
 */
public class DukeException extends Exception {
    /**
     * DukeException to throw exception when todo description is needed or when command is not valid
     * @param message the output to tell users to key in a valid command
     */
    public DukeException(String message) {
        super(message);
    }
}
