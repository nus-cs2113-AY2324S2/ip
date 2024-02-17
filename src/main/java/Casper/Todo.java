package Casper;


public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public void markTask(){
        super.markTask();
        wrapEchoMessage("     Nice Job! I'll mark this as done: \n       "+toString());
    }
    @Override
    public void unMarkTask(){
        super.unMarkTask();
        wrapEchoMessage("     Noted. Get it done soon...\n       "+toString());
    }

    @Override
    public String saveFormat(){
        return String.format("T # %d # %s", super.isDone?1:0, super.description);
    }
}
