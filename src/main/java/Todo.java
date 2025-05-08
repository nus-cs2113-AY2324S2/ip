public class Todo extends Task{

    public Todo(String description) {
        super(description);
        taskTypeLetter = "T";
    }

    /**
     * Returns a String of the todo consisting of a representation of
     * its task type, its marked status, and its description
     *
     * @return The string of the todo in the correct format
     */
    @Override
    public String toString(){
        return ("[T]" + super.toString());
    }

    /**
     * Prints out newly added todo for the user
     *
     */
    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }
}
