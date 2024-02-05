package Command;

import Exceptions.MarioErrorExecutingCommand;
import Exceptions.MarioMissingDateTime;
import Exceptions.MarioMissingTask;
import Exceptions.MarioWrongFormat;
import Templates.BaseDate;
import Templates.TaskList;
import Templates.Task.Deadline;

public class DeadlineCommand extends BaseCommand {
    public DeadlineCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            if (taskString.contains(Deadline.keyword)) {

                String dateString = taskString.substring(taskString.indexOf(Deadline.keyword) + Deadline.keyword.length(),
                        taskString.length()).strip();
                if (dateString.isBlank()) {
                    throw new MarioMissingDateTime();
                }
                taskString = taskString.substring(0, taskString.indexOf(Deadline.keyword));
                if (taskString.isBlank()) {
                    throw new MarioMissingTask();
                }
                BaseDate deadlineDate = new BaseDate(dateString);
                Deadline newDeadline = new Deadline(taskString, deadlineDate);
                return taskList.addTask(newDeadline);
            } else {
                throw new MarioWrongFormat();
            }
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}
