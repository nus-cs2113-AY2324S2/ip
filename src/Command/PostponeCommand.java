package Command;

import Exceptions.MarioErrorExecutingCommand;
import Exceptions.MarioMissingDateTime;
import Exceptions.MarioMissingTask;
import Exceptions.MarioWrongFormat;
import Templates.BaseDate;
import Templates.TaskList;
import Templates.Task.Deadline;
import Templates.Task.Event;
import Templates.Task.Task;

public class PostponeCommand extends BaseCommand {
    public PostponeCommand(String taskString) {
        super(false, taskString);
    }

    public String execute(TaskList taskList) throws Exception {
        try {
            String[] stringArr = taskString.strip().split(" ");
            Integer index = Integer.parseInt(stringArr[0]);
            Task targetTask = taskList.getTask(index);
            if(targetTask.getCompleted()){
                throw new Exception("Task has been completed! Unable to edit...");
            }
            if (targetTask instanceof Deadline) {
                if (stringArr[1].equals(Deadline.keyword)) {
                    String dateString = taskString
                            .substring(taskString.indexOf(Deadline.keyword) + Deadline.keyword.length());
                    BaseDate newDate = new BaseDate(dateString);
                    ((Deadline) targetTask).setDeadline(newDate);
                } else {
                    throw new MarioWrongFormat();
                }
            } else if (targetTask instanceof Event) {
                if (taskString.contains(Event.keyword1) && taskString.contains(Event.keyword2)) {
                    String dateString = taskString.substring(
                            taskString.indexOf(Event.keyword1) + Event.keyword1.length(),
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
                    ((Event) targetTask).setDate(starDate, endDate);
                } else {
                    throw new MarioWrongFormat();
                }
            }
            return String.format("Task date has been updated!\n%s", targetTask.toString());
        } catch (NumberFormatException e) {
            throw new Exception("Please provide a valid task index to mark!");
        } catch (Exception e) {
            throw new MarioErrorExecutingCommand(e);
        }
    }
}