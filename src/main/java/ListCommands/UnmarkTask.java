package ListCommands;

import sam.task.Task;

import java.util.ArrayList;

public class UnmarkTask extends Command{

    public UnmarkTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    public void execute(String indexStr) throws SamException{
        int index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
        indexCheck(index); // Check if index is valid
        tasks.get(index).setStatus(false); // Mark the task as done
        System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index)); // Print confirmation message
    }
}
