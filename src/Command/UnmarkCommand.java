package Command;

import Exceptions.MarioErrorExecutingCommand;
import Templates.TaskList;

public class UnmarkCommand extends BaseCommand {

    public UnmarkCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            Integer index = Integer.parseInt(taskString);
            return taskList.unmarkTask(index);
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid task index to unmark!");
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}