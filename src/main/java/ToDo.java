public class ToDo extends Task {
    private static final String TYPE = "T";
    ToDo(String description) {
        super(description, TYPE);
    }

    ToDo(String description, boolean status) {
        super(description, TYPE, status);
    }
}
