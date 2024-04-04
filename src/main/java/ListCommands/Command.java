package ListCommands;

import sam.task.Task;

import java.util.ArrayList;

public abstract class Command {

    protected ArrayList<Task> tasks;

    protected void indexCheck(int index) throws SamException {
        if (index < 0 || index >= tasks.size()) {
            if (index < 0) {
                throw new SamException("No can do, the list starts at 1."); // Throw exception if index is less than 0
            } else {
                throw new SamException("Hey lil bro, you have only " + tasks.size() + " things in the list."); // Throw exception if index is out of bounds
            }
        }
    }

    public Command(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void execute() throws SamException{

    }
}
