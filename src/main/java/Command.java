public class Command {
    static final String LIST_COMMAND = "LIST";
    static final String MARK_COMMAND = "MARK";
    static final String UNMARK_COMMAND = "UNMARK";
    static final String TODO_COMMAND = "TODO";
    static final String DEADLINE_COMMAND = "DEADLINE";
    static final String EVENT_COMMAND = "EVENT";

    static final String BYE_COMMAND = "BYE";

    public static String parseInput(String input) {
        return input.toUpperCase();
    }
}
