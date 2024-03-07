package command;

import exceptions.MarioErrorExecutingCommand;
import exceptions.MarioMissingDateTime;
import exceptions.MarioMissingTask;
import exceptions.MarioWrongFormat;
import templates.BaseDate;
import templates.TaskList;
import templates.task.Deadline;

/**
 * Abstract base class and its concrete subclasses representing commands that can be executed within the Mario application.
 * BaseCommand defines the common structure for commands, including execution logic and a flag indicating if the command should terminate the application.
 * Subclasses like ByeCommand, DeleteCommand, UnmarkCommand, etc.,implement specific functionalities tailored to handling user commands in the chat application. Each command class encapsulates the logic required to execute a particular command, including adding tasks, marking tasks as done or not done, finding tasks, and handling task deadlines and events. The design pattern allows for easy extension and management of different types of commands within the system.
 */

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
