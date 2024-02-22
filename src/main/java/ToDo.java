public class ToDo extends Task {
    private static final String TYPE = "T";
    ToDo(String input) {
        super(input, TYPE);
    }

    ToDo(String input, boolean status) {
        super(input, TYPE, status);
    }
}
