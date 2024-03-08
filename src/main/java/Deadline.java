/**
 * The {@code Deadline} class represents a deadline item.
 * comparing with todo , it has a end time
 *
 */
public class Deadline extends Todo{
    String endtime;
    String Icon="D";
    /**
     * Constructs a new deadline item.
     *
     * @param isDone      Indicates whether the deadline item is done
     * @param description The description of the deadline item
     * @param endtime     A String indicating the end time of this deadline
     */
    public Deadline(boolean isDone, String description, String endtime) {
        super(isDone, description);
        this.endtime = endtime;
    }
    /**
     * similar to the one in Todo class
     * @return The string representation of the to-do item
     */
    @Override
    public String toString(){
        String DoneIcon = isDone? "x":" ";
        return "["+Icon+"]"+"["+DoneIcon+"] "+ description + "(by:"+ endtime+")";
    }

}