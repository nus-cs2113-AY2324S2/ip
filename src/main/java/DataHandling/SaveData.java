package DataHandling;

import Tasks.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The SaveData class handles saving tasks to a file.
 */
public class SaveData {
    private static final String filePath = "src/data/GermaBotData.txt";

    /**
     * Saves a ToDo task to the file.
     *
     * @param toDoTask The ToDo task to be saved.
     * @param taskType The type of the task ('T' for ToDo).
     * @throws IOException If there is an error writing to the file.
     */
    public static void addTodoToFile(ToDo toDoTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("T | " + (toDoTask.isDone() ? "1" : "0") + " | " + toDoTask.getDescription() + System.lineSeparator());
        writer.close();
    }

    /**
     * Saves a Deadline task to the file.
     *
     * @param deadlineTask The Deadline task to be saved.
     * @param taskType The type of the task ('D' for Deadline).
     * @throws IOException If there is an error writing to the file.
     */
    public static void addDeadlineToFile(Deadline deadlineTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("D | " +  (deadlineTask.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() +
                " | " + deadlineTask.getBy() + System.lineSeparator());
        writer.close();
    }

    /**
     * Saves an Event task to the file.
     *
     * @param eventTask The Event task to be saved.
     * @param taskType The type of the task ('E' for Event).
     * @throws IOException If there is an error writing to the file.
     */
    public static void addEventToFile(Event eventTask, char taskType) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write("E | " + (eventTask.isDone() ? "1" : "0") + " | " + eventTask.getDescription() +
                " | " + eventTask.getFrom() + " | " + eventTask.getTo() + System.lineSeparator());
        writer.close();
    }
}
