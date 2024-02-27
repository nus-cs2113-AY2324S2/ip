package Alexis.console;

import Alexis.task.TaskList;
import Alexis.task.Task;
import Alexis.task.ToDo;
import Alexis.task.Deadline;
import Alexis.task.Event;
import Alexis.task.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class is responsible for all save and load operations to local disk.
 */
public class Storage {
    private static final String FILE_NAME = "Alexis.txt";
    private static final String DIR_NAME = "./data/";
    private static final String FILE_PATH = DIR_NAME + FILE_NAME;

    /**
     * Formats a list of tasks into a string and returns a string representation of all tasks for file storage.
     * Each task is represented by a line in the string.
     *
     * @param tasks The task list containing the tasks to be formatted.
     * @return A formatted string representation of all tasks.
     */
    private static String formatFile(TaskList tasks) {
        StringBuilder formattedTasks = new StringBuilder();
        for (Task task : tasks.getTasks()) {
            String taskSymbol = getTaskSymbol(task);
            int taskStatus = getTaskStatus(task);
            String taskDescription = task.getDescription();
            String taskDate = getTaskDate(task);

            if (task instanceof ToDo) {
                formattedTasks.append(String.format("%s | %d | %s\n", taskSymbol, taskStatus, taskDescription));
            } else {
                formattedTasks.append(String.format("%s | %d | %s | %s\n", taskSymbol, taskStatus, taskDescription, taskDate));
            }
        }
        return formattedTasks.toString();
    }

    /**
     * Converts a line of text from a file into a Task object and returns the object.
     *
     * @param fileInput The string input from the saved file representing a single task.
     * @return The Task object parsed from the input string or null if the task type is not recognized.
     */
    private static Task formatTask(String fileInput) {
        String separator = " \\| ";
        String[] sections = fileInput.split(separator);
        String taskType = sections[0].trim();
        int taskStatus = Integer.parseInt(sections[1].trim());
        String description = sections[2].trim();

        switch (taskType) {
        case "T":
            return parseToDoFromFile(description, taskStatus);
        case "D":
            return parseDeadlineFromFile(sections, description, taskStatus);
        case "E":
            return parseEventFromFile(sections, description, taskStatus);
        }
        return null;
    }

    /**
     * Parses and returns a ToDo object from a string from a saved file.
     *
     * @param description The description of the ToDo task.
     * @param taskStatus The status of the task, where 1 indicates done and 0 indicates not done.
     * @return A ToDo object with the specified description and status.
     */
    private static ToDo parseToDoFromFile(String description, int taskStatus) {
        ToDo toDo = new ToDo(description);
        markTaskFromFileAsDone(taskStatus, toDo);
        return toDo;
    }

    /**
     * Parses and returns a Deadline object from a string from a saved file.
     *
     * @param sections The array of strings after splitting the file input by the separator.
     * @param description The description of the Deadline task.
     * @param taskStatus The status of the task, where 1 indicates done and 0 indicates not done.
     * @return A Deadline object with the specified description and status.
     */
    private static Deadline parseDeadlineFromFile(String[] sections, String description, int taskStatus) {
        String date = sections[3].trim();
        Deadline deadline = new Deadline(description, date);
        markTaskFromFileAsDone(taskStatus, deadline);
        return deadline;
    }

    /**
     * Parses and returns an Event object from a string from a saved file.
     *
     * @param sections The array of strings after splitting the file input by the separator.
     * @param description The description of the Event task.
     * @param taskStatus The status of the task, where 1 indicates done and 0 indicates not done.
     * @return An Event object with the specified description and status.
     */
    private static Event parseEventFromFile(String[] sections, String description, int taskStatus) {
        String eventSeparator = "-";
        String[] eventDurationSections = sections[3].split(eventSeparator);
        String start = eventDurationSections[0].trim();
        String end = eventDurationSections[1].trim();
        Event event = new Event(description, start, end);

        markTaskFromFileAsDone(taskStatus, event);
        return event;
    }

    /**
     * Marks a task as done if its status is 1.
     *
     * @param taskStatus The status of the task from the file, where 1 indicates done and 0 indicates not done.
     * @param task The task to be marked as done if its status is 1.
     */
    private static void markTaskFromFileAsDone(int taskStatus, Task task) {
        if (taskStatus == 1) {
            task.markAsDone();
        }
    }

    /**
     * Retrieves and returns the date associated with a task as a string.
     *
     * @param task The task whose date is to be retrieved.
     * @return The date of the task as a string or an empty string if the task does not have a date.
     */
    private static String getTaskDate(Task task) {
        TaskType taskType = TaskType.getTaskType(task.getClass().getSimpleName());
        String taskDate = null;

        switch (taskType) {
        case TODO:
            taskDate = "";
            break;
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            taskDate = deadline.getBy();
            break;
        case EVENT:
            Event event = (Event) task;
            taskDate = event.getStart() + "-" + event.getEnd();
            break;
        }
        return taskDate;
    }

    /**
     * Retrieves and returns the symbol representing the task type.
     *
     * @param task The task whose symbol is to be retrieved.
     * @return The symbol representing the task type as a string.
     */
    private static String getTaskSymbol(Task task) {
        TaskType taskType = TaskType.getTaskType(task.getClass().getSimpleName());
        String taskSymbol = null;

        switch (taskType) {
        case TODO:
            taskSymbol = "T";
            break;
        case DEADLINE:
            taskSymbol = "D";
            break;
        case EVENT:
            taskSymbol = "E";
            break;
        }
        return taskSymbol;
    }

    /**
     * Retrieves and returns the status of a task as an integer.
     *
     * @param task The task whose status is to be retrieved.
     * @return 1 if the task is done or 0 if the task is not done.
     */
    private static int getTaskStatus(Task task) {
        int taskStatus;
        if (task.isDone()) {
            taskStatus = 1;
        } else {
            taskStatus = 0;
        }
        return taskStatus;
    }

    /**
     * Writes the text to a file at the predefined file path.
     *
     * @param fileText The text to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeToFile(String fileText) throws IOException {
        FileWriter fw = new FileWriter(Storage.FILE_PATH);
        fw.write(fileText);
        fw.close();
    }

    /**
     * Saves the current state of the task list to the local disk.
     *
     * @param tasks The TaskList to save to disk.
     */
    public static void saveToLocalDisk(TaskList tasks) {
        File fileDir = new File(DIR_NAME);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }

        try {
            writeToFile(formatFile(tasks));
            System.out.println("File successfully saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Reads the tasks from a file and adds them to the task list.
     *
     * @param tasks The task list to add the tasks into.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static void readFromFile(TaskList tasks) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String fileInput = scanner.nextLine();
            Task task = formatTask(fileInput);
            if (task != null) {
                tasks.addToTaskListFromFIle(task);
            }
        }
        scanner.close();
    }
}
