package Command;

import Exceptions.MarioErrorExecutingCommand;
import Templates.TaskList;

public class MarkCommand extends BaseCommand {

    public MarkCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            Integer index = Integer.parseInt(taskString);
            return taskList.markTask(index);
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid task index to mark!");
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}