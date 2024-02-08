public class InputParser {
    // These are the prefix strings to define the various data types of a command parameter.
    private static final String DEADLINE_PREFIX = "/by";
    private static final String FROM_DATE_PREFIX = "/from";
    private static final String TO_DATE_PREFIX = "/to";

    /* We use a String array (of 4 elements) to store details of a command.
     * The constants given below are the indexes for the different data elements of a command.
     * For example, the task type is stored as the 0th element in the String array.
     */
    private static final int INDEX_COMMAND_TYPE = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_DEADLINE = 2;
    private static final int INDEX_FROM_DATE = 2;
    private static final int INDEX_TO_DATE = 3;

    protected String[] commandTypeAndParams;
    protected String line;
    public InputParser(String line) {
        this.commandTypeAndParams = new String[4];
        this.line = line;
        this.parseInput(line);
    }

    public void extractCommandTypeFromString(String line) {
        this.commandTypeAndParams[INDEX_COMMAND_TYPE] = line.split(" ")[0].trim();
    }
    public void extractDetailsFromMarkUnmarkString(String line) {
        int descriptionIndex = line.indexOf(" ");

        this.commandTypeAndParams[INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();
    }
    public void extractDetailsFromTodoString(String line) {
        int descriptionIndex = line.indexOf(" ");

        this.commandTypeAndParams[INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();
    }
    public void extractDetailsFromDeadlineString(String line) {
        int descriptionIndex = line.indexOf(" ");
        int deadlineIndex = line.indexOf(DEADLINE_PREFIX);

        this.commandTypeAndParams[INDEX_DESCRIPTION] = line.substring(descriptionIndex, deadlineIndex).trim();
        this.commandTypeAndParams[INDEX_DEADLINE] = line.substring(deadlineIndex
                + DEADLINE_PREFIX.length()).trim();
    }
    public void extractDetailsFromEventString(String line) {
        int descriptionIndex = line.indexOf(" ");
        int fromIndex = line.indexOf(FROM_DATE_PREFIX);
        int toIndex = line.indexOf(TO_DATE_PREFIX);

        this.commandTypeAndParams[INDEX_DESCRIPTION] = line.substring(descriptionIndex, fromIndex).trim();
        this.commandTypeAndParams[INDEX_FROM_DATE] = line.substring(fromIndex+ FROM_DATE_PREFIX.length(),
                toIndex).trim();
        this.commandTypeAndParams[INDEX_TO_DATE] = line.substring(toIndex + TO_DATE_PREFIX.length()).trim();
    }
    public void parseInput(String line) {
        extractCommandTypeFromString(line);
        String commandType = commandTypeAndParams[INDEX_COMMAND_TYPE];

        switch(commandType) {
        case ("mark"):
            //fallthrough
        case ("unmark"):
            extractDetailsFromMarkUnmarkString(line);
            break;
        case ("todo"):
            extractDetailsFromTodoString(line);
            break;
        case ("deadline"):
            extractDetailsFromDeadlineString(line);
            break;
        case ("event"):
            extractDetailsFromEventString(line);
            break;
        }
    }
}
