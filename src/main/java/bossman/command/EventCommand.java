package bossman.command;

import bossman.ui.Ui;
import bossman.exceptions.commandexceptions.InvalidEventCommandException;
import bossman.task.Event;
import bossman.task.Task;
import bossman.task.TaskList;

/**
 * EventCommand represents the command to add an event task.
 * It implements the Command interface.
 */
public class EventCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;
    private String from; // Start time of the event
    private String to; // End time of the event

    /**
     * Constructs an EventCommand with the provided TaskList and command arguments.
     *
     * @param userTasks the TaskList containing user's tasks
     * @param commandArgs the arguments provided with the command
     * @throws InvalidEventCommandException if the command arguments are invalid
     */
    public EventCommand(TaskList userTasks, String commandArgs) throws InvalidEventCommandException {
        this.userTasks = userTasks;

        // Split commandArgs into task description and event time parts
        String[] eventArgParts = commandArgs.split("/from", 2);

        // Check if the command arguments are valid
        if (eventArgParts.length != 2 || eventArgParts[0].isBlank()) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        taskDescription = eventArgParts[0];

        // Check if the event time parts are valid
        String[] eventArgTimeParts = eventArgParts[1].split("/to", 2);
        if (eventArgTimeParts.length != 2) {
            throw new InvalidEventCommandException("Invalid event command");
        }

        from = eventArgTimeParts[0];
        to = eventArgTimeParts[1];
    }

    /**
     * Executes the EventCommand by adding the event task to the TaskList.
     * Prints a confirmation message with the added task and the updated task count.
     */
    @Override
    public void execute() {
        Task eventTask = new Event(taskDescription, false, from, to);
        userTasks.addTask(eventTask);

        Ui.printMessageNoSepSameLine("Added:");
        eventTask.printTask();
        Ui.printMessageNoSepNewLine("");
        Ui.printMessageWithSepNewLine("Now you have " + userTasks.getTaskListSize() + " tasks in the list.");
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, indicating that this command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
