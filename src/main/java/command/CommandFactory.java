package command;

import exception.ZukeException;
import ui.MessageDecoder;

/**
 * The CommandFactory class generates the command of different type based on the decoded user input.
 */
public class CommandFactory {
    /**
     * Generates the command of different type based on the decoded user input.
     *
     * @param input the user input.
     * @return the command to be executed.
     * @throws ZukeException if the input is invalid.
     */
    public static Command generate(String input) throws ZukeException {
        String[] processedMessage = MessageDecoder.separateCommand(input);
        String command = processedMessage[0];
        String information = processedMessage[1];
        CommandType type = CommandType.analyseType(command);
        String[] decodedInformation = MessageDecoder.decodeInput(type, information);

        switch (type) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case FIND:
            return new FindCommand(decodedInformation);

        case DELETE:
            return new DeleteCommand(Integer.parseInt(decodedInformation[0]));

        case HELP:
            return new HelpCommand();

        case MARK:
        case UNMARK:
            return new MarkUnmarkCommand(type,
                    Integer.parseInt(decodedInformation[0]));

        default:
            return new AddCommand(type, decodedInformation);
        }
    }
}
