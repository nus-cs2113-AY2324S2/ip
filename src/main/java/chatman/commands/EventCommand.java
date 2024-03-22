package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectFormatException;
import chatman.tasks.Event;

/**
 * Implements functionality to enable ChatMan response to user-entered "event DESCRIPTION /from  START /to END" command.
 *
 * @author LWachtel1
 * */
public class EventCommand extends TaskCommand {

    /**
     * Constructor for EventCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from CommandParser object) to use in perform()
     * method.
     * */
    public EventCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Parses event command into its arguments, description, from & to date-time Strings, then instantiates new Event
     * object and adds this to task arraylist.
     *
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add new Event object.
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     * @throws IncorrectFormatException If command is entered without required formatting of arguments.
     * */
    @Override
    public void perform() throws FullListException, IncorrectArgumentNumException, IncorrectFormatException {
        String[] eventCommand = userCommand.split("/",3);

        if (eventCommand.length != 3) {
            throw new IncorrectArgumentNumException("EVENT", userCommand);
        }

        int argumentCount = 0;
        for (String argument: eventCommand) {
            //removes leading and trailing whitespace to prevent failure of next guard clauses & date-times extraction
            eventCommand[argumentCount] = eventCommand[argumentCount].trim();
            if (argument.contains("/")) {
                throw new IncorrectArgumentNumException("EVENT", argument);
            }
            argumentCount++;
        }

        if (!eventCommand[1].startsWith("from")) {
            throw new IncorrectFormatException("EVENT", eventCommand[1]);
        }

        if (!eventCommand[2].startsWith("to")) {
            throw new IncorrectFormatException("EVENT", eventCommand[2]);
        }

        if (ChatMan.accessTasks().size() == ChatMan.MAX_NUM_TASKS) {
            throw new FullListException("EVENT", userCommand);
        }

        String eventDesc = eventCommand[0].replaceAll("(?i)EVENT", "").trim();
        String from = eventCommand[1].replaceAll("(?i)FROM", "").trim();
        String to = eventCommand[2].replaceAll("(?i)TO", "").trim();

        if (eventDesc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IncorrectFormatException("EVENT", userCommand);
        }


        ChatMan.accessTasks().add(new Event(eventDesc, from, to, userCommand));



    }
}
