package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Event;
import winter.task.Task;

import java.io.IOException;


/**
 * Represents the command given by the user to add an Event task
 */
public class EventCommand extends Command {
    private String eventName;
    private String startTime;
    private String endTime;

    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE_SUCCESS = "Great! New Event added: ";

    public EventCommand(String eventName, String startTime, String endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Upon receiving the command from the user, create a new Event object, add it to task list,
     * show the confirmation message and update it in storage
     * @param tasks The TaskList object representing a list of the tasks
     * @param ui The user interface that provides feedback to the user
     * @param storage The storage object which helps store changes made to the list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task newEvent = new Event(tasks.getCurrentTaskIndex()+1, false, eventName, startTime,endTime);
        tasks.addNewTask(newEvent);
        //tasks.increaseLastTaskIndex();
        ui.showTaskAddedConfirm(tasks,newEvent);
        try {
            storage.writeToFile(formatEventForStorage(newEvent), true);
        } catch (IOException e) {
            System.out.println("Could not write to file: " + e.getMessage());
        }
    }
    /**
     * Format the Event object into a string that can be written to the storage file
     * @param newEvent The new deadline object that was created
     * @return String in the format that can be written to storage and read later
     */
    private String formatEventForStorage(Task newEvent) {

        return "E" + " | " + newEvent.getIsMarked() + " | " +
                newEvent.getTaskName() + " | " + newEvent.getStartTime() + " | " +
                newEvent.getEndTime() + System.lineSeparator();
    }
}
