public class Event extends Task {
    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        taskTypeLetter = "E";
        this.from = from;
        this.to = to;
    }

    /**
     * Rewrites the description of the event into a format suitable to save onto the text file
     *
     * @return The string of the event in the correct format
     */
    @Override
    public String saveTaskDescription(){
        return (super.saveTaskDescription() + "|" + this.from.trim() + "|" + this.to.trim());
    }

    /**
     * Returns a String of the event consisting of a representation of
     * its task type, its marked status and description, when it starts, and when it ends.
     *
     * @return The string of the event in the correct format
     */
    @Override
    public String toString() {
        return("[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")");
    }

    /**
     * Prints out newly added event for the user
     *
     */
    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }
}
