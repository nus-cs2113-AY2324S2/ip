package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidEventCommandException;
import bossman.task.Event;
import bossman.task.Task;
import bossman.task.TaskList;

public class EventCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;
    private String from;
    private String to;

    public EventCommand(TaskList userTasks, String commandArgs) throws InvalidEventCommandException {
        this.userTasks = userTasks;

        String[] eventArgParts = commandArgs.split("/from", 2);

        if (eventArgParts.length != 2 || eventArgParts[0].isBlank()) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        taskDescription = eventArgParts[0];
        String[] eventArgTimeParts = eventArgParts[1].split("/to", 2);

        if (eventArgTimeParts.length != 2) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        from = eventArgTimeParts[0];
        to = eventArgTimeParts[1];
    }

    @Override
    public void execute() {
        Task eventTask = new Event(taskDescription, false, from, to);
        userTasks.addTask(eventTask);

        Ui.printMessageNoSepSameLine("Added:");
        eventTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
