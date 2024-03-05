public enum CommandType {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE;

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
