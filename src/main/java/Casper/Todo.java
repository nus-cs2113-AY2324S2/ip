package Casper;


/**
 * An implementation of the <code>Task</code> class, specifically tasks that are to-do in nature.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
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
     * Returns a formatted representation of the <code>Todo</code> instance.
     *
     * @return String representing the current <code>Todo</code> instance for the save file.
     */
    @Override
    public String saveFormat(){
        return String.format("T # %d # %s", super.isDone?1:0, super.description);
    }
}
