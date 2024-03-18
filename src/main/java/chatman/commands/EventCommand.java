package chatman.commands;

import chatman.ChatMan;
import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
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
     * */
    @Override
    public void perform() throws FullListException, IncorrectArgumentNumException {
        String[] eventCommand = userCommand.split(" ",1);
        if (eventCommand.length !=2 ) {
            throw new IncorrectArgumentNumException();
        }

        eventCommand = userCommand.split("/");
        if (eventCommand.length != 3) {
            throw new IncorrectArgumentNumException();
        }

        if (ChatMan.storedTasks.size() == ChatMan.MAX_NUM_TASKS) {
            throw new FullListException();
        }

        String eventDesc = eventCommand[0].replaceAll("(?i)EVENT", "");
        String from = eventCommand[1].replaceAll("(?i)FROM", "").stripLeading();
        String to = eventCommand[2].replaceAll("(?i)TO", "").stripLeading();

        ChatMan.storedTasks.add(new Event(eventDesc, from, to));

        super.replyAddedTask();


    }
}
