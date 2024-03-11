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

    public static final String DATE_TIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final String DATE_TIME_OUTPUT_FORMAT = "MMM dd YYYY, hh:mm a";

    // Thees are the error messages printed when the user enters an invalid command, or when there's error in
    // loading from the storage file.
    public static final String INVALID_COMMAND_ERROR_MESSAGE = "Hmm, Mona didn't quite catch that. " +
            "Could you try again? Or type 'help' to see what Mona can do!";
    public static final String EMPTY_FIELDS_ERROR_MESSAGE = "Oh no, looks like something's missing. " +
            "Mona needs all the pieces to help you out!";
    public static final String MISSING_BY_PREFIX_IN_DEADLINE_ERROR_MESSAGE = "Mona needs to know when! " +
            "Please add '/by' followed by the deadline so Mona can remind you in time!";
    public static final String IMPROPER_FLAGS_IN_EVENT_ERROR = "Hmm, Mona's a bit confused. " +
            "Make sure to include both '/from' and '/to' flags " +
            "in the right order to tell Mona when the event starts and ends!";
    public static final String INVALID_NUMBER_ERROR_MESSAGE = "Whoops! Mona's looking for a number. " +
            "Mind giving it another go?";
    public static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Mona's scratching her head. " +
            "She can't find a task with that number! " +
            "Perhaps you could double-check the task index using the 'list' command and try again?";
    public static final String ERROR_LOADING_FILE = "Uh-oh! Mona thinks the saved .txt file might be could be " +
            "corrupted. So, we'll need to stop here. Please peek at the 'Editing the data file' section on the " +
            "User Guide at https://nur-haziq.github.io/ip/ for how to fix this. " +
            "Mona's going to take a little break until then!";

    public static final String HELP_MESSAGE = "Need some help? Mona's got you covered! Here's what I can do: " +
            System.lineSeparator() + System.lineSeparator() +

            "1. todo DESCRIPTION - Add a new to-do task." + System.lineSeparator() +
            "2. deadline DESCRIPTION /by DATE - Add a task with a deadline." + System.lineSeparator() +
            "3. event DESCRIPTION /from DATE /to DATE - Add an event with its start and end date." +
            System.lineSeparator() +
            "4. list - Display all your tasks." + System.lineSeparator() +
            "5. mark INDEX - Mark a task as completed using its list index." + System.lineSeparator() +
            "6. unmark INDEX - Mark a task as not completed using its list index." + System.lineSeparator() +
            "7. delete INDEX - Remove a task using its list index." + System.lineSeparator() +
            "8. find KEYWORD - Search for tasks containing a specific word." + System.lineSeparator() +
            "9. help - Show this list of commands again." + System.lineSeparator() +
            "10. bye - Exit the application." + System.lineSeparator() +
            System.lineSeparator() +

            "Use the commands exactly as shown. Words in ALL CAPS are for you to fill in with your details. " +
            "Let's get things done together!";
    public static final String ADD_TASK_MESSAGE = "Got it! I've added that task for you.";
    public static final String DELETE_TASK_MESSAGE = "Consider it gone! That task is deleted.";
    public static final String MARK_TASK_MESSAGE = "Woohoo! That task is now marked as done.";
    public static final String UNMARK_TASK_MESSAGE = "No worries! Mona has marked that task as not done. " +
            "There's always next time!";
    public static final String LIST_TASKS_MESSAGE = "Here's all the tasks you've got lined up:";
    public static final String TASKS_FOUND_MESSAGE = "Here's what I found! Ready to take a look?";
    public static final String NO_TASKS_FOUND_MESSAGE = "Hmm, I couldn't find any tasks that match the keyword '%s'. " +
            "Maybe try a different word?";
    public static final String EXIT_MESSAGE = "Bye-bye! Mona will miss you. Come back soon!";
}
