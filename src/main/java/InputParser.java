public class InputParser {
    protected String[] commandTypeAndParams;
    protected String line;
    public InputParser(String line) {
        this.commandTypeAndParams = new String[4];
        this.line = line;
        this.parseInput(line);
    }

    public void extractCommandTypeFromString(String line) {
        this.commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = line.split(" ")[0].trim();
    }
    public void extractDetailsFromMarkUnmarkString(String line) {
        int descriptionIndex = line.indexOf(" ");

        this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();
    }
    public void extractDetailsFromTodoString(String line) {
        int descriptionIndex = line.indexOf(" ");

        this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();
    }
    public void extractDetailsFromDeadlineString(String line) {
        int descriptionIndex = line.indexOf(" ");
        int deadlineIndex = line.indexOf(Constants.BY_PREFIX);

        this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex, deadlineIndex).trim();
        this.commandTypeAndParams[Constants.INDEX_DEADLINE] = line.substring(deadlineIndex
                + Constants.BY_PREFIX.length()).trim();
    }
    public void extractDetailsFromEventString(String line) {
        int descriptionIndex = line.indexOf(" ");
        int fromIndex = line.indexOf(Constants.FROM_PREFIX);
        int toIndex = line.indexOf(Constants.TO_PREFIX);

        this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex, fromIndex).trim();
        this.commandTypeAndParams[Constants.INDEX_FROM_DATE] = line.substring(fromIndex
                        + Constants.FROM_PREFIX.length(), toIndex).trim();
        this.commandTypeAndParams[Constants.INDEX_TO_DATE] = line.substring(toIndex
                + Constants.TO_PREFIX.length()).trim();
    }
    public void parseInput(String line) {
        extractCommandTypeFromString(line);
        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

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
