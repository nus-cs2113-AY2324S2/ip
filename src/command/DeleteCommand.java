package command;

import templates.TaskList;
import exceptions.MarioErrorExecutingCommand;

public class DeleteCommand extends BaseCommand {

    public DeleteCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            if (taskString.equals("/all")) {
                while (taskList.getLength() != 0) {
                    taskList.deleteTask(1);
                }
                return ("Deleted all tasks");
            }
            Integer index = Integer.parseInt(taskString);
            String deleteTask_response = taskList.deleteTask(index);
            return deleteTask_response;
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid task index to delete!");
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}
