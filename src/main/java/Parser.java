public class Parser {
    public static String[] splitEvent(String userInput) {
        String[] eventParts = userInput.split("/from");
        return new String[]{eventParts[0].trim(), eventParts[1].trim()};
    }

    public static String[] splitTodo(String userInput) {
        return userInput.split("\\s+", 2);
    }

    public static String[] splitDeadline(String userInput) {
        String[] deadlineParts = userInput.split("/by");
        return new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()};
    }

    public static String[] splitTaskNumber(String userInput) {
        return userInput.split(" ");
    }

    public static String[] splitTimeline(String date) {
        return date.split("/to");
    }

    public static String parseFindKeyword(String userInput) {
        return userInput.substring(5).trim();
    }
}
