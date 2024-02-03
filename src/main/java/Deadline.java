import java.util.Arrays;

// subclass of Task
public class Deadline extends Task{

    public Deadline(String description, int index) {
        super(description, index); // automatically invoke the constructor of Task
        System.out.println(toString());
    }
    public String getBy() {
        //this.by = Arrays.toString(description.split("/"));
        String[] splitLine = description.split("/"); //split input from / onwards
        return splitLine[1].substring(3); // return date
    }

    // since overriding occurs, toString() of Deadline class is called instead
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
    
}
