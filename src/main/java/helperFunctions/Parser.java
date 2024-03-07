package helperFunctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Process User Input and calls appropriate TaskList function
     *
     * @param tasks TaskList object to hold many tasks
     * @param userInput from CLI or from FILE_NAME
     * @param FILE_NAME to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @return true to continue asking for user input; false to stop
     * @throws InvalidParamsException if invalid command
     */
    public static boolean processUserInput(TaskList tasks, String userInput, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        String[] userInputInParts = userInput.split(" ");

        if (userInputInParts[0].equalsIgnoreCase("BYE")) {
            return false; // EXITS loop
        }
        if (userInputInParts[0].equalsIgnoreCase("LIST")) {
            System.out.print(tasks.displayList());
        } else if (userInputInParts[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !userInput.toUpperCase().contains("UNMARK");
            tasks.markOperation(userInputInParts, isMark, FILE_NAME, isReadMode);
        } else if (userInputInParts[0].equalsIgnoreCase("DELETE")) {
            tasks.deleteOperation(userInputInParts, FILE_NAME);
        } else if (userInputInParts[0].equalsIgnoreCase("FIND")) {
            tasks.findOperation(userInputInParts);
        } else if (userInputInParts[0].equalsIgnoreCase("TODO")) {
            tasks.addTodoTask(userInputInParts, userInput, FILE_NAME, isReadMode); // change tasks to ArrayList<TAsk>, f void now
        } else if (userInputInParts[0].equalsIgnoreCase("DEADLINE")) {
            tasks.addDeadlineTask(userInputInParts, userInput, FILE_NAME, isReadMode);
        } else if (userInputInParts[0].equalsIgnoreCase("EVENT")) {
            tasks.addEventTask(userInputInParts, userInput, FILE_NAME, isReadMode);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + Ui.printCommandsList());
        }
        return true;
    }

    /**
     * Takes in deadline of "yyyy/mm/dd" and formats to "MMM d yyyy"
     *
     * @param deadline is unformatted
     * @return deadline of format "MMM d yyyy"
     */
    public static String formatDeadline(String deadline) {
        String formattedDeadline = deadline.replaceAll("/", "-").trim();
        LocalDate dateObject = LocalDate.parse(formattedDeadline);
        return dateObject.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Checks for valid deadline format "yyyy/mm/dd"
     *
     * @param deadline of unknown format
     * @return true if valid, else false
     */
    public static boolean isValidDeadline(String deadline) {
        int LENGTH_OF_DEADLINE_COMPONENTS = 3;
        int LENGTH_OF_YEAR = 4;
        int LENGTH_OF_MONTHS = 2;
        int LENGTH_OF_DAYS = 2;

        String formattedDeadline = deadline.replaceAll("/", "-").trim();
        String[] deadlineParts = formattedDeadline.split("-");
        return ((deadlineParts.length == LENGTH_OF_DEADLINE_COMPONENTS)
                && (deadlineParts[0].length() == LENGTH_OF_YEAR)
                && (deadlineParts[1].length() == LENGTH_OF_MONTHS)
                && (deadlineParts[2].length() == LENGTH_OF_DAYS));
    }
}
