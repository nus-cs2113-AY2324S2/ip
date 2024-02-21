import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";

    public static void saveTodo(Todo todo) throws IOException {
        char type = 'T';
        String description = todo.description;
        int doneStatus = todo.toString().contains("[X]") ? 1 : 0;
        String toDoText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + System.lineSeparator();
        saveData(toDoText);
    }

    public static void saveDeadline(Deadline deadline) throws IOException {
        char type = 'D';
        int doneStatus = deadline.toString().contains("[X]") ? 1 : 0;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        saveData(deadlineText);
    }

    public static void saveEvent(Event event) throws IOException {
        char type = 'E';
        int doneStatus = event.toString().contains("[X]") ? 1 : 0;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + doneStatus + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        saveData(eventText);
    }

    public static void saveMark(ArrayList<Task> tasks) throws IOException {
        for (Task t : tasks) {
            String listItem = t.toString();
            char type = listItem.charAt(1);
            switch (type) {
            case 'T':
                Todo todo = (Todo) t;
                saveTodo(todo);
                break;
            case 'D':
                Deadline deadline = (Deadline) t;
                saveDeadline(deadline);
                break;
            case 'E':
                Event event = (Event) t;
                saveEvent(event);
                break;
            }
        }
    }

    public static void saveData(String taskCommand) throws IOException {
        File file = new File(DATA_PATH);
        FileWriter fw = new FileWriter(file, true);
        fw.write(taskCommand);
        fw.close();
    }
}
