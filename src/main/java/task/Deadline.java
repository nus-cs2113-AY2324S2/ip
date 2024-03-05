package task;


/**
 * Represents a Deadline in Kapwa
 * 
 * @see TaskM
 * 
 * @author yyangdaa
 * @version 0.1
 * @since 2024-03-03
 * 
 */

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline
     * @return the string representation of the Deadline
     */
    
    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[" + type + "][" + isDoneIcon + "]" + super.description + " (by: " + by + ")";
    }
}
