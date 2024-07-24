package userInterface;

public class Message {
    public static final String LOGO
            = "      ___           ___           ___                       ___     \n" +
            "     /\\  \\         /\\__\\         /\\  \\          ___        /\\  \\    \n" +
            "    /::\\  \\       /:/  /        /::\\  \\        /\\  \\      /::\\  \\   \n" +
            "   /:/\\:\\  \\     /:/__/        /:/\\:\\  \\       \\:\\  \\    /:/\\ \\  \\  \n" +
            "  /:/  \\:\\  \\   /::\\  \\ ___   /::\\~\\:\\  \\      /::\\__\\  _\\:\\~\\ \\  \\ \n" +
            " /:/__/ \\:\\__\\ /:/\\:\\  /\\__\\ /:/\\:\\ \\:\\__\\  __/:/\\/__/ /\\ \\:\\ \\ \\__\\\n" +
            " \\:\\  \\  \\/__/ \\/__\\:\\/:/  / \\/_|::\\/:/  / /\\/:/  /    \\:\\ \\:\\ \\/__/\n" +
            "  \\:\\  \\            \\::/  /     |:|::/  /  \\::/__/      \\:\\ \\:\\__\\  \n" +
            "   \\:\\  \\           /:/  /      |:|\\/__/    \\:\\__\\       \\:\\/:/  /  \n" +
            "    \\:\\__\\         /:/  /       |:|  |       \\/__/        \\::/  /   \n" +
            "     \\/__/         \\/__/         \\|__|                     \\/__/    ";
    public static final String DASH
            = "____________________________________________________________\n";

    public static final String GREETING
            = DASH + "Hello, I'm Chris\n" + "What can I do for you?\n" + DASH;

    public static final String FAREWELL
            = DASH + "Bye. Hope to see you again soon!\n" + DASH;

    public static final String LIST_OUTPUT_FRONT
            = DASH + "Here are the tasks in your list";

    public static final String ADD_TASK_OUTPUT_FRONT
            = DASH + "Got it. I've added this task:";

    public static final String FIND_OUTPUT_FRONT
            = DASH + "Here are the matching tasks in your list:";

    public static final String NO_RESULT
            = DASH + "Nothing found";

    public static final String MARK_TASK_FRONT
            = DASH + "Nice! I've marked this task as done:";

    public static final String UNMARK_TASK_FRONT
            = DASH + "OK, I've marked this task as not done yet:";

    public static final String DELETE_TASK_FRONT
            = DASH + "Noted. I've removed this task:";
}
