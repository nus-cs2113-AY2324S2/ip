package command;

import exceptions.MarioErrorExecutingCommand;
import exceptions.MarioMissingDateTime;
import exceptions.MarioMissingTask;
import exceptions.MarioWrongFormat;
import templates.BaseDate;
import templates.TaskList;
import templates.task.Deadline;

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
                taskString = taskString.substring(0, taskString.indexOf(Deadline.keyword)).replaceAll("[^a-zA-Z0-9]", "");
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
