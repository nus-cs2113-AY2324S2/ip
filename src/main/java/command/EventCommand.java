package command;

import commandexceptions.JingHaoExceptions;
import storage.Storage;
import tasktype.Event;
import tasktype.Task;
import tasktype.TaskList;
import ui.Ui;

/**
 * Represents the command to add an Event task.
 */
public class EventCommand implements Command {
    private final String userInput;

    /**
     * Constructs a EventCommand with the user's input.
     *
     * @param description The description of the task from the user.
     */
    public EventCommand(String description) {
        userInput = description;
    }

    /**
     * Executes the command by creating an Event object and adding the event task to the existing task list.
     * Displays event task to be added and the corresponding status on the screen after executing the command.
     *
     * @param taskList The list of task in the JingHao chatbot.
     * @param ui The user interface of the JingHao chatbot.
     * @param storage The file storage of the JingHao chatbot.
     * @throws JingHaoExceptions If user input does not satisfy the required format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JingHaoExceptions {
        boolean isFromFile = false;
        String[] eventDescriptions = userInput.split("/from", 2);
        String invalidEvent ="Invalid event Command Format!\n" +
                "Use: event (Event description) + /from + (YYYY-MM-DD) + /to + (YYYY-MM-DD) \n";

        if(eventDescriptions.length != 2 || eventDescriptions[0].isBlank() || eventDescriptions[1].isBlank()) {
            throw new JingHaoExceptions(invalidEvent);
        }
        String description = eventDescriptions[0];
        String[] eventTime = eventDescriptions[1].split("/to", 2);
        if(eventTime.length != 2 || eventTime[0].isBlank() || eventTime[1].isBlank()) {
            throw new JingHaoExceptions(invalidEvent);
        }
        String fromDate = eventTime[0].trim();
        String toDate = eventTime[1].trim();
        Task newEvent = new Event(description,fromDate,toDate, isFromFile);
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task:\n " +newEvent);
        ui.printTotalTask(taskList.size());
    }

    /**
     * Determines whether the command is an exit command.
     *
     * @return Returns false since this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
