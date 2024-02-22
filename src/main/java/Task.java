import java.nio.InvalidMarkException;
import java.util.Arrays;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public char getTypeIcon() {
        return type;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
