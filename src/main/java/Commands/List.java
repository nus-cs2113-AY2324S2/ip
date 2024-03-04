package Commands;

import Task.Task;
import Utils.Parser;

import java.util.ArrayList;


public class List extends Command{

    public List(Parser parser){super(parser);}
    /**
     * Execute the List command , print all the items in given taskList
     * @param tasksList stores in ChatBot
     */
    @Override
    public void execute(ArrayList<Task> tasksList){
        if(tasksList.isEmpty()){
            System.out.println("\t"+"There is currently nothing in my memory, nothing to list!"+"\n");
        }
        int order = 1;
        for(Task element:tasksList) {
            if (element == null)
                break;
            System.out.println("\t" + String.valueOf(order) + "." + element);
            order++;
        }
    }
}
