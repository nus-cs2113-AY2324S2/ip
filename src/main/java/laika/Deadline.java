package laika;
public class Deadline extends Task{

    protected String by = " (by: ";


    public Deadline(String description, String by) {
        super(description);
        this.by += by + ")";
        this.type = "[D]";
    }


    @Override
    public String toString(){
        return type + super.toString()  + by;
    }
}