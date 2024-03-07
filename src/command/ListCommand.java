package command;

import exceptions.MarioErrorExecutingCommand;
import templates.TaskList;

public class ListCommand extends BaseCommand {

    public ListCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception{
        try {
            return (taskList.toString());
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}
