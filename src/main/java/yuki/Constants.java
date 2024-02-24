package yuki;

public class Constants {
    protected static final int LENGTH_TODO_COMMAND = 4;
    protected static final int LENGTH_DEADLINE_COMMAND = 8;
    protected static final int LENGTH_EVENT_COMMAND = 5;

    protected static final String LIST_COMMAND = "list";
    protected static final String MARK_COMMAND = "mark";
    protected static final String UNMARK_COMMAND = "unmark";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String TODO_COMMAND = "todo";
    protected static final String DEADLINE_COMMAND = "deadline";
    protected static final String EVENT_COMMAND = "event";
    protected static final String HELP_COMMAND = "help";
    protected static final String EXIT_COMMAND = "exit";

    public static final char TODO_INDICATOR = 'T';
    public static final char DEADLINE_INDICATOR = 'D';
    public static final char EVENT_INDICATOR = 'E';

    public static final int TASK_INDICATOR_INDEX = 7;
    public static final int TASK_DESCRIPTION_INDEX = 10;
}
