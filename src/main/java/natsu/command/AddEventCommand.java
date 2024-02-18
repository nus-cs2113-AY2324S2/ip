package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Event;
import natsu.util.Printer;

import static natsu.util.TaskManager.list;

public class AddEventCommand {

    public AddEventCommand(String userInput) throws InvalidCommandException {
        if (!userInput.contains(CommandConstants.EVENT_START_INDICATOR) || !userInput.contains(CommandConstants.EVENT_END_INDICATOR)) {
            throw new InvalidCommandException("     I'm terribly sorry, but do add '/from' and '/to' along with the event details. Please try again!");
        }
        if (userInput.indexOf(CommandConstants.EVENT_START_INDICATOR) == CommandConstants.EVENT_COMMAND_PREFIX_LENGTH ||
                userInput.indexOf(CommandConstants.EVENT_END_INDICATOR) == CommandConstants.EVENT_COMMAND_PREFIX_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, but the description of an event cannot be empty. Please try again!");
        }
        Event event = getEvent(userInput);
        list.add(event);
        Printer.printTaskAdded(event.toString());
    }

    private static Event getEvent(String userInput) throws InvalidCommandException {
        if (userInput.indexOf(CommandConstants.EVENT_START_INDICATOR) + CommandConstants.EVENT_START_INDICATOR.length() >= userInput.length() ||
                userInput.indexOf(CommandConstants.EVENT_END_INDICATOR) + CommandConstants.EVENT_END_INDICATOR.length() == userInput.length()) {
            throw new InvalidCommandException("     I'm terribly sorry, but the date/time details of an event cannot be empty. Please try again!");
        }
        int fromIndex = userInput.indexOf(CommandConstants.EVENT_START_INDICATOR);
        int toIndex = userInput.indexOf(CommandConstants.EVENT_END_INDICATOR);
        String eventDescription = userInput.substring(CommandConstants.EVENT_COMMAND_PREFIX_LENGTH, fromIndex).trim();
        String eventFrom = userInput.substring(fromIndex + CommandConstants.EVENT_START_INDICATOR.length() + 1, toIndex).trim();
        String eventTo = userInput.substring(toIndex + CommandConstants.EVENT_END_INDICATOR.length() + 1).trim();
        return new Event(eventDescription, eventFrom, eventTo);
    }
}
