package gab.task;

import gab.exception.GabException;

/**
 * Super class of Deadline, Event and Todo
 */

public class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    /**
     * Initialise a new task class with name and whether task isDone
     * @param description task name
     */

    public Task (String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    /**
     * Updates task to be done
     */

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Update task as not done
     */

    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Return the format to print the status of task
     *
     * @return task completion status
     */

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Method to get the task name
     *
     * @return the task name
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Returns format of string to be printed, to be over ride by specific class
     *
     * @return format of string to print
     */

    @Override
    public String toString() {
        return getStatusIcon() + " " + DESCRIPTION; //will be over ride by subclass
    }

    /**
     * Returns format of data file to be printed
     *
     * @return file format of task to be saved in data file
     */

    public String toFileFormat() {
        return " | " + (this.isDone? "1" : "0") + " | " + this.DESCRIPTION;
    }

    /**
     * Loads file input and tag them with their task category
     * Return task and create a new task to be added to taskList
     *
     * @param line input read from file
     * @return task created based on task type
     * @throws GabException thrown when error format is wrong or corrupted
     */

    public static Task loadFromFile(String line) throws GabException {
        String[] savedArray = line.split(" \\| ");
        String taskStatus = savedArray[0];
        boolean isDone = savedArray[1].equals("1");
        String taskName = savedArray[2];

        Task task;
        switch (taskStatus) {
        case "[T]":
            task = new ToDo(taskName);
            break;
        case "[D]":
            String by = savedArray[3];
            task = new Deadline(taskName, by);
            break;
        case "[E]":
            String eventDates = savedArray[3];
            String[] eventArray = eventDates.split(" to: ");
            String[] eventFromArray = eventArray[0].split(" ");
            String startDate = eventFromArray[1];
            String endDate = eventArray[1];
            task = new Event(taskName, startDate, endDate);
            break;
        default:
            throw new GabException("Error file format is incorrect and unable to load! ");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}

