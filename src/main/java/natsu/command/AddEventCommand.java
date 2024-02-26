package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Event;
import natsu.util.Ui;

import static natsu.util.TaskList.list;

/**
 * Represents a command to add a new event task to the task list.
 * This command parses user input to extract the event description,
 * start time, and end time. It then creates a new {@link Event} object
 * and adds it to the task list.
 */
public class AddEventCommand {

    /**
     * Constructs an {@code AddEventCommand} and adds a new event to the task list based on user input.
     * The command checks for the presence of '/from' and '/to' in the user input to extract the necessary details.
     *
     * @param userInput The full user input string containing the event description, start time, and end time.
     * @throws InvalidCommandException If the input does not contain '/from' and '/to' indicators,
     *                                 if the description of the event is empty, or if the event start or end time
     *                                 is not specified correctly.
     */
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
        Ui.printTaskAdded(event.toString());
    }

    /**
     * Extracts and creates an event from the user input.
     * This method parses the input to separate the event description from its start and end times.
     *
     * @param userInput The user input containing event details.
     * @return The {@link Event} created from the user input.
     * @throws InvalidCommandException If the date/time details of an event cannot be parsed
     *                                 or are empty.
     */
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
