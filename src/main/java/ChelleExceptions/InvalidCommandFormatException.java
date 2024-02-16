package ChelleExceptions;
import ChelleCommands.*;

public class InvalidCommandFormatException extends Exception{

    public InvalidCommandFormatException(CommandType command){

        switch (command) {

        case MARK:
            System.out.println("Chelle: Invalid format. Use 'mark ___'.");
            break;

        case UNMARK:
            System.out.println("Chelle: Invalid format. Use 'unmark ___'.");
            break;

        case TODO:
            System.out.println("Chelle: Invalid format. Use 'todo ___'.");
            break;

        case DEADLINE:
            System.out.println("Chelle: Invalid format. Use 'deadline ___ /by ___'.");
            break;

        case EVENT:
            System.out.println("Chelle: Invalid format. Use 'event ___ /from ___ /to ___'.");
            break;

        default:
            System.out.println("Chelle: Invalid command. Please start your command with one of the following commands: \n" +
                    "'list', 'mark', 'unmark', 'todo', 'deadline' or 'event'.");
            break;
        }

    }
}
