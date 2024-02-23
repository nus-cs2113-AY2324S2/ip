package command;

import templates.TaskList;

public class ByeCommand extends BaseCommand{

    public static String BYE_COMMAND = "Bye. Hope to see you again soon!\n";

    public ByeCommand(){
        super(true, "");
    }

    public String execute(TaskList taskList){
        return BYE_COMMAND;
    }
}
