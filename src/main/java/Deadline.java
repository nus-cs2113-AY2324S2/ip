// add code here
public class Deadline extends Task{
    //Attributes
    protected String by;

    //Constructors
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    //Methods
    public String toString(){
        return "[D]" + super.toString() + "(do by: " + by + ")";
    }
}