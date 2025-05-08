public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        taskTypeLetter = "D";
    }

    /**
     * Rewrites the description of the deadline into a format suitable to save onto the text file
     *
     * @return The string of the deadline in the correct format
     */
    @Override
    public String saveTaskDescription(){
        return (super.saveTaskDescription() + "|" + this.by.trim());
    }

    /**
     * Returns a String of the deadline consisting of a representation of
     * its task type, its marked status and description, and its due date
     *
     * @return The string of the deadline in the correct format
     */
    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by:" + this.by + ")");
    }

    /**
     * Prints out newly added deadline for the user
     *
     */
    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }
}
