
package exceptions;
/**
 * This exception is thrown when no task description is detected in the user's input.
 * It ensures that tasks cannot be created or modified without a proper description.
 */

public class MarioMissingTask extends Exception{
    public MarioMissingTask(){
        super("No task description detected!");
    }
}
