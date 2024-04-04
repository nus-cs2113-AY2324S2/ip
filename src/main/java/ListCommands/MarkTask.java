package ListCommands;

import sam.task.Task;

import java.util.ArrayList;

public class MarkTask extends Command{

    public MarkTask(ArrayList<Task> tasks) {
        super(tasks);
    }

    public void execute(String indexStr) throws SamException{
        int index = Integer.parseInt(indexStr) - 1; // Convert index string to integer
        indexCheck(index); // Check if index is valid
        tasks.get(index).setStatus(true); // Mark the task as done
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index)); // Print confirmation message
    }
}
