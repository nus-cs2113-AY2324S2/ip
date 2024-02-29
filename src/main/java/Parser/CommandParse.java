package Parser;

public class CommandParse {
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
        command = "event " + description + " /from " + fromTime + " /to " + toTime;
        return command;
    }

    public static String getDeadlineCommand(String nextLine) {
        int startDescription = nextLine.indexOf("[D]") + 6;
        int endDescription = nextLine.indexOf(" (by:");
        String description = nextLine.substring(startDescription, endDescription).trim();
        String keyword = "(by: ";
        int start = nextLine.indexOf(keyword) + keyword.length();
        int end = nextLine.lastIndexOf(')');

        String dateTime = nextLine.substring(start, end).trim();
        String command;
        command = "deadline " + description + " /by " + dateTime;
        return command;
    }

    public static String getTodoCommand(String input) {
        return input.replace("todo ", "");
    }

    public static String getTodoString(String nextLine) {
        return nextLine.substring(6).trim();
    }
}
