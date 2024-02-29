package gary.task;

import gary.exception.UnknownCommandException;
import gary.exception.MissingTodoDescriptionException;
import gary.exception.MissingDeadlineByException;
import gary.exception.MissingDeadlineDescriptionException;
import gary.exception.MissingEventToException;
import gary.exception.MissingEventFromException;
import gary.exception.MissingEventDescriptionException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;
    public static void processList(int todosCount, ArrayList<Task> todos) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < todosCount; i += 1) {
            todos.get(i).printTask(i);
        }
    }

    public static void processAddTask(String command, ArrayList<Task> todos, String line)
            throws UnknownCommandException, MissingTodoDescriptionException,
            MissingDeadlineByException, MissingDeadlineDescriptionException, DateTimeParseException,
            MissingEventFromException, MissingEventToException, MissingEventDescriptionException {
        if (command.equalsIgnoreCase("TODO")) {
            createNewTodo(todos, line);
        } else if (command.equalsIgnoreCase("DEADLINE")) {
            createNewDeadline(todos, line);
        } else if (command.equalsIgnoreCase("EVENT")) {
            createNewEvent(todos, line);
        } else {
            throw new UnknownCommandException();
        }
    }

    public static void processMark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.markAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
    }

    public static void processUnmark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.unmarkAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
    }

    public static void processDelete(ArrayList<Task> todos, String[] lineWords, int todosCount) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        int taskIndex = Integer.parseInt(lineWords[1]) - 1;
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        todos.remove(taskIndex);
        System.out.println("Noted, I've removed this task:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
        System.out.println("Now you have " + (todosCount - 1) + " tasks in the list.");
    }

    private static void createNewTodo(ArrayList<Task> todos, String line)
            throws MissingTodoDescriptionException {
        try {
            todos.add(new Todo(line.substring(TODO_DESCRIPTION_START_INDEX)));
        } catch(StringIndexOutOfBoundsException e) {
            throw new MissingTodoDescriptionException();
        }
    }

    private static void createNewDeadline(ArrayList<Task> todos, String line)
            throws MissingDeadlineByException, MissingDeadlineDescriptionException,
            DateTimeParseException {
        if (!(line.contains("/by"))) {
            throw new MissingDeadlineByException();
        }

        int bySlashIndex = line.indexOf("/");
        String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_START_INDEX, bySlashIndex);
        if (deadlineDescription.isBlank()) {
            throw new MissingDeadlineDescriptionException();
        }

        try {
            String deadlineBy = line.substring(bySlashIndex + DEADLINE_BY_SPACE_LENGTH).strip();
            if (deadlineBy.isBlank()) {
                throw new MissingDeadlineByException();
            }
            LocalDate deadlineByDate = LocalDate.parse(deadlineBy);
            String formattedDeadlineDate = deadlineByDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            todos.add(new Deadline(deadlineDescription, formattedDeadlineDate));
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDeadlineByException();
        }
    }

    private static void createNewEvent(ArrayList<Task> todos, String line)
            throws MissingEventFromException, MissingEventToException,
            MissingEventDescriptionException {
        if (!(line.contains("/from"))) {
            throw new MissingEventFromException();
        }
        if (!(line.contains("/to"))) {
            throw new MissingEventToException();
        }

        int fromSlashIndex = line.indexOf("/");
        String substringBeforeFrom = line.substring(0, fromSlashIndex + 1);
        int toSlashIndex = substringBeforeFrom.length()
                + line.substring(fromSlashIndex + 1).indexOf("/");
        String eventDescription = line.substring(EVENT_DESCRIPTION_START_INDEX, fromSlashIndex);
        if (eventDescription.isBlank()) {
            throw new MissingEventDescriptionException();
        }

        String eventFrom = line.substring(fromSlashIndex + EVENT_FROM_SPACE_LENGTH, toSlashIndex);
        if (eventFrom.isBlank()) {
            throw new MissingEventFromException();
        }

        try {
            String eventTo = line.substring(toSlashIndex + EVENT_TO_SPACE_LENGTH);
            if (eventTo.isBlank()) {
                throw new MissingEventToException();
            }
            todos.add(new Event(eventDescription, eventFrom, eventTo));
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingEventToException();
        }
    }
    private static String getTaskSymbol(Task currentTask) {
        String taskSymbol = null;
        switch(currentTask.getTaskType()) {
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
}
