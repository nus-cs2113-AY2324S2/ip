package exceptions.descriptions;

import exceptions.WallybotException;

/**
 * Thrown when the description does not follow the proper format.
 */
public class WrongFormatDescriptionException extends WallybotException {
    public WrongFormatDescriptionException(String type) {
        message = showWrongFormat(type);
    }

    /**
     * Create a message that reminds the user of the proper format, depending on the Task type.
     */
    public String showWrongFormat(String type) {
        StringBuilder message = new StringBuilder();
        message.append("Hmm...seems that your description is in the wrong format :O");
        message.append(System.lineSeparator());
        if (type.equals("Deadline")) {
            message.append("A Deadline needs '/by'");
        } else if (type.equals("Event")) {
            message.append("An Event needs '/from' and '/to'");
        }

        return message.toString();
    }
}
