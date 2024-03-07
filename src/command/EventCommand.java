package command;

import exceptions.MarioErrorExecutingCommand;
import exceptions.MarioMissingDateTime;
import exceptions.MarioMissingTask;
import exceptions.MarioWrongFormat;
import templates.BaseDate;
import templates.TaskList;
import templates.task.Event;

/**
 * Abstract base class and its concrete subclasses representing commands that can be executed within the Mario application.
 * BaseCommand defines the common structure for commands, including execution logic and a flag indicating if the command should terminate the application.
 * Subclasses like ByeCommand, DeleteCommand, UnmarkCommand, etc.,implement specific functionalities tailored to handling user commands in the chat application. Each command class encapsulates the logic required to execute a particular command, including adding tasks, marking tasks as done or not done, finding tasks, and handling task deadlines and events. The design pattern allows for easy extension and management of different types of commands within the system.
 */

public class EventCommand extends BaseCommand {

    public EventCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            if (taskString.contains(Event.KEYWORD_START) && taskString.contains(Event.KEYWORD_END)) {
                String dateString = taskString.substring(taskString.indexOf(Event.KEYWORD_START) + Event.KEYWORD_START.length(),
                        taskString.indexOf(Event.KEYWORD_END));
                String dateString1 = taskString.substring(
                        taskString.indexOf(Event.KEYWORD_END) + Event.KEYWORD_END.length(),
                        taskString.length());
                if (dateString.isBlank()) {
                    throw new MarioMissingDateTime();
                }
                if (dateString1.isBlank()) {
                    throw new MarioMissingDateTime();
                }

                taskString = taskString.substring(0, taskString.indexOf(Event.KEYWORD_START)).replaceAll("[^a-zA-Z0-9]", "");
                if (taskString.isBlank()) {
                    throw new MarioMissingTask();
                }
                BaseDate starDate = new BaseDate(dateString);
                BaseDate endDate = new BaseDate(dateString1);
                if (endDate.isBefore(starDate)) {
                    throw new Exception("End date cannot be before start date\n");
                }
                Event newEvent = new Event(taskString, starDate, endDate);

                return taskList.addTask(newEvent);
            } else {
                throw new MarioWrongFormat();
            }
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}