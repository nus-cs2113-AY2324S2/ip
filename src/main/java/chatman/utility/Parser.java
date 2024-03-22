package chatman.utility;

import chatman.commands.*;

import chatman.exceptions.FalseCommandException;
import chatman.exceptions.FullListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectIndexException;
import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectFormatException;


import java.util.Arrays;

/**
 * Implements command parser.
 *
 * @author LWachtel1
 * */
public class Parser {

    private static final String[] RECOGNISED_COMMANDS = {"BYE", "LIST", "MARK", "UNMARK", "DELETE", "TODO",
            "DEADLINE", "EVENT", "FIND"};

    /**
     * Constructor for Parser.
     */
    public Parser() {

    }


    /**
     * Extracts command type from user text input and then instantiates object of corresponding class.
     * Calls object's perform() method to execute desired ChatMan command.
     * Returns reference of object to instance of Ui class.
     *
     *
     * @param receivedCommand Raw text input from user, intended to represent a chatbot command.
     * @param isLoadFile Boolean that specifies whether parse() is being called during initial program load or not.
     * @return Reference to instantiated object corresponding to command.
     * @throws FalseCommandException If command type entered is unrecognised.
     * @throws FullListException If task arraylist size equals MAX_NUM_TASKS when attempting to add Todo, Deadline
     * or Event.
     * @throws IncorrectArgumentNumException If a command is provided with incorrect number of arguments.
     * @throws IncorrectIndexException If MarkUnmarkCommand or DeleteCommand provided with non-numerical index or
     * with numerical index beyond task arraylist size.
     * @throws EmptyListException If list of stored tasks is currently empty.
     * @throws IncorrectFormatException If command is entered without required formatting of arguments.
     * */

    public static Command parse(String receivedCommand, boolean isLoadFile) throws FalseCommandException,
            FullListException, IncorrectArgumentNumException, IncorrectIndexException, EmptyListException,
            IncorrectFormatException {

        Command commandToReturn = null;

        String[] fullCommand = receivedCommand.split(" ");
        String commandType = fullCommand[0].toUpperCase(); //first word assumed to specify type of command

        //@@author LWachtel1-reused
        //Reused from https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
        //with minor modifications
        boolean isValidCommand = Arrays.asList(RECOGNISED_COMMANDS).contains(commandType);
        //@@author
        if (!isValidCommand) {
            //FalseCommandException caught by read() method of Ui object
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

        case "DELETE":
            DeleteCommand remover = new DeleteCommand(receivedCommand);
            remover.perform();
            commandToReturn= remover;
            break;

        case "TODO":
            TodoCommand toDo = new TodoCommand(receivedCommand);
            toDo.perform();
            commandToReturn = toDo;
            if (!isLoadFile) {
                TaskCommand.replyAddedTask();
            }
            break;

        case "DEADLINE":
            DeadlineCommand deadLine = new DeadlineCommand(receivedCommand);
            deadLine.perform();
            commandToReturn = deadLine;
            if (!isLoadFile) {
                TaskCommand.replyAddedTask();
            }
            break;


        case "EVENT":
            EventCommand event = new EventCommand(receivedCommand);
            event.perform();
            commandToReturn = event;
            if (!isLoadFile) {
                TaskCommand.replyAddedTask();
            }
            break;

        case "FIND":
            FindCommand find = new FindCommand(receivedCommand);
            find.perform();
            commandToReturn = find;
            break;

        default:
            break;
        }

        return commandToReturn;

    }


}
