/**
 * The Parser class provides methods for parsing user input.
 */
public class Parser {
    /**
     * Splits an event task input into event description and timeline.
     *
     * @param userInput The user input specifying the event task.
     * @return An array containing the event description and timeline.
     */
    public static String[] splitEvent(String userInput) {
        String[] eventParts = userInput.split("/from");
        return new String[]{eventParts[0].trim(), eventParts[1].trim()};
    }

    /**
     * Splits a todo task input into description.
     *
     * @param userInput The user input specifying the todo task.
     * @return An array containing the todo description.
     */
    public static String[] splitTodo(String userInput) {
        return userInput.split("\\s+", 2);
    }

    /**
     * Splits a deadline task input into description and deadline.
     *
     * @param userInput The user input specifying the deadline task.
     * @return An array containing the deadline description and deadline.
     */
    public static String[] splitDeadline(String userInput) {
        String[] deadlineParts = userInput.split("/by");
        return new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()};
    }

    /**
     * Splits a task number input.
     *
     * @param userInput The user input specifying the task number.
     * @return An array containing the task number.
     */
    public static String[] splitTaskNumber(String userInput) {
        return userInput.split(" ");
    }

    /**
     * Splits a timeline input into start and end dates.
     *
     * @param date The user input specifying the timeline.
     * @return An array containing the start and end dates of the timeline.
     */
    public static String[] splitTimeline(String date) {
        return date.split("/to");
    }

}
