package ui;

import java.util.Random;

public class Message {
    public static final String DELIMITER = "\n>====> >====> >====> >====> >====> >====> >====> >====> >====> >====>\n";

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
            + "Let's dive into the adventure!\n\n"
            + ">>> Type 'help' or 'h' to view the available commands."
            + DELIMITER;

    private static final String[] LOGOS = {LOGO_DEFAULT, LOGO_BOLD, LOGO_3D};

    // Random logo display at app start
    public static final String GREETING_MESSAGE = "Hello from\n" + LOGOS[new Random().nextInt(LOGOS.length)]
            + GREETING;

    public static final String EXIT_MESSAGE = "Farewell, adventurer! Until our paths cross again!\n";

    public static final String MARK_MESSAGE = "Ta-da! Task conquered! I've marked this task as done:\n";

    public static final String UNMARK_MESSAGE = "Whoopsie-daisy! This task is back in action:\n";

    public static final String ADD_TASK_MESSAGE_FRONT = "Awesome sauce! Task successfully added:\n  ";

    public static final String ADD_TASK_MESSAGE_MIDDLE = "\nYou now have a whopping ";

    public static final String ADD_TASK_MESSAGE_END = " tasks in the list. Keep 'em coming!";

    public static final String LIST_MESSAGE_FRONT = "Behold, the mighty task(s) in your realm:\n";

    public static final String LIST_MESSAGE_END = "\nThese tasks await your valiant efforts, noble taskmaster!";

    public static final String EMPTY_LIST_ERROR_MESSAGE = "Looks like our task list is empty.\n"
            + "Time to fill it up with some action-packed tasks!";

    private static final String RANGE_ERROR_MESSAGE = "Oops! The specified index is out of range.\n"
            + "Please enter an index between 1 and ";

    public static final String INVALID_INPUT_MESSAGE = "Oopsie! Looks like I got tangled up in my circuits.\n"
            + "Please try again or type 'help' for valid commands.";

    public static String getListInquiryErrorMessage(int size) {
        return size == 0 ? EMPTY_LIST_ERROR_MESSAGE : RANGE_ERROR_MESSAGE + size + ". (both inclusive)";
    }

    public static final String HELP_MESSAGE = "Need a hand? Here's your guide to Adam's commands!\n\n"
            + "Available commands:\n"
            + "- bye (or exit, ex): Exit the chatbot.\n"
            + "- list: View all tasks in the task list.\n"
            + "- todo [description]: Add a new todo task.\n"
            + "- deadline [description] /by [deadline]: Add a new task with a deadline.\n"
            + "- event [description] /from [start time] /to [end time]: Add a new event task.\n"
            + "- mark [task number]: Mark a task as completed.\n"
            + "- unmark [task number]: Mark a completed task as incomplete.\n"
            + "- delete [task number]: Delete a task from the list. (Watch out, it's permanent!)\n"
            + "- help (or h): Display this help menu.\n\n"
            + "To use a command, simply type it followed by any necessary parameters\n"
            + "(e.g. \"deadline Hand in assignments /by 4th June\").\n"
            + "Enjoy chatting with Adam!";

    public static String getDeleteMessage(int newSize, String task) {
        return "Oops! Task gone with the digital wind!\n  "
                + task
                + (newSize == 0 ? "\nOur list is empty now!"
                        : "\nNow down to " + newSize + " tasks in the list.");
    }

}