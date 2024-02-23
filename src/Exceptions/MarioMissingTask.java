
package exceptions;

public class MarioMissingTask extends Exception{
    public MarioMissingTask(){
        super("No task description detected!");
    }
}
