package Casper;


/**
 * An implementation of the <code>Task</code> class, specifically tasks that are bounded by a range (event-like).
 */
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

    /**
     * Change the given task's status as marked.
     */
    @Override
    public void markTask(){
        super.markTask();
        wrapEchoMessage("     Nice Job! I'll mark this as done: \n       "+ this);
    }

    /**
     * Change the given task's status as unmarked.
     */
    @Override
    public void unMarkTask(){
        super.unMarkTask();
        wrapEchoMessage("     Noted. Get it done soon...\n       "+ this);
    }

    /**
     * Returns a formatted representation of the <code>Event</code> instance.
     *
     * @return String representing the current <code>Event</code> instance for the save file.
     */
    @Override
    public String saveFormat(){
        return String.format("E # %d # %s # %s # %s", super.isDone?1:0, super.description, this.from, this.to);
    }
}
