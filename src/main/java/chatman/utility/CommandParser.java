package chatman.utility;

import chatman.ChatMan;
import chatman.commands.*;


import chatman.exceptions.*;
import chatman.storage.StorageHandler;
import chatman.tasks.Task;

import java.util.Arrays;

/**
 * Implements command parser.
 *
 * @author LWachtel1
 * */
public class CommandParser {

    private static final String[] RECOGNISED_COMMANDS = {"BYE", "LIST", "MARK", "UNMARK", "TODO",
            "DEADLINE", "EVENT"};

    /**
     * Constructor for CommandParser.
     */
    public CommandParser() {

    }


    /**
     * Extracts command type from user text input and then instantiates object of corresponding class.
     * Calls object's perform() method to execute desired ChatMan command.
     * Returns reference of object to instance of CommandReader class.
     *
     *
     * @param receivedCommand Raw text input from user, intended to represent a chatbot command.
     * @return Reference to instantiated object corresponding to command.
     * @throws FalseCommandException If command type entered is unrecognised.
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add Todo, Deadline
     * or Event.
     * @throws IncorrectArgumentNumException If a command is provided with incorrect number of arguments.
     * @throws IncorrectMarkUnmarkException If MarkUnmarkCommand provided with non-numerical index or with numerical
     * index beyond task arraylist size.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * @throws IncorrectFormatException If command is entered without required formatting of arguments.
     * */
    public static Command parse(String receivedCommand, boolean loadFile) throws FalseCommandException,
            FullListException,
            IncorrectArgumentNumException, IncorrectMarkUnmarkException, EmptyListException, IncorrectFormatException {
        Command commandToReturn = null;

        String[] fullCommand = receivedCommand.split(" ");
        String commandType = fullCommand[0].toUpperCase(); //first word assumed to specify type of command

        //@@author LWachtel1-reused
        //Reused from https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
        //with minor modifications
        boolean isValidCommand = Arrays.asList(RECOGNISED_COMMANDS).contains(commandType);
        //@@author
        if (!isValidCommand) {
            //FalseCommandException caught by read() method of CommandReader object
            throw new FalseCommandException(commandType,receivedCommand);
        }

        switch (commandType) {
        case "BYE":
            ByeCommand exit = new ByeCommand(receivedCommand);
            exit.perform();
            commandToReturn = exit;
            break;

        case "LIST":
            ListCommand list = new ListCommand(receivedCommand);
            list.perform();
            commandToReturn = list;
            break;

        case "MARK":
            //Fallthrough
        case "UNMARK":
            MarkUnmarkCommand listMarker = new MarkUnmarkCommand(receivedCommand);
            listMarker.perform();
            commandToReturn = listMarker;
            break;

        case "TODO":
            TodoCommand toDo = new TodoCommand(receivedCommand);
            toDo.perform();
            commandToReturn = toDo;
            if (!loadFile) {
                TaskCommand.replyAddedTask();
            }
            break;

        case "DEADLINE":
            DeadlineCommand deadLine = new DeadlineCommand(receivedCommand);
            deadLine.perform();
            commandToReturn = deadLine;
            if (!loadFile) {
                TaskCommand.replyAddedTask();
            }
            break;


        case "EVENT":
            EventCommand event = new EventCommand(receivedCommand);
            event.perform();
            commandToReturn = event;
            if (!loadFile) {
                TaskCommand.replyAddedTask();
            }
            break;

        default:
            break;
        }

        return commandToReturn;

    }


}
