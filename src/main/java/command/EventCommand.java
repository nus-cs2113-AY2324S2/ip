package command;

import commandexceptions.InvalidEventCommandException;
import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Event;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

public class EventCommand implements Command {
    private final String userInput;

    public EventCommand(String description) {
        userInput = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        String[] eventDescriptions = userInput.split("/from", 2);
        String invalidEvent ="Invalid Deadline Command Format!\n" +
                "Use: deadline (Deadline description) + /by + (date)\n";

        if(eventDescriptions.length != 2 || eventDescriptions[0].isBlank() || eventDescriptions[1].isBlank()) {
            throw new JingHaoExceptions(invalidEvent);
        }
        String description = eventDescriptions[0];
        String[] eventTime = eventDescriptions[1].split("/to", 2);
        if(eventTime.length != 2 || eventTime[0].isBlank() || eventTime[1].isBlank()) {
            throw new JingHaoExceptions(invalidEvent);
        }
        String fromDate = eventTime[0];
        String toDate = eventTime[1];
        Task newEvent = new Event(description,fromDate,toDate);
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:\n " +newEvent);
        ui.printTotalTask(taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
