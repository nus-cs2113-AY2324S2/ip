import java.io.File;
/**
 * Represents all constant variables used in the Jarvas bot.
 */
public class Constant {

    // Used in CommandHandling.java
    public static final int MAX_TASKS = 100;
    public static final int MARK_OFFSET = 4;
    public static final int UNMARK_OFFSET = 6;
    public static final int TODO_OFFSET = 4;
    public static final int EVENT_OFFSET = 5;
    public static final int DEADLINE_OFFSET = 8;
    public static final int DELETE_OFFSET = 6;
    public static final int FIND_OFFSET = 4;
    public static final int ARRAY_START_INDEX = 0;

    // Used in Persistence.java
    public static final String FILE_NAME = "saveFile.txt";
    public static final File SAVE_FILE = new File(Constant.FILE_NAME);

    // Used in Event.java
    public static final int EVENT_PARAMETERS = 3;
    public static final int EVENT_FROM_OFFSET = 6;
    public static final int EVENT_TO_OFFSET = 3;

    // Used in Deadline.java
    public static final int DEADLINE_PARAMETERS = 2;
    public static final int DEADLINE_BY_OFFSET = 3;


    // Formatted Replies
    public static final String PARTITION_LINE = "____________________________________________________________";
    // Input Errors
    public static final String INVALID_COMMAND = "Invalid command. Enter 'help' to view available commands.";
    public static final String UNSPECIFIED_PARAMETER = "Parameter is unspecified.";
    public static final String INVALID_PARAMETER = "Parameter is invalid and out of bounds";
    // List Errors
    public static final String EMPTY_LIST = "List is empty.";
    public static final String NO_RESULTS = "There are no results that match your search query.";
    // Storage Replies
    public static final String SAVE_ERROR = "File save failed.\nWrite error occurred:\n";
    public static final String MISSING_FILE = "Data file not found/corrupted. Starting with an empty list.";
    public static final String LOAD_ERROR = "File read error:\n" + "Error at task number = ";
    public static final String CORRUPT_ERROR = "\nFile is corrupted. Ceasing any further data imports.";
    public static final String SUCCESSFUL_LOAD = "Prior data file found\n" + "Previous data has been imported.";
}
