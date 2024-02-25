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

public abstract class Storage {
    private static final String FILE_NAME = "Alexis.txt";
    private static final String DIR_NAME = "./data/";
    private static final String FILE_PATH = DIR_NAME + FILE_NAME;

    public static String formatFile(TaskList tasks) {
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

    public static Task formatTask(String fileInput) {
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

    private static ToDo parseToDoFromFile(String description, int taskStatus) {
        ToDo toDo = new ToDo(description);
        markTaskFromFileAsDone(taskStatus, toDo);
        return toDo;
    }

    private static Deadline parseDeadlineFromFile(String[] sections, String description, int taskStatus) {
        String date = sections[3].trim();
        Deadline deadline = new Deadline(description, date);
        markTaskFromFileAsDone(taskStatus, deadline);
        return deadline;
    }

    private static Event parseEventFromFile(String[] sections, String description, int taskStatus) {
        String eventSeparator = "-";
        String[] eventDurationSections = sections[3].split(eventSeparator);
        String start = eventDurationSections[0].trim();
        String end = eventDurationSections[1].trim();
        Event event = new Event(description, start, end);

        markTaskFromFileAsDone(taskStatus, event);
        return event;
    }

    private static void markTaskFromFileAsDone(int taskStatus, Task task) {
        if (taskStatus == 1) {
            task.markAsDone();
        }
    }

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

    private static int getTaskStatus(Task task) {
        int taskStatus;
        if (task.isDone()) {
            taskStatus = 1;
        } else {
            taskStatus = 0;
        }
        return taskStatus;
    }

    public static void writeToFile(String filePath, String fileText) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(fileText);
        fw.close();
    }

    public static void saveToLocalDisk(TaskList tasks) {
        File fileDir = new File(DIR_NAME);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }

        try {
            writeToFile(FILE_PATH, formatFile(tasks));
            System.out.println("File successfully saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

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
