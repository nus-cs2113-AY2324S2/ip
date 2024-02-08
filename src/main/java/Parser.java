public class Parser {
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    public static String getCommandArgument (String userInput) {
        try{
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    public static boolean isUserInputEmpty(String userInput){
        return userInput.isEmpty();
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
}
