package Tony.FileManager;

import Tony.task.Task;
import Tony.task.Todo;
import Tony.task.Deadline;
import Tony.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaver {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " | ";
    private ArrayList<Task> tasks;

    /**
     * Represents a <code>FileSaver</code> object that saves tasks added by user.
     * @param tasks is the current list of tasks to save into the file.
     */
    public FileSaver(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns formatted text with <code>SEPARATOR</code> to save in the file.
     * @param todo Task type of Todo
     * @return <code>toDoText</code> formatted String to save in the file.
     */

    public String saveTodo(Todo todo) {
        char type = 'T';
        String description = todo.description;
        int doneStatus = todo.toString().contains("[X]") ? 1 : 0;
        String toDoText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + System.lineSeparator();
        return toDoText;
    }
    /**
     * Returns formatted text with <code>SEPARATOR</code> to save in the file.
     * @param deadline Task type of Deadline
     * @return <code>deadlineText</code> formatted String to save in the file.
     */
    public String saveDeadline(Deadline deadline) {
        char type = 'D';
        int doneStatus = deadline.toString().contains("[X]") ? 1 : 0;
        String description = deadline.description;
        String by = deadline.by;
        String deadlineText = type + SEPARATOR + doneStatus
                + SEPARATOR + description + SEPARATOR + by  + System.lineSeparator();
        return deadlineText;
    }
    /**
     * Returns formatted text with <code>SEPARATOR</code> to save in the file.
     * @param event Task type of Event
     * @return <code>eventText</code> formatted String to save in the file.
     */
    public String saveEvent(Event event) {
        char type = 'E';
        int doneStatus = event.toString().contains("[X]") ? 1 : 0;
        String description = event.description;
        String from = event.from;
        String to = event.to;
        String eventText = type + SEPARATOR + doneStatus + SEPARATOR + description
                + SEPARATOR + from + " to " + to + System.lineSeparator();
        return eventText;
    }

    /**
     * Updates the file with the latest changes made in the <code>tasks</code> list.
     * @throws IOException If there is error locating file to save the data into.
     */

    public void updateFile() throws IOException {
        checkForEmptyList();
        for (int i = 0; i < tasks.size(); i++) {
            String listItem = tasks.get(i).toString();
            char type = listItem.charAt(1);
            boolean isAppend = i != 0;
            switch (type) {
            case 'T':
                Todo todo = (Todo) tasks.get(i);
                String todoLine = saveTodo(todo);
                saveData(todoLine, isAppend);
                break;
            case 'D':
                Deadline deadline = (Deadline) tasks.get(i);
                String deadlineLine = saveDeadline(deadline);
                saveData(deadlineLine, isAppend);
                break;
            case 'E':
                Event event = (Event) tasks.get(i);
                String eventLine = saveEvent(event);
                saveData(eventLine, isAppend);
                break;
            default:
                saveData("", false);
            }
        }
    }

    /**
     * Checks if the list is empty, then when updating the file,
     * then the file should also be empty.
     * @throws IOException If there is error locating file to save the data into.
     */

    private void checkForEmptyList() throws IOException {
        if (tasks.isEmpty()) {
            saveData("", false);
        }
    }

    /**
     * Saves the formatted task line into the file
     * @param taskCommand Formatted String to save into file
     * @param isAppend Determine whether file is to be appended to existing data.
     * @throws IOException If there is error locating file to save the data into.
     */

    public void saveData(String taskCommand, boolean isAppend) throws IOException {
        File file = new File(DATA_PATH);
        FileWriter fw = new FileWriter(file, isAppend);
        fw.write(taskCommand);
        fw.close();
    }
}
