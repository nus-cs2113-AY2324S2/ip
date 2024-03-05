package winter.commands;

import winter.Storage;
import winter.TaskList;
import winter.UI;
import winter.task.Event;
import winter.task.Task;

import java.io.IOException;



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

    private String formatEventForStorage(Task newEvent) {

        return "E" + " | " + newEvent.getIsMarked() + " | " +
                newEvent.getTaskName() + " | " + newEvent.getStartTime() + " | " +
                newEvent.getEndTime() + System.lineSeparator();
    }
}
