package GermaBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import DataHandling.*;
import Exceptions.FileReadException;
import Tasks.*;

/**
 * Handles storage operations for the GermaBot application.
 */
public class Storage {
    private File dataFile;

    public Storage() {
    }

    /**
     * Loads tasks from a file.
     * @return The list of tasks loaded from the file.
     * @throws FileNotFoundException If the file is not found.
     * @throws FileReadException If there is an error reading the file.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, FileReadException {
        return LoadData.loadFile();
    }

    /**
     * Saves a todo task to the file.
     * @param toDoTask The todo task to save to the file.
     * @param taskType The type of task (T for todo, D for deadline, E for event).
     * @throws IOException If an I/O error occurs.
     */
    public static void saveTodo(ToDo toDoTask, char taskType) throws IOException {
        SaveData.addTodoToFile(toDoTask, taskType);
    }

    /**
     * Saves a deadline task to the file.
     * @param deadlineTask The deadline task to save to the file.
     * @param taskType The type of task (T for todo, D for deadline, E for event).
     * @throws IOException If an I/O error occurs.
     */
    public static void saveDeadline(Deadline deadlineTask, char taskType) throws IOException {
        SaveData.addDeadlineToFile(deadlineTask, taskType);
    }

    /**
     * Saves an event task to the file.
     * @param eventTask The event task to save to the file.
     * @param taskType The type of task (T for todo, D for deadline, E for event).
     * @throws IOException If an I/O error occurs.
     */
    public static void saveEvent(Event eventTask, char taskType) throws IOException {
        SaveData.addEventToFile(eventTask, taskType);
    }

}
