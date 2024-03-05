package dukeRobot.Tasks;
import java.util.*;


/**
 * responds to the implementation of Deadline commands
 * A <code>Deadline</code> corresponds to an deadline object
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by.split(" ", 2)[1];
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }



}
