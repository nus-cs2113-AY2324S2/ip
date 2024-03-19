/**
 * Enum representing different types of commands that Mavis can process.
 */
public enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    BYE;

    /**
     * Converts a string representation of a command type to the corresponding enum value.
     *
     * @param str The string representation of the command type.
     * @return The corresponding CommandType enum value.
     * @throws IllegalArgumentException If the input string does not match any known command type.
     */
    public static CommandType fromString(String str) {
        try {
            switch (str.toLowerCase()) {
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "list":
                return LIST;
            case "mark":
                return MARK;
            case "unmark":
                return UNMARK;
            case "delete":
                return DELETE;
            case "find":
                return FIND;
            case "bye":
                return BYE;
            default:
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            MavisException.handleException(e, str);
        }
        return null;
    }
}
