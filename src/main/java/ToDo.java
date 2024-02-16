import java.util.ArrayList;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getDescription() {
        String[] splitUpDescription = description.trim().split("(?i)todo");
        String realDescription = splitUpDescription[1];
        return realDescription;
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }

}
