package joe.task;

import joe.JoeException;
import joe.util.FileManager;
import joe.util.InputParser;
import joe.util.Printer;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    protected int numberOfTasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        numberOfTasks = 0;
    }

    /**
     * Adds a task to the array list
     *
     * @param t Task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
        numberOfTasks++;
    }

    /**
     * Adds a task to the array list accordingly from the user input.
     *
     * @param type Task type indicating the type of task to be added
     * @param message User input arguments
     * @throws JoeException if task to be added cannot be created, or the input arguments are invalid
     */
    public void addTask(TaskType type, String message) throws JoeException {
        if (message.isEmpty()) {
            throw new JoeException();
        }

        Task t;
        switch (type) {
        case TODO:
            t = new ToDo(message);
            break;
        case DEADLINE:
            String deadlineName = InputParser.getTaskName(message);
            String deadlineTime = InputParser.getDeadlineTime(message);
            if (deadlineName.isEmpty() || deadlineTime.isEmpty()) {
                throw new JoeException();
            }
            t = new Deadline(deadlineName, deadlineTime);
            break;
        case EVENT:
            String eventName = InputParser.getTaskName(message);
            String[] eventDuration = InputParser.getEventTime(message);
            if (eventName.isEmpty() || eventDuration[0].isEmpty() || eventDuration[1].isEmpty()) {
                throw new JoeException();
            }
            t = new Event(eventName, eventDuration[0], eventDuration[1]);
            break;
        default:
            throw new JoeException();
        }

        tasks.add(t);
        numberOfTasks++;
        Printer.printTaskAddingMessage(t.getTaskStatus(), numberOfTasks);

        saveList();
    }

    /**
     * Deletes a task from the array list
     *
     * @param taskNumber Index of the task to remove
     * @throws JoeException if the task number is more than the size of the array list, or is a negative integer
     */
    public void deleteTask(int taskNumber) throws JoeException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new JoeException();
        }
        Printer.printDeleteMessage();
        System.out.println("  " + tasks.get(taskNumber - 1).getTaskStatus());
        tasks.remove(taskNumber - 1);
        numberOfTasks--;
        Printer.printNumOfTasks(numberOfTasks);

        saveList();
    }

    /**
     * List all tasks currently in the array list
     */
    public void listTasks() {
        Printer.printListMessage();
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).getTaskStatus());
        }
        Printer.printHeaderLine();
    }

    /**
     * Sets the mark status of a task accordingly
     *
     * @param taskNumber Index of the task to toggle
     * @param isMark Boolean to determine to mark or unmark task
     * @throws JoeException if the task number is more than the size of the array list, or is a negative integer
     */
    public void toggleTaskMarkedStatus(int taskNumber, boolean isMark) throws JoeException {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            throw new JoeException();
        }
        tasks.get(taskNumber - 1).setDone(isMark);
        if (isMark) {
            Printer.printMarkMessage();
        } else {
            Printer.printUnmarkMessage();
        }
        System.out.println("  " + tasks.get(taskNumber - 1).getTaskStatus());
        Printer.printHeaderLine();

        saveList();
    }

    /**
     * Saves the current list to the system. Prints error message if unable to save
     */
    protected void saveList() {
        try {
            FileManager.saveData(tasks);
        } catch (IOException e) {
            Printer.printSaveError();
        }
    }
}
