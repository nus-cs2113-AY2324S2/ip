package Casper;


public class Event extends Task {
    String from, to;
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + " (from: " + from+" to: "+to+")";
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
        return String.format("E # %d # %s # %s # %s", super.isDone?1:0, super.description, this.from, this.to);
    }

}
