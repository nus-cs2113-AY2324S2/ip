package Parser;

public class CommandParse {

    /**
     * Parses the event command string from a line read from the task list file.
     *
     * @param nextLine The string line of task read from the task list file.
     * @return The command string for an event task, formatted as "description /from startTime /to endTime".
     */
    public static String getEventCommand(String nextLine) {
        int startDescription = nextLine.indexOf("[E]") + 6;
        int endDescription = nextLine.indexOf(" (from:");
        String description = nextLine.substring(startDescription, endDescription).trim();
        String fromKeyword = "(from: ";
        String toKeyword = " to:";
        int start = nextLine.indexOf(fromKeyword) + fromKeyword.length();
        int end = nextLine.indexOf(toKeyword);
        String fromTime = nextLine.substring(start, end).trim();
        String startKeyword = "to: ";
        int start1 = nextLine.indexOf(startKeyword) + startKeyword.length();
        int end1 = nextLine.lastIndexOf(')');
        String toTime = nextLine.substring(start1, end1).trim();
        String command;
        command = description + " /from " + fromTime + " /to " + toTime;
        return command;
    }

    /**
     * Parses the deadline command string from a line read from the task list file.
     *
     * @param nextLine The string line read from the task list file.
     * @return The command string for a deadline task, formatted as "description /by dateTime".
     */
    public static String getDeadlineCommand(String nextLine) {
        int startDescription = nextLine.indexOf("[D]") + 6;
        int endDescription = nextLine.indexOf(" (by:");
        String description = nextLine.substring(startDescription, endDescription).trim();
        String keyword = "(by: ";
        int start = nextLine.indexOf(keyword) + keyword.length();
        int end = nextLine.lastIndexOf(')');

        String dateTime = nextLine.substring(start, end).trim();
        String command;
        command = description + " /by " + dateTime;
        return command;
    }

    /**
     * Extracts the todo command description from an input string.
     *
     * @param input The input string containing the todo command.
     * @return The description of the todo task.
     */
    public static String getTodoCommand(String input) {
        return input.replace("todo ", "");
    }

    /**
     * Extracts the todo task description from a line read from the task list file.
     *
     * @param nextLine The string line read from the task list file.
     * @return The description of the todo task.
     */

    public static String getTodoString(String nextLine) {
        return nextLine.substring(6).trim();
    }
}
