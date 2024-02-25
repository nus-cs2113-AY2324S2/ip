package Commands;

import Task.Task;
import Utils.Parser;
import Utils.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Mark extends Command{
    int markNumber;
    public Mark(Parser parser){
        super(parser);
        this.markNumber = Integer.parseInt(parser.getNumber());
    }
    @Override
    public void execute(ArrayList<Task> tasksList) throws IOException {
        try {
            tasksList.get(markNumber - 1).mark(true);
            System.out.println("\t"+"Nice! I've marked this task as done:");
            System.out.println("\t" + tasksList.get(markNumber - 1));
            System.out.println("\tStatus changed, I will save the data for you!");
            Storage storage = new Storage();
            Storage.saveStatus(tasksList);
        }catch (IndexOutOfBoundsException e){
            System.out.println("\tInput error: please enter an number less than "+this.markNumber+"!");
        }
    }
}
