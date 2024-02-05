package Command;

import Exceptions.MarioErrorExecutingCommand;
import Exceptions.MarioMissingDateTime;
import Exceptions.MarioMissingTask;
import Exceptions.MarioWrongFormat;
import Templates.BaseDate;
import Templates.TaskList;
import Templates.Task.Event;

public class EventCommand extends BaseCommand {

    public EventCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            if (taskString.contains(Event.keyword1) && taskString.contains(Event.keyword2)) {
                String dateString = taskString.substring(taskString.indexOf(Event.keyword1) + Event.keyword1.length(),
                        taskString.indexOf(Event.keyword2));
                String dateString1 = taskString.substring(
                        taskString.indexOf(Event.keyword2) + Event.keyword2.length(),
                        taskString.length());
                if (dateString.isBlank()) {
                    throw new MarioMissingDateTime();
                }
                if (dateString1.isBlank()) {
                    throw new MarioMissingDateTime();
                }

                taskString = taskString.substring(0, taskString.indexOf(Event.keyword1));
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