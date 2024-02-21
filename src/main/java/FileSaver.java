import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";

    public static void saveTodo(Todo todo) throws IOException {
        String type = "T";
        String description = todo.description;
        int notDone = todo.getStatusIcon().equals(" ") ? 0 : 1;
        String toDoText = type + SEPARATOR + notDone
                + SEPARATOR + description + System.lineSeparator();
        saveData(toDoText);
    }

    public static void saveDeadline(Deadline deadline) throws IOException {
        String type = "D";
        int notDone = deadline.getStatusIcon().equals(" ") ? 0 : 1;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + notDone
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        saveData(deadlineText);
    }

    public static void saveEvent(Event event) throws IOException {
        String type = "E";
        int notDone = event.getStatusIcon().equals(" ") ? 0 : 1;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + notDone + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        saveData(eventText);
    }

    public static void saveMark(Task[] tasks) {

    }

    public static void saveData(String taskCommand) throws IOException {
        File file = new File(DATA_PATH);
        FileWriter fw = new FileWriter(file, true);
        fw.write(taskCommand);
        fw.close();
    }
}
