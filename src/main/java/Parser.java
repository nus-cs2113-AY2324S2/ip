public class Parser {
    /**
     * Splits the user's command into individual words. Space-delimited.
     *
     * @param userInput Command from the user.
     * @return String array containing space-delimited words.
     */
    public static String[] getCommandArguments(String userInput) {
        return userInput.split(" ");
    }
}
