package Quokka.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The completion status to be set.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status icon of the task.
     *
     * @return The completion status icon ('X' for done, ' ' for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Parses a string representation of a task from a data file and returns a Task object.
     *
     * @param data The string representation of the task.
     * @return A Task object parsed from the string representation.
     */
    public static Task parseFromFileString(String data) {
        String[] parts = data.split("] ", 2);
        if (parts.length == 2) {
            boolean isDone = parts[0].charAt(4) == 'X';
            String taskType = parts[0].substring(1, 2);
            String taskData = parts[1];
            switch (taskType) {
            case "T":
                return Todo.parseFromString(taskData, isDone);
            case "D":
                return Deadline.parseDeadlineFromString(taskData, isDone);
            case "E":
                return Event.parseFromString(taskData, isDone);
            default:
                return null;
            }
        }
        return null;
    }


    /**
     * Parses a string representation of a deadline task and returns a Deadline object.
     *
     * @param taskData The string representation of the deadline task.
     * @param isDone   The completion status of the deadline task.
     * @return A Deadline object parsed from the string representation.
     */
    public static Task parseDeadlineFromString(String taskData, boolean isDone) {
        String[] parts = taskData.split(" \\(by: ", 2);
        if (parts.length == 2) {
            String description = parts[0].trim();
            LocalDateTime by = LocalDateTime.parse(parts[1].substring(0, parts[1].length() - 1),
                    DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return new Deadline(description, by, isDone);
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
