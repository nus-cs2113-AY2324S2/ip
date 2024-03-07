package helperFunctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static boolean processUserInput(TaskList tasks, String userInput, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        String[] req = userInput.split(" ");

        if (req[0].equalsIgnoreCase("BYE")) {
            return false; // EXITS loop
        }
        if (req[0].equalsIgnoreCase("LIST")) {
            System.out.print(tasks.displayList());
        } else if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !userInput.toUpperCase().contains("UNMARK");
            tasks.markOperation(req, isMark, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("DELETE")) {
            tasks.deleteOperation(req, FILE_PATH);
        } else if (req[0].equalsIgnoreCase("FIND")) {
            tasks.findOperation(req, FILE_PATH);
        } else if (req[0].equalsIgnoreCase("TODO")) {
            tasks.addTodoTask(req, userInput, FILE_PATH, isReadMode); // change tasks to ArrayList<TAsk>, f void now
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            tasks.addDeadlineTask(req, userInput, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            tasks.addEventTask(req, userInput, FILE_PATH, isReadMode);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + Ui.printCommandsList());
        }
        return true;
    }

    public static String formatDeadline(String deadline) {
        String formattedDeadline = deadline.replaceAll("/", "-").trim();
        LocalDate dateObject = LocalDate.parse(formattedDeadline);
        return dateObject.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    // Checks for format "yyyy-mm-dd"
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
