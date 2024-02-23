package gab.task;

import gab.exception.GabException;

public class Task {
    private final String DESCRIPTION;
    private boolean isDone;
    protected static int taskCount = 0;

    public Task (String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
        taskCount++;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + DESCRIPTION; //will be over ride by subclass
    }

    public String toFileFormat() {
        return " | " + (this.isDone? "1" : "0") + " | " + this.DESCRIPTION;
    }

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

