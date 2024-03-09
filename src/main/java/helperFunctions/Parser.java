package helperFunctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Process User Input and calls appropriate TaskList function
     *
     * @param tasks      TaskList object to hold many tasks
     * @param userInput  from CLI or from FILE_NAME
     * @param FILE_NAME  to store tasks in
     * @param isReadMode specifies if reading from FILE_NAME or writing to it
     * @return true to continue asking for user input; false to stop
     * @throws InvalidParamsException if invalid command
     */
    public static boolean processUserInput(TaskList tasks, String userInput, String FILE_NAME, boolean isReadMode) throws InvalidParamsException {
        final int COMMAND_INDEX = 0;
        boolean isMark;

        String[] userInputInParts = userInput.split(" ");
        switch (userInputInParts[COMMAND_INDEX].toUpperCase()) {
        case "BYE":
            return false; // EXITS loop
        case "LIST":
            System.out.print(tasks.displayList());
            break;
        case "MARK":
            isMark = true;
            tasks.markOperation(userInputInParts, isMark, FILE_NAME, isReadMode);
            break;
        case "UNMARK":
            isMark = false;
            tasks.markOperation(userInputInParts, isMark, FILE_NAME, isReadMode);
            break;
        case "DELETE":
            tasks.deleteOperation(userInputInParts, FILE_NAME);
            break;
        case "FIND":
            tasks.findOperation(userInputInParts);
            break;
        case "TODO":
            tasks.addTodoTask(userInputInParts, userInput, FILE_NAME, isReadMode); // change tasks to ArrayList<TAsk>, f void now
            break;
        case "DEADLINE":
            tasks.addDeadlineTask(userInputInParts, userInput, FILE_NAME, isReadMode);
            break;
        case "EVENT":
            tasks.addEventTask(userInputInParts, userInput, FILE_NAME, isReadMode);
            break;
        default:
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
