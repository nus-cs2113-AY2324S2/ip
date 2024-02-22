import java.io.File;

public class Constant {

    // Used in CommandHandling.java
    public static final int MAX_TASKS = 100;
    public static final int MARK_OFFSET = 4;
    public static final int UNMARK_OFFSET = 6;
    public static final int TODO_OFFSET = 4;
    public static final int EVENT_OFFSET = 5;
    public static final int DEADLINE_OFFSET = 8;
    public static final int DELETE_OFFSET = 6;
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

}
