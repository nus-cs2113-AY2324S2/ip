package Casper;


public class Deadline extends Task {
    String by;
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]"+super.toString() + " (by: "+by+")";
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
}
