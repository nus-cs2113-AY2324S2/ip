package vibes.task;

import vibes.exception.CommandNotFoundException;
import vibes.exception.InvalidArgumentException;
import vibes.task.type.Deadline;
import vibes.task.type.Event;
import vibes.task.type.Task;
import vibes.task.type.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskList {
    public static final String DATA_FOLDER = "./data/";
    public static final String DATA_FILE = "tasks.txt";
    public static final String PARAM_SEPARATOR = " | ";

    ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    public void executeCommand(String commandToExecute, String userInput) throws CommandNotFoundException, InvalidArgumentException {
        int taskNumber;
        String description;

        switch (commandToExecute) {
        case "list":
            listTasks();
            break;
        case "mark":
            taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
            setAsDone(taskNumber);
            break;
        case "unmark":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            setAsNotDone(taskNumber);
            break;
        case "todo":
            if (5 > userInput.length()) {
                throw new InvalidArgumentException();
            }
            description = userInput.substring(userInput.indexOf(" ") + 1);
            addTodo(description);

            System.out.println("\t Got it. I've added this task:");
            System.out.println("\t   " + tasks.get(taskCount));
            taskCount++;
            System.out.println("\t Now you have " + taskCount + " tasks in the list.");
            break;
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/from") - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            addEvent(description, from, to);

            System.out.println("\t Got it. I've added this task:");
            System.out.println("\t   " + tasks.get(taskCount));
            taskCount++;
            System.out.println("\t Now you have " + taskCount + " tasks in the list.");
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            addDeadline(description, by);

            System.out.println("\t Got it. I've added this task:");
            System.out.println("\t   " + tasks.get(taskCount));
            taskCount++;
            System.out.println("\t Now you have " + taskCount + " tasks in the list.");
            break;
        case "delete":
            taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
            deleteTask(taskNumber);
            break;
        default:
            throw new CommandNotFoundException();
        }

        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() throws IOException {
        // Clear the file content before writing
        FileWriter fileClearer = new FileWriter(DATA_FOLDER + DATA_FILE);
        fileClearer.write("");
        fileClearer.close();

        FileWriter fileWriter = new FileWriter(DATA_FOLDER + DATA_FILE, true);
        for (Task task : tasks) {
            if (task == null) {
                break;
            }

            String textToWrite = "";
            switch (task.getTaskType()) {
            case 'T':
                Todo todoTask = (Todo) task;
                textToWrite = todoTask.getTaskType() + PARAM_SEPARATOR + (todoTask.isDone() ? 1 : 0) + PARAM_SEPARATOR
                        + todoTask.getDescription();
                break;
            case 'D':
                assert task instanceof Deadline;
                Deadline deadlineTask = (Deadline) task;
                textToWrite = deadlineTask.getTaskType() + PARAM_SEPARATOR + (deadlineTask.isDone() ? 1 : 0) +
                        PARAM_SEPARATOR + deadlineTask.getDescription() + PARAM_SEPARATOR + deadlineTask.getBy();
                break;
            case 'E':
                assert task instanceof Event;
                Event eventTask = (Event) task;
                textToWrite = eventTask.getTaskType() + PARAM_SEPARATOR + (eventTask.isDone() ? 1 : 0) +
                        PARAM_SEPARATOR + eventTask.getDescription() + PARAM_SEPARATOR + eventTask.getFrom()
                        + PARAM_SEPARATOR + eventTask.getTo();
                break;
            }
            fileWriter.write(textToWrite + "\n");
        }
        fileWriter.close();
    }

    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void setAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void setAsNotDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void deleteTask(int taskNumber) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + tasks.get(taskNumber));
        tasks.remove(taskNumber);
        taskCount--;
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
    }

    public void loadTasks() throws FileNotFoundException {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.err.println("Failed to create data folder.");
                return;
            }
        }

        File file = new File(DATA_FOLDER + DATA_FILE);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.err.println("Failed to create data file.");
                    return;
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the data file: " + e.getMessage());
                return;
            }
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            readTask(s.nextLine());
        }
    }

    private void readTask(String textLine) {
        char taskType = textLine.charAt(0);
        boolean isMarked = (textLine.charAt(4) == '1');
        String description;

        switch (taskType) {
        case 'T':
            description = textLine.substring(8).trim();
            addTodo(description);
            if (isMarked) {
                tasks.get(taskCount).setDone(true);
            }
            taskCount++;
            break;
        case 'D':
            description = textLine.substring(8, textLine.indexOf('|', 8)).trim();
            String by = textLine.substring(textLine.indexOf('|', 8) + 1).trim();
            addDeadline(description, by);
            if (isMarked) {
                tasks.get(taskCount).setDone(true);
            }
            taskCount++;
            break;
        case 'E':
            description = textLine.substring(8, textLine.indexOf('|', 8)).trim();
            String from = textLine.substring(textLine.indexOf('|', 8) + 1, textLine.lastIndexOf('|'))
                    .trim();
            String to = textLine.substring(textLine.lastIndexOf('|') + 1).trim();
            addEvent(description, from, to);
            if (isMarked) {
                tasks.get(taskCount).setDone(true);
            }
            taskCount++;
            break;
        }
    }
}
