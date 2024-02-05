package Command;

import Templates.TaskList;

public class ByeCommand extends BaseCommand{

    public static String byeMessage = "Bye. Hope to see you again soon!\nClosing application in 5 seconds...";

    public ByeCommand(){
        super(true, "");
    }

    public String execute(TaskList taskList){
        return byeMessage;
    }
}
