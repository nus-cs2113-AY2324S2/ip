package Commands;

import Task.Task;
import Utils.Parser;

import java.util.ArrayList;

/**
 * Represents the Find Command. A <code>Find</code> object corresponds to
 * a Find Command
 */
public class Find extends Command{
    protected String keyWord;
    public Find(Parser parser){
        super(parser);
        this.keyWord = parser.getNumber();
    }

    @Override
    public void execute(ArrayList<Task> tasksList){
        ArrayList<Task> result = new ArrayList<Task>();
        for(Task element:tasksList) {
            if (element == null)
                break;
            if(element.get_description().contains(keyWord)){
                result.add(element);
            }

        }
        if(result.isEmpty()){
            System.out.println("\tSorry, there is no match items in your list!");
        }
        else{
            System.out.println(("\tHere are the matching tasks in your list:"));
            int order = 1;
            for(Task element:result) {
                if (element == null)
                    break;
                System.out.println("\t" + String.valueOf(order) + "." + element);
                order++;
            }
        }
    }
}
