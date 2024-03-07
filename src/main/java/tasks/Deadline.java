package tasks;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by, int taskNum){
        super(description + by, taskNum);
        this.type = 'D';
    }

    @Override
    public char getType() {
        return this.type;
    }
}
