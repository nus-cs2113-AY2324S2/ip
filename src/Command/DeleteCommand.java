package Command;

import Exceptions.MarioErrorExecutingCommand;
import Templates.TaskList;
import Templates.Task.*;

public class DeleteCommand extends BaseCommand {

    public DeleteCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            Integer index = Integer.parseInt(taskString);
            Task deletedTask = taskList.getTask(index);
            String deleteTask_response = taskList.deleteTask(index);
            return deleteTask_response + (String.format("Noted. I've removed this task:\n%s\n%s", deletedTask.toString(),
            taskList.stringNumberTask()));
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid task index to delete!");
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}
