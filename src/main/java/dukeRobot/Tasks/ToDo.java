package dukeRobot.Tasks;

/**
 * represents the implementation of the manipulation of command ToDo
 * A <code>ToDo</code> corresponds to an object of TODO commands.
 */
public class ToDo extends Task {
    public ToDo(String description) throws ArrayIndexOutOfBoundsException{
        super(description);

    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
