import java.util.Random;

public class Message {
    public static final String DELIMITER = "\n>====> >====> >====> >====> >====> >====> >====> >====> >====>\n";

    private static final String LOGO_DEFAULT = "              _                 \n"
            + "     /\\      | |                \n"
            + "    /  \\   __| | __ _ _ __ ___  \n"
            + "   / /\\ \\ / _` |/ _` | '_ ` _ \\ \n"
            + "  / ____ \\ (_| | (_| | | | | | |\n"
            + " /_/    \\_\\__,_|\\__,_|_| |_| |_|\n";

    private static final String LOGO_BOLD = " █████╗ ██████╗  █████╗ ███╗   ███╗\n"
            + "██╔══██╗██╔══██╗██╔══██╗████╗ ████║\n"
            + "███████║██║  ██║███████║██╔████╔██║\n"
            + "██╔══██║██║  ██║██╔══██║██║╚██╔╝██║\n"
            + "██║  ██║██████╔╝██║  ██║██║ ╚═╝ ██║\n"
            + "╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝\n";

    private static final String LOGO_3D = " ________  ________  ________  _____ ______      \n"
            + "|\\   __  \\|\\   ___ \\|\\   __  \\|\\   _ \\  _   \\    \n"
            + "\\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\   \n"
            + " \\ \\   __  \\ \\  \\ \\\\ \\ \\   __  \\ \\  \\\\|__| \\  \\  \n"
            + "  \\ \\  \\ \\  \\ \\  \\_\\\\ \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \n"
            + "   \\ \\__\\ \\__\\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\\n"
            + "    \\|__|\\|__|\\|_______|\\|__|\\|__|\\|__|     \\|__|\n";

    private static final String GREETING = DELIMITER
            + "Greetings, human! I'm Adam, your friendly chat bot.\n"
            + "Ready to conquer the world of tasks together?\n"
            + "Let's dive into the adventure!\n"
            + DELIMITER;

    private static final String[] LOGOS = {LOGO_DEFAULT, LOGO_BOLD, LOGO_3D};

    // Random logo display at app start
    public static final String GREETING_MESSAGE = "Hello from\n" + LOGOS[new Random().nextInt(LOGOS.length)]
            + GREETING;

    public static final String EXIT_MESSAGE = "Farewell, adventurer! Until our paths cross again!\n";

    public static final String MARK_MESSAGE = "Ta-da! Task conquered! I've marked this task as done:\n";

    public static final String UNMARK_MESSAGE = "Whoopsie-daisy! This task is back in action:\n";

    public static final String ERROR_MESSAGE = "Oopsie! Looks like I got tangled up in my circuits.\n"
            + "Could you please try again?";

    public static final String ADD_TASK_MESSAGE_FRONT = "Awesome sauce! Task successfully added:\n  ";

    public static final String ADD_TASK_MESSAGE_MIDDLE = "\nYou now have a whopping ";

    public static final String ADD_TASK_MESSAGE_END = " tasks in the list. Keep 'em coming!";

    public static final String LIST_MESSAGE_FRONT = "Behold, the mighty task(s) in your realm:\n";

    public static final String LIST_MESSAGE_END = "\nThese tasks await your valiant efforts, noble taskmaster!";
}
