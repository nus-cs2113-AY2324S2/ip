import java.util.ArrayList;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getDescription() throws LovieException {
        String[] splitUpDescription = description.split("todo");
        if (splitUpDescription.length == 1) {
            throw new LovieException("Oops. Make sure to add a description for your todo!");
        }
        String realDescription = splitUpDescription[1];
        return realDescription;
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

}
