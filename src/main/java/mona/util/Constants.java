package mona.util;

public final class Constants {

    // Private constructor to prevent instantiation
    private Constants() {
    }

    // These are the prefix strings to define the various data types of a command parameter.
    public static final String TODO_PREFIX = "todo";
    public static final String MARK_PREFIX = "mark";
    public static final String UNMARK_PREFIX = "unmark";
    public static final String DEADLINE_PREFIX = "deadline";
    public static final String EVENT_PREFIX = "event";
    public static final String LIST_PREFIX = "list";


    public static final String BY_PREFIX = "/by";
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";

    /* Given that we use a String array (of 4 elements) to store details of a command,
     * the constants given below are the indexes for the different data elements of a command.
     * For example, the command type (eg. list, mark, unmark etc.) is stored as the 0th element
     * in the String array.
     */
    public static final int INDEX_COMMAND_TYPE = 0;
    public static final int INDEX_DESCRIPTION = 1;
    public static final int INDEX_DEADLINE = 2;
    public static final int INDEX_FROM_DATE = 2;
    public static final int INDEX_TO_DATE = 3;

    public static final String ASCII_LOGO =
            "___  ___                  \n"
            + "|  \\/  |                  \n"
            + "| .  . | ___  _ __   __ _ \n"
            + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
            + "| |  | | (_) | | | | (_| |\n"
            + "\\_|  |_/\\___/|_| |_|\\__,_|\n";

    public static final String DATA_FILE_PATH = "data/tasks.txt";
    public static final String DATA_FOLDER_PATH = "./data/";
    public static final String DATA_FILE_NAME = "tasks.txt";
}
