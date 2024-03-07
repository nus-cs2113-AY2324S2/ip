package command;

import exceptions.MarioErrorExecutingCommand;
import templates.BaseDate;
import templates.TaskList;

public class FinddateCommand extends BaseCommand {

    public FinddateCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            String dateString = taskString.strip();
            BaseDate deadlineDate = new BaseDate(dateString);
            return (taskList.findDate(deadlineDate));
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}