

// Subclass of Task
public class Deadline extends Task{

    public Deadline(String description, int index) {
        super(description, index); // Automatically invokes the constructor of Task
        System.out.println(toString());
    }
    public String getBy() {
        String[] splitLine = description.split("/"); // Split input from / onwards
        return splitLine[1].substring(3); // Return date
    }

    // Override task's toString() to add [D] and the deadline timing
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
    
}
