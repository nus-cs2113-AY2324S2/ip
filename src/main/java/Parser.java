import java.util.Scanner;

/**
 * Defines a class whose methods extract specific bits of information from the user's raw input.
 */
public class Parser {
    public static String getFirstWord(String userInput){
        return userInput = userInput.split(" ")[0];
    }


    /**
     * Reads in an input from the standard input.
     *
     * @return Raw user input.
     */
    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }


    /**
     * Returns the first integer in the user input
     *
     * @param userInput Raw user input.
     * @return A single integer.
     */
    public static int getFirstInt(String userInput){
        String numberString = "0";
        for (int i = 0; i < userInput.length(); i++) {
            if (Character.isDigit(userInput.charAt(i))) {
                numberString = userInput.substring(i);
                break;
            }
        }
        return Integer.parseInt(numberString);
    }


    /**
     * Returns the entire description of a task located within the user's command.
     * Description can be more than 1 word.
     *
     * @param userInput Raw user input.
     * @return String with task description.
     */
    public static String extractDescription(String userInput){
        try {
            int start = userInput.indexOf(" ") + 1;
            int end = userInput.indexOf("/") - 1;
            if (start > 0 && (end > start)) {
                return userInput.substring(start, end);
            } else if (start > 0) {
                return userInput.substring(start);
            } else {
                throw new JeffException.InvalidInputException("YOU NEED A DESCRIPTION");
            }
        } catch (JeffException.InvalidInputException e) {
            throw new IllegalArgumentException("ERROR IN THE INPUT STRING");
        }
    }


    /**
     * Returns only the first time value provided by the user indicated by a "/".
     *
     * @param userInput Raw user input.
     * @return String for the starting time of an event.
     */
    public static String extractStartTime(String userInput){
        String[] words = userInput.split(" ");
        String startTime = null;

        for (String word : words) {
            if (word.startsWith("/")) {
                startTime = word;
                break;
            }
        }
        return startTime.replace("/", "");
    }


    /**
     * Returns only the last time value provided by the user indicated by a "/".
     *
     * @param userInput Raw user input.
     * @return String for the ending time of an event.
     */
    public static String extractEndTime(String userInput) {
        String[] words = userInput.split(" ");
        int importantPartsFound = 0;
        String endTime = null;

        for (String word : words) {
            if (word.startsWith("/")) {
                importantPartsFound++;
                if (importantPartsFound == 2) {
                    endTime = word;
                    break;
                }
            }
        }
        return endTime.replace("/", "");
    }
}
