package tasks;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description + by);
        this.type = 'D';
    }

    @Override
    public char getType() {
        return this.type;
    }
}
