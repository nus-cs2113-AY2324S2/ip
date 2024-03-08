package GermaBot;

import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Contains the main logic for handling user commands in the GermaBot application.
 */
public class Logic {

    public static TaskManager taskManager = new TaskManager();

    /**
     * Returns the index of a task in the arraylist that is specified in the input.
     * @param input The user input.
     * @return The index parsed from the input.
     * @throws IllegalArgumentException If the input format is invalid, such as if there is no index provided.
     */
    public static int getIdx(String input) throws IllegalArgumentException {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex == input.length() - 1) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
    }

    /**
     * Creates a new ToDo task based on user input and adds it to the task list.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input containing the task description.
     * @throws EmptyTaskException If the task description is empty.
     * @throws IOException If there is an error saving the task to the file.
     */
    public static void createTodo(ArrayList<Task> toDoList, String input) throws EmptyTaskException, IOException {
        int idxBetweenTodoAndDescription = 5;
        String toDoTask = input.substring(input.indexOf("todo ") + idxBetweenTodoAndDescription);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addTodo(toDoList, toDoTask);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveTodo(new ToDo(toDoTask), 'T');
        UI.printCreateTodoMessage(toDoTask);
    }

    /**
     * Creates a new Deadline task based on user input and adds it to the task list.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input containing the task description and deadline.
     * @throws EmptyTaskException If the task description is empty.
     * @throws MissingDeadlineException If the deadline is missing.
     * @throws IOException If there is an error saving the task to the file.
     */
    public static void createDeadline(ArrayList<Task> toDoList, String input) throws EmptyTaskException, MissingDeadlineException, IOException {
        String description = input.replaceFirst("deadline ", "");
        if (description.equals("deadline")) {
            throw new EmptyTaskException();
        }
        int idxOfEndDate = description.indexOf("/");
        if (idxOfEndDate == -1) {
            throw new MissingDeadlineException();
        }
        int idxToDateFromEndDate = 4;
        String date = description.substring(idxOfEndDate + idxToDateFromEndDate);
        String toDoTask = description.substring(0, idxOfEndDate - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addDeadline(toDoList, description, idxOfEndDate, date);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveDeadline(new Deadline(description.substring(0, idxOfEndDate - 1), date), 'D');
        UI.printCreateDeadlineMessage(toDoTask, date);
    }

    /**
     * Creates a new Event task based on user input and adds it to the task list.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input containing the task description, start date, and end date.
     * @throws EmptyTaskException If the task description is empty.
     * @throws MissingDeadlineException If the end date is missing.
     * @throws MissingStartDateException If the start date is missing.
     * @throws IOException If there is an error saving the task to the file.
     */
    public static void createEvent(ArrayList<Task> toDoList, String input) throws EmptyTaskException, MissingDeadlineException,
            MissingStartDateException, IOException {
        String description = input.replaceFirst("event ", "");
        int idxOfFrom = description.indexOf("/from");
        if (idxOfFrom == -1) {
            throw new EmptyTaskException();
        }
        int idxOfBy = description.indexOf("/to");
        if (idxOfBy == -1) {
            throw new MissingDeadlineException();
        }
        int idxFromFromToStartDate = 6;
        String startDate = description.substring(idxOfFrom + idxFromFromToStartDate, idxOfBy - 1);
        if (startDate.isBlank()) {
            throw new MissingStartDateException();
        }
        int idxFromByToEndDate = 4;
        String endDate = description.substring(idxOfBy + idxFromByToEndDate);
        if (endDate.isBlank()) {
            throw new MissingDeadlineException();
        }
        String toDoTask = description.substring(0, idxOfFrom - 1);
        if (toDoTask.isBlank()) {
            throw new EmptyTaskException();
        }
        taskManager.addEvent(toDoList, description, idxOfFrom, startDate, endDate);
        Task.setNoOfTask(Task.getNoOfTask() + 1);
        Storage.saveEvent(new Event(description.substring(0, idxOfFrom - 1), startDate, endDate), 'E');
        UI.printCreateEventMessage(toDoTask, startDate, endDate);
    }

    /**
     * Deletes a task from the task list based on the index specified in the user input.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input containing the index of the task to be deleted.
     * @throws TaskNotFoundException If the specified task index is not found.
     */
    public static void deleteTask(ArrayList<Task> toDoList, String input) throws TaskNotFoundException {
        try {
            int idxToDelete = getIdx(input);
            if (idxToDelete >= Task.getNoOfTask()) {
                throw new TaskNotFoundException();
            } else {
                UI.deleteTask(toDoList, idxToDelete);
                Task.setNoOfTask(Task.getNoOfTask() - 1);
            }
        } catch (IllegalArgumentException e) {
            UI.printIllegalArgumentException();
        }
    }

    /**
     * Processes user input to create, delete, or manage tasks.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input command.
     */
    public static void createTask(ArrayList<Task> toDoList, String input) {
        if (input.startsWith("delete")) {
            try {
                deleteTask(toDoList, input);
            } catch (TaskNotFoundException e) {
                try {
                    int idx = getIdx(input);
                    UI.printTaskNotFoundException(idx);
                } catch (IllegalArgumentException f) {
                    UI.printIllegalArgumentException();
                }
            }
        } else if (input.startsWith("todo")) {
            try {
                createTodo(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (IOException e) {
                UI.printIOException();
            }
        } else if (input.startsWith("deadline")) {
            try {
                createDeadline(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        } else if (input.startsWith("event")) {
            try {
                createEvent(toDoList, input);
            } catch (EmptyTaskException e) {
                UI.printEmptyTaskException();
            } catch (MissingStartDateException e) {
                UI.printMissingStartDateException();
            } catch (MissingDeadlineException e) {
                UI.printMissingDeadlineException();
            } catch (IOException e) {
                UI.printIOException();
            }
        }
    }

    /**
     * Searches for tasks in the task list that match the keyword specified in the user input.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input containing the search keyword.
     */
    public static void findTask(ArrayList<Task> toDoList, String input) {
        int counter = 1;
        int idxBetweenFindAndDescription = 5;
        String taskToFind = input.substring(input.indexOf("find ") + idxBetweenFindAndDescription);
        if (taskToFind.isBlank()) {
            throw new IllegalArgumentException();
        }
        UI.printFindingMessage(taskToFind);
        for (int i = 0; i < Task.getNoOfTask(); i++) {
            if (toDoList.get(i).getDescription().contains(taskToFind)) {
                UI.printTask(toDoList, counter, i);
                counter++;
            }
        }
        if (counter == 1) {
            UI.printTaskNotFoundMessage(taskToFind);
        }
    }

    /**
     * Interprets and executes the command specified in the user input.
     *
     * @param toDoList The list of tasks in the form of an ArrayList.
     * @param input The user input command.
     * @throws UnknownInputException If the input command is not recognized.
     */
    public static void readCommand(ArrayList<Task> toDoList, String input) throws UnknownInputException {
        if (input.startsWith("delete") || input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            createTask(toDoList, input);
        } else if (input.startsWith("list")) {
            if (toDoList.isEmpty()) {
                UI.printListEmptyMessage();
            } else {
                UI.printTaskList(toDoList);
            }
        } else if (input.startsWith("unmark")) {
            try {
                int idx = getIdx(input);
                taskManager.markTaskUndone(toDoList, idx);
                UI.printMarkUndone(idx, toDoList);
            } catch (IllegalArgumentException e) {
                UI.printIllegalArgumentException();
            }
        } else if (input.startsWith("mark")) {
            try {
                int idx = getIdx(input);
                taskManager.markTaskDone(toDoList, idx);
                UI.printMarkDone(idx, toDoList);
            } catch (IllegalArgumentException e) {
                UI.printIllegalArgumentException();
            }
        } else if (input.startsWith("find")) {
            try {
                findTask(toDoList, input);
            } catch (IllegalArgumentException e) {
                UI.printIllegalArgumentException();
            }
        } else {
            throw new UnknownInputException();
        }
    }
}
