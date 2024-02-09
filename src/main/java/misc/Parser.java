package misc;

public class Parser {
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    public static String getCommandArgument (String userInput) {
        try{
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e){
            // If there is no argument associated with that command
            return "";
        }
    }

    /**
     * Checks if the command argument contains only a number of type String.
     *
     * @param commandArgument The argument portion of the user input.
     * @return Whether the argument is a number of type String.
     */
    public static boolean isValidTaskNumberString(String commandArgument){
        String[] argumentList = commandArgument.split(" ");
        if (argumentList.length != 1){
            return false;
        }

        try {
            Integer.parseInt(commandArgument);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     * Gets the task number by converting the string to integer type.
     * Assumes that the input has been checked and is valid.
     *
     * @param taskNumberString The task number that is of type String.
     * @return The task number of type int.
     */
    public static int getTaskNumberFromString(String taskNumberString){
        return Integer.parseInt(taskNumberString);
    }

    public static String parseDeadlineDescription(String rawDeadlineDescription){
        String[] stringArray = rawDeadlineDescription.split("/by", 2);
        if (stringArray.length != 2) {
            return rawDeadlineDescription;
        }
        return stringArray[0] + "(by:" + stringArray[1] + ")";
    }

    public static String parseEventDescription(String rawEventDescription){
        String[] stringArray = rawEventDescription.split("/from", 2);
        if (stringArray.length != 2){
            return rawEventDescription;
        }

        String[] toFromArray = stringArray[1].split("/to", 2);
        if (toFromArray.length != 2){
            return rawEventDescription;
        }

        return stringArray[0] + "(from:" + toFromArray[0] + "to:" + toFromArray[1] + ")";
    }
}
