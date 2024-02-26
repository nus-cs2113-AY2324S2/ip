import Exceptions.CorruptedFileException;
import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {
    protected String userInput;

    public Parser() {
    }

    // no error checking for processing task information has been implemented (e.g. deadline but no /by)
    public static String[] processTaskInformation(String userInput) throws TodoLackInputsException, DeadlineLackInputsException, EventLackInputsException {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String[] output = new String[4];
        switch (wordArray[0]) {
        case ("todo"):
            output[0] = "todo";
            output[1] = userInput.substring(4).trim();
            if (output[1].trim().isEmpty()) {
                throw new TodoLackInputsException();
            }
            break;

        case ("deadline"):
            output[0] = "deadline";
            int byIndex = userInput.indexOf("/by");
            output[1] = userInput.substring(8, byIndex).trim();
            output[2] = userInput.substring(byIndex + 4).trim();
            try {
                LocalDate deadline = LocalDate.parse(output[2]);
                output[2] = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("You could try inputting your deadline in a yyyy-mm-dd format for it to read neater. =)");
            }

            if (output[1].trim().isEmpty() || output[2].trim().isEmpty()) {
                throw new DeadlineLackInputsException();
            }
            break;

        case ("event"):
            output[0] = "event";
            int fromIndex = userInput.indexOf("/from");
            output[1] = userInput.substring(5, fromIndex).trim();
            int toIndex = userInput.indexOf("/to");
            output[2] = userInput.substring(fromIndex + 6, toIndex).trim();
            output[3] = userInput.substring(toIndex + 4).trim();
            try {
                LocalDate startDate = LocalDate.parse(output[2]);
                LocalDate endDate = LocalDate.parse(output[3]);
                output[2] = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                output[3] = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("You could try inputting your start date and end date in a yyyy-mm-dd format " +
                        "for it to read neater. =)");
            }
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty()) {
                throw new EventLackInputsException();
            }
            break;

        default:
            output[0] = "error";
        }
        return output;
    }

    public static int processTaskIdforMarkingAndDeletingTask(String userInput) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        return Integer.parseInt(wordArray[1]) - 1;
    }

    public static String[] processTaskLoadingData(String data) throws CorruptedFileException {
        String[] wordArray = data.split(",");
        String[] output = new String[5];
        switch (wordArray[0]) {
        case ("T"):
            output[0] = "todo";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        case ("D"):
            output[0] = "deadline";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            output[3] = wordArray[3];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        case ("E"):
            output[0] = "event";
            output[1] = wordArray[1];
            output[2] = wordArray[2];
            output[3] = wordArray[3];
            output[4] = wordArray[4];
            if (output[1].trim().isEmpty() || output[2].trim().isEmpty() || output[3].trim().isEmpty() ||
                    output[4].trim().isEmpty()) {
                throw new CorruptedFileException();
            }
            break;

        default:
            output[0] = "error";
            throw new CorruptedFileException();
        }
        return output;
    }

}
