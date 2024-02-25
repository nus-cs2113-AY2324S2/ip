package Commands;

import Task.Task;
import Utils.Parser;

import java.util.ArrayList;

public class Bye extends Command{
    public Bye(Parser parser){
        super(parser);
    }

    @Override
    public void execute(ArrayList<Task> tasksList){
        System.out.println("\t"+"Bye.Hope to see you again soon!");
    }
}
