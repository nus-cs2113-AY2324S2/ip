public class Parser {
    /**
     * Splits the user's command into individual words. Space-delimited.
     *
     * @param userInput Command from the user.
     * @return String array containing space-delimited words.
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    public static String getCommandArgument (String userInput) {
        return userInput.split(" ", 2)[1];
    }
    }
}
