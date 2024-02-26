package natsu.command;

/**
 * Contains constants used for parsing and handling commands within the application.
 * This includes lengths for command strings to help with parsing user input and
 * specific indicators for parsing command arguments.
 */
public class CommandConstants {

    public static final int MARK_COMMAND_LENGTH = 5;
    public static final int UNMARK_COMMAND_LENGTH = 7;
    public static final int TODO_COMMAND_LENGTH = 5;
    public static final int DEADLINE_COMMAND_PREFIX_LENGTH = 9;
    public static final int EVENT_COMMAND_PREFIX_LENGTH = 6;
    public static final int DELETE_COMMAND_LENGTH = 7;
    public static final String DEADLINE_INDICATOR = "/by";
    public static final String EVENT_START_INDICATOR = "/from";
    public static final String EVENT_END_INDICATOR = "/to";
}
