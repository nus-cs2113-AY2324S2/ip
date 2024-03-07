package allez;

/**
 * Verify if inputs entered by user is in the correct format.
 */
public class Parser {

    /**
     * Parse line in the context of adding an Event.
     *
     * @param line input given by user
     * @return a String array containing "description", "from" and "to" of the deadline.
     * @throws MissingDetailsException /description, /from or /to is not specified by user
     */
    public static String[] parseEventInput(String line) throws MissingDetailsException {
        String[] checkFrom = line.split(" /from ", 2);
        String[] checkTo = checkFrom[1].split(" /to ", 2);
        String[] lineSegment = new String[3];
        lineSegment[0] = checkFrom[0].substring(5).trim();
        lineSegment[1] = checkTo[0].trim();
        lineSegment[2] = checkTo[1].trim();
        if(lineSegment[0].isBlank() || lineSegment[1].isBlank() || lineSegment[2].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    /**
     * Parse line in the context of adding a Deadline.
     *
     * @param line input given by user
     * @return a String array containing "description" and "by"
     * @throws MissingDetailsException /description and /by is not specified by user
     */
    public static String[] parseDeadlineInput(String line) throws MissingDetailsException {
        String[] lineSegment = line.substring(9).split(" /by ", 2);
        lineSegment[0] = lineSegment[0].trim();
        lineSegment[1] = lineSegment[1].trim();
        if (lineSegment[0].isBlank() || lineSegment[1].isBlank()) {
            throw new MissingDetailsException();
        }
        return lineSegment;
    }

    /**
     * Parse currentInput and obtain the first word, which acts as the command.
     *
     * @param currentInput line entered by user
     * @return first word of the line
     */
    public String parseCommand(String currentInput) {
        return currentInput.split(" ", 2)[0];
    }

    /**
     * Parse line and obtain the task number to be marked.
     *
     * @param line entered by user
     * @return the task number to mark
     */
    public static int parseMarkTask(String line) {
        return Integer.parseInt(line.substring(4).trim()) -1;
    }

    /**
     * Parse line and obtain the task number to be deleted.
     *
     * @param line entered by user
     * @return the task number to delete
     */
    public static int parseDelete(String line) {
        return Integer.parseInt(line.substring(6).trim()) -1;
    }



}
