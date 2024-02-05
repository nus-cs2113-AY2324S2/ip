import java.util.List;

public class Task {
    protected Boolean isDone;
    protected String name;

    public Task() {
        this.name = "Empty Task";
        this.isDone = false;
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public static String parse(String command) {
        return command.substring(command.indexOf(" ") + 1);
    }
    public void addTo(List<Task> tasks) {
        tasks.add(this);
        System.out.println("______________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     " + tasks.get(tasks.size()-1) + "\n" +
                "     Now you have " + Integer.toString(tasks.size()) + " tasks in the list.\n" +
                "______________________________________");
    }

    @Override
    public String toString() {
        if (isDone){
            return "[X] " + name;
        }
        else{
            return "[ ] " + name;
        }
    }
}
