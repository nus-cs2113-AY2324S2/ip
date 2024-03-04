public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        taskTypeLetter = "D";
    }

    @Override
    public String saveTaskDescription(){
        return (super.saveTaskDescription() + "|" + this.by.trim());
    }
    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by:" + this.by + ")");
    }

    @Override
    public void printTask (int numberOfListItems){
        System.out.println("Got it! I've added this task:\n" + this.toString());
        System.out.println("Now you have " + (numberOfListItems + 1) + " tasks in the list!");
    }
}
