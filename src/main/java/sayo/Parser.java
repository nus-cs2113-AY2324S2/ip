package sayo;

public class Parser {
    
    public static int getIndexForMark(String input) {
        return Integer.parseInt(input.substring(5)) - 1;
    }

    public static int getIndexForUnmark(String input) {
        return Integer.parseInt(input.substring(7)) - 1;
    }

    public static String getTodoDescription(String input) {
        return input.substring(5).trim();
    }

    public static String getDeadlineDescription(String input, int byIndex) {
        return input.substring(9, byIndex).trim();
    }

    public static String getDeadlineBy(String input, int byIndex) {
        return input.substring(byIndex + 4).trim();
    }

    public static int getEventFromIndex(String input) {
        return input.indexOf("/from");
    }

    public static int getEventToIndex(String input) {
        return input.indexOf("/to");
    }

    public static String getEventDescription(String input, int fromIndex) {
        return input.substring(6, fromIndex).trim();
    }

    public static String getEventStart(String input, int fromIndex, int toIndex) {
        return input.substring(fromIndex + 6, toIndex).trim();
    }

    public static String getEventEnd(String input, int toIndex) {
        return input.substring(toIndex + 4).trim();
    }

    public static int getDeleteIndex(String input) {
        return Integer.parseInt(input.substring(7).trim()) - 1;
    }
    

}
