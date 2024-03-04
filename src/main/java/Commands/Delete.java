package Commands;

import Task.Deadline;
import Task.Task;
import Utils.Parser;
import Utils.Storage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Delete Command. A <code>Delete</code> object corresponds to
 * a Delete Command
 */

public class Delete extends Command{
    int deleteNumber;
    public Delete(Parser parser){
        super(parser);
        this.deleteNumber = Integer.parseInt(parser.getNumber());
    }
    @Override
    public void execute(ArrayList<Task> tasksList) throws IOException {
        try {
            int index = deleteNumber-1;
            System.out.println("\t" + "I see, I will remove the task " + deleteNumber + "\n");
            System.out.println("\t" + tasksList.get(index));
            tasksList.remove(index);
            System.out.println("\t" + "Now you have " + tasksList.size() + " tasks in my memory \n");
            System.out.println("\tStatus changed, I will save the data for you!");
            Storage storage = new Storage();
            Storage.saveStatus(tasksList);
        }catch (IndexOutOfBoundsException e){
            System.out.println("\tInput error: please enter an number less than "+this.deleteNumber+"!");
        }
    }
}
