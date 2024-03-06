package Storage;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a utility class which appends news tasks to an existing file.
 * This class provides methods to add ToDos, Deadlines and Events to a specified file.
 */
public class AppendData {
    /**
     * Appends a ToDo to a specified file.
     * @param input The ToDo task to append
     * @param fw The FileWriter object which appends the ToDo to the file
     * @throws IOException If an I/O error occurs (such as being unable to access the specified file)
     */
    public static void appendToDo(ToDo input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String textToAppend = taskType + " | " + isCompleted + " | " + description;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    /**
     * Appends a Deadline to a specified file.
     * @param input The Deadline task to append
     * @param fw The FileWriter object which appends the Deadline to the file
     * @throws IOException If an I/O error occurs (such as being unable to access the specified file)
     */

    public static void appendDeadline(Deadline input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + by;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    /**
     * Appends an Event to a specified file.
     * @param input The Event task to append
     * @param fw The FileWriter object which appends the Event to the file
     * @throws IOException If an I/O error occurs (such as being unable to access the specified file)
     */
    public static void appendEvent(Event input, FileWriter fw) throws IOException {
        String description = input.getDescription();
        String taskType = input.getTaskType();
        String isCompleted = (input.getStatusIcon().equals("X")) ? "1" : "0";
        String by = input.getEndDate();
        String from = input.getStartDate();
        String textToAppend = taskType + " | " + isCompleted + " | " + description + " | " + from + " | " + by;
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

}
