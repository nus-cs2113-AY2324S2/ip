package ChelleExceptions;

import ChelleCommands.CommandType;
import Common.Messages;

public class InvalidCommandFormatException extends Exception{

    public InvalidCommandFormatException(CommandType command){

        switch (command) {

        case MARK:
            System.out.println(Messages.MESSAGE_INVALID_MARK_FORMAT);
            break;
        case UNMARK:
            System.out.println(Messages.MESSAGE_INVALID_UNMARK_FORMAT);
            break;
        case TODO:
            System.out.println(Messages.MESSAGE_INVALID_TODO_FORMAT);
            break;
        case DEADLINE:
            System.out.println(Messages.MESSAGE_INVALID_DEADLINE_FORMAT);
            break;
        case EVENT:
            System.out.println(Messages.MESSAGE_INVALID_EVENT_FORMAT);
            break;
        case DELETE:
        System.out.println(Messages.MESSAGE_INVALID_DELETE_FORMAT);
        break;
        default:
            System.out.println(Messages.MESSAGE_INVALID_COMMAND);
            break;
        }

    }
}
