package Tony.FileManager;
import Tony.task.Todo;
import Tony.task.Deadline;
import Tony.task.Event;
import Tony.Tony;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";

    public static String saveTodo(Todo todo) {
        char type = 'T';
        String description = todo.description;
        int doneStatus = todo.toString().contains("[X]") ? 1 : 0;
        String toDoText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + System.lineSeparator();
        return toDoText;
    }

    public static String saveDeadline(Deadline deadline) {
        char type = 'D';
        int doneStatus = deadline.toString().contains("[X]") ? 1 : 0;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        return deadlineText;
    }

    public static String saveEvent(Event event) {
        char type = 'E';
        int doneStatus = event.toString().contains("[X]") ? 1 : 0;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + doneStatus + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        return eventText;
    }

    public static void updateFile() throws IOException {
        for (int i = 0; i < Tony.tasks.size(); i++) {
            String listItem = Tony.tasks.get(i).toString();
            char type = listItem.charAt(1);
            boolean isAppend = i != 0;
            switch (type) {
            case 'T':
                Todo todo = (Todo) Tony.tasks.get(i);
                String todoLine = saveTodo(todo);
                saveData(todoLine, isAppend);
                break;
            case 'D':
                Deadline deadline = (Deadline) Tony.tasks.get(i);
                String deadlineLine = saveDeadline(deadline);
                saveData(deadlineLine, isAppend);
                break;
            case 'E':
                Event event = (Event) Tony.tasks.get(i);
                String eventLine = saveEvent(event);
                saveData(eventLine, isAppend);
                break;
            }
        }
    }

    public static void saveData(String taskCommand, boolean isAppend) throws IOException {
        File file = new File(DATA_PATH);
        FileWriter fw = new FileWriter(file, isAppend);
        fw.write(taskCommand);
        fw.close();
    }
}
