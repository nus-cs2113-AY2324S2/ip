package Command;

import Exceptions.MarioErrorExecutingCommand;
import Templates.TaskList;

public class FindCommand extends BaseCommand {

    public FindCommand(String taskString){
        super(false, taskString);
    }
    public String execute(TaskList taskList) throws Exception{
        try {
            return (taskList.findTask(taskString));
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}
