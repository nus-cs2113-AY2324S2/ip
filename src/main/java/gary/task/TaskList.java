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

/**
 * TaskList class contains methods to add or modify tasks in the ArrayList.
 */
public class TaskList {
    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;

    /**
     * list out all the task available in the ArrayList by iterating through every element
     * in the ArrayList todos.
     *
     * @param todosCount number of tasks in the array list.
     * @param todos array list that stores and manages the task while programme is running.
     */
    public static void processList(int todosCount, ArrayList<Task> todos) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < todosCount; i += 1) {
            todos.get(i).printTask(i);
        }
    }

    /**
     * Process users' command related to adding new task, including todo, deadline, and event.
     * Throws exception if users' command is unknown or missing description.
     *
     * @param command type of task user wants to add.
     * @param todos array list that stores and manages the task while programme is running.
     * @param line user input to the terminal.
     * @throws UnknownCommandException if command given by user is unknown to programme.
     * @throws MissingTodoDescriptionException if description for todo is empty.
     * @throws MissingDeadlineByException if no "/by" or no date given for "/by" in deadline.
     * @throws MissingDeadlineDescriptionException if description for deadline is empty.
     * @throws DateTimeParseException if date given is not in yyyy-mm-dd format.
     * @throws MissingEventFromException if no "/from" or no description given for "/from" in event.
     * @throws MissingEventToException if no "/to" or no description given for "/to" in event.
     * @throws MissingEventDescriptionException if description for event is empty.
     */
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

    /**
     * Process users' command to mark the available task as done. Marked task will be printed
     * out to give confirmation to users.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     */
    public static void processMark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.markAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
    }

    /**
     * Process users' command to unmark available task as not done. Task unmaerked will be printed
     * out to give confirmation to users.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     */
    public static void processUnmark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.unmarkAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
    }

    /**
     * Process users' command to delete available task. Deletion of task is based on the number
     * given by users from the list. Deleted task will be printed out and updated number of task
     * is shown.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param lineWords user input to the terminal, that has been split into array.
     * @param todosCount number of tasks in the array list.
     */
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

    /**
     * Process users' command to find tasks based on a given keyword. Tasks found to have the
     * keyword is printed.
     *
     * @param keyword keyword to find in tasks description.
     * @param todos array list that stores and manages the task while programme is running.
     */
    public static void processFind(String keyword, ArrayList<Task> todos) {
        int findTaskNumber = 0;
        System.out.println("Here are the matching tasks in your list:");

        for (Task currentTask : todos) {
            if (!(currentTask.getTaskDescription().contains(keyword))) {
                continue;
            }
            currentTask.printTask(findTaskNumber);
            findTaskNumber += 1;
        }
    }

    /**
     * New todo is created by calling Todo class and added to the ArrayList. If todo description
     * is missing from users' input, error is caught and more detailed exception is thrown to
     * be handled.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param line user input to the terminal.
     * @throws MissingTodoDescriptionException if description for todo is empty.
     */
    private static void createNewTodo(ArrayList<Task> todos, String line)
            throws MissingTodoDescriptionException {
        try {
            todos.add(new Todo(line.substring(TODO_DESCRIPTION_START_INDEX)));
        } catch(StringIndexOutOfBoundsException e) {
            throw new MissingTodoDescriptionException();
        }
    }

    /**
     * New deadline is created by calling Deadline class and added to the ArrayList. If deadline
     * description is not provided, by description is not provided or no "/by" in the input,
     * error is caught and more detailed exception is thrown to be handled.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param line user input to the terminal.
     * @throws MissingDeadlineByException if no "/by" or no date given for "/by" in deadline.
     * @throws MissingDeadlineDescriptionException if description for deadline is empty.
     * @throws DateTimeParseException if date given is not in yyyy-mm-dd format.
     */
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

    /**
     * New event is created by calling Event class and added to the ArrayList. If event description
     * is not provided, no "/from" and its description, or no "/to" and its description, error is
     * caught and more detailed exception is thrown to be handled.
     *
     * @param todos array list that stores and manages the task while programme is running.
     * @param line user input to the terminal.
     * @throws MissingEventFromException if no "/from" or no description given for "/from" in event.
     * @throws MissingEventToException if no "/to" or no description given for "/to" in event.
     * @throws MissingEventDescriptionException if description for event is empty.
     */
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

    /**
     * Take in the task, and return the task type symbol.
     *
     * @param currentTask a task of todo, deadline, or event.
     * @return symbol of task in string.
     */
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
