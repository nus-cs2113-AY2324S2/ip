package ui;

import tasks.Task;

public final class Constants {
    public static final String LINE =  "    __________________________________________________________\n";

    public static final String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
                    "    the heir of Isildur Elendil's son of Gondor.\n" +
                    "    What can I do for you?\n";
    public static final String EXIT = "    Farewell. May we meet again!\n";
    public static final String TAB = "    ";
    public static final String COMMANDLIST = "    Here is a list of commands:\n" +
                    "\n" +
                    "    \"list\": Displays list of tasks.\n" +
                    "\n" +
                    "    \"todo <description>\": Adds a Todo task to the list.\n" +
                    "\n" +
                    "    \"deadline <description> /by <deadline>\": Adds a task and its deadline to the list.\n" +
                    "\n" +
                    "    \"event <description> /from <start> /to <end>\": Adds an event and its start and end conditions to the list.\n" +
                    "\n" +
                    "    \"mark <task number>\": Marks the corresponding task in the list as completed.\n" +
                    "\n" +
                    "    \"unmark <task number>\": Marks the corresponding task in the list as incomplete.\n" +
                    "\n" +
                    "    \"delete <task number>\": Removes the corresponding task from the list.\n" +
                    "\n" +
                    "    \"find <keyword>\": Displays the tasks containing the keyword.\n" +
                    "\n" +
                    "    \"help\": Displays this list of commands.\n" +
                    "\n" +
                    "    \"bye\": Closes the program.\n";
    public static final String FILEREADERROR = LINE + "    File read error\n" + LINE;
    public static final String FILEERROR = LINE + "    File Error!\n" + LINE ;
    public static final String FILEWRITEERROR = "Error writing file: ";
    public static final String CREATEDIRECTORYERROR = "Unable to create directory: ";
    public static final String ALREADYUNMARKED = LINE + "    This task has already been unmarked.\n" + LINE;
    public static final String ALREADYMARKED = LINE + "    This task has already been marked.\n" + LINE;
    public static final String ADDEDTASK = LINE + "    Got it. I've added this task:";
    public static final String INVALIDFORMAT = LINE + "    Invalid format!\n" + LINE;
    public static final String NOFILE = "No file found! Creating new file.";
    public static final String FILEPATH = "./ip/data/AragornList.txt";
    public static final String CURRENTLIST = "    Here are the tasks in your list: ";
    public static final String EMPTYLIST = LINE + "    List is empty. Add tasks to view them here.\n" + LINE;
    public static final String EMPTYINPUT = LINE + "    Input is empty. Use \"/help\" command to view the list of commands\n" + LINE;
    public static final String INVALIDINPUT = LINE + "    Your input is invalid. Use the \"/help\" command to view the list of commands.\n" + LINE;
    public static final String HELLOMESSAGE =LINE + GREET + LINE + "\n" + COMMANDLIST + LINE;
    public static final String HELPMESSAGE = LINE + COMMANDLIST + LINE;
    public static final String BYEMESSAGE = LINE + TAB + EXIT + LINE;
    public static final String TASKTYPEERROR = LINE + "    Task type error: ";
    public static final String INVALIDINDEXFORMAT = LINE + "    Invalid format\n" + LINE;
    public static final String INVALIDINDEX = LINE + "    Task index is not in the list\n" + LINE;
    public static final String INVALIDTASK = LINE + "    Invalid Task\n" + LINE;
    public static final String DELETETASK = LINE + TAB + "I've deleted this task from the list:\n" + TAB + "   ";
    public static final String MARKTASK = LINE + TAB + "Nice! I've marked this task as done:\n" + TAB + "   ";
    public static final String UNMARKTASK = LINE + TAB + "OK, I've marked this task as incomplete:\n" + TAB + "   ";
    public static final String NEWLINE = "\n";
    public static final String COMPLETE = "X";
    public static final String INCOMPLETE = " ";
    public static final String TODO = "TODO";
    public static final String DEADLINE = "DEADLINE";
    public static final String EVENT = "EVENT";
    public static final String LIST = "LIST";
    public static final String MARK = "MARK";
    public static final String UNMARK = "UNMARK";
    public static final String DELETE = "DELETE";
    public static final String FIND = "FIND";
    public static final String HELP = "HELP";
    public static final String BYE = "BYE";
    public static final String INVALID = "INVALID";
    public static final String DOT = ". ";
    public static final String ONE = "1";
    public static final String BAR = "\\|";
    public static final String BYREGEX = "/by";
    public static final String FROMREGEX = "/from";
    public static final String TOREGEX = "/to";
    public static final String EMPTYDESCRIPTION = LINE + "    Task description is empty!\n" + LINE;
    public static final String EMPTYDEADLINE = LINE + "    Deadline condition is empty!\n" + LINE;
    public static final String EMPTYEVENTSTART = LINE + "    Start condition is empty!\n" + LINE;
    public static final String EMPTYEVENTEND = LINE + "    End condition is empty!\n" + LINE;
    public static final String EMPTYFIND = LINE + "    No matched found. :(\n" + LINE;
    public static final String FINDTASK = " matching tasks found!";

    public static void printRemainingTasks(int remainingTasks, int size) {
        System.out.println("    You have " + remainingTasks + " / " + size + " remaining tasks in the list.\n" + LINE);
    }
    public static void printAddTask(Task list) {
        System.out.println(ADDEDTASK);
        System.out.println(TAB + list.taskString() + NEWLINE);
    }
    public static Task taskTypeError(String taskType) {
        System.out.println(TASKTYPEERROR + taskType);
        return null;
    }
}
