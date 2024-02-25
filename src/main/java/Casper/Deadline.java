package Casper;


/**
 * An implementation of the <code>Task</code> class, specifically tasks that are bounded by a deadline.
 */
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
     * Returns a formatted representation of the <code>Deadline</code> instance.
     *
     * @return String representing the current <code>Deadline</code> instance for the save file.
     */
    @Override
    public String saveFormat(){
        return String.format("D # %d # %s # %s", super.isDone?1:0, super.description, this.by);
    }
}
