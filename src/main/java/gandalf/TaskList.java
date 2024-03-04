package gandalf;

import action.Deadline;
import action.Event;
import action.Task;
import action.ToDo;

import exception.EmptyFindException;
import exception.IncompleteCommandException;
import exception.InvalidDeadlineFormatException;
import exception.InvalidKeywordException;
import exception.InvalidTaskDeletionException;
import exception.InvalidTaskIndexException;
import exception.MissingDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * The TaskList class manages the user's to-do list commands.
 */
public class TaskList {
    /** The index used to keep track of the current position for adding tasks in the list. */
    public static int insertIndex = 0;

    /**
     * Deletes the user's task specified by the index provided in the user input.
     *
     * @param userInput The user input containing the task index to delete.
     * @param listTasks The list of tasks.
     * @throws InvalidTaskDeletionException If the task deletion operation is invalid.
     */
    public static void deleteUserTasks(String userInput, ArrayList<Task> listTasks)
            throws InvalidTaskDeletionException {
        if (listTasks.isEmpty() || userInput.trim().length() == 6) {
            throw new InvalidTaskDeletionException();
        }
        
        int indexToDelete = Parser.parseIndex(userInput);

        if (indexToDelete > listTasks.size() || indexToDelete < 0) {
            throw new InvalidTaskDeletionException();
        }

        Ui.printTaskIsDeletedMessage(listTasks, indexToDelete);
        listTasks.remove(indexToDelete - 1);
        insertIndex -= 1;
        Ui.printNumberOfTasks(insertIndex);
    }

    /**
     * Inserts user tasks into the list based on the command in the user input.
     *
     * @param userInput The user input containing the task to add.
     * @param listTasks The list of tasks.
     * @param hideInput Flag indicating whether to hide the input message.
     * @throws InvalidKeywordException If the keyword in the user input is invalid.
     * @throws MissingDescriptionException If the task description is missing.
     * @throws InvalidTaskIndexException If the task index is invalid.
     * @throws IncompleteCommandException If the command in the user input is incomplete.
     * @throws InvalidDeadlineFormatException If the deadline format in the user input is invalid.
     */
    public static void insertUserTasks (String userInput, ArrayList<Task> listTasks, boolean hideInput)
            throws InvalidKeywordException, MissingDescriptionException,
            InvalidTaskIndexException, IncompleteCommandException, InvalidDeadlineFormatException {
        if (userInput.startsWith("todo")) {
            handleToDoTasks(userInput, listTasks, insertIndex, hideInput);
        } else if (userInput.startsWith("deadline")) {
            handleDeadlineTasks(userInput, listTasks, insertIndex, hideInput);
        } else if (userInput.startsWith("event")) {
            handleEventTasks(userInput, listTasks, insertIndex, hideInput);
        } else {
            throw new InvalidKeywordException();
        }
        insertIndex += 1;
    }

    /**
     * Handles event tasks specified in the user input and adds them to the task list.
     *
     * @param userInput The user input containing the event task details.
     * @param listTasks The list of tasks.
     * @param insertIndex The index to insert the event task.
     * @param hideInput Flag indicating whether to hide the input message.
     * @throws IncompleteCommandException If the event task command is incomplete.
     */
    public static void handleEventTasks(String userInput, ArrayList<Task> listTasks, int insertIndex, boolean hideInput)
            throws IncompleteCommandException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new IncompleteCommandException();
        }
        else {
            Parser parser = new Parser(userInput);
            String eventItem = parser.getEventItem();
            String eventFrom = parser.getEventFrom();
            String eventTo = parser.getEventTo();

            listTasks.add(insertIndex, new Event(eventItem, eventFrom, eventTo));
            if (!hideInput) {
                Ui.printTaskIsAddedMessage(listTasks, insertIndex);
            }
            insertIndex += 1;
            if (!hideInput) {
                Ui.printNumberOfTasks(insertIndex);
            }
        }
    }

    /**
     * Handles deadline tasks specified in the user input and adds them to the task list.
     *
     * @param userInput The user input containing the deadline task details.
     * @param listTasks The list of tasks.
     * @param insertIndex The index to insert the deadline task.
     * @param hideInput Flag indicating whether to hide the input message.
     * @throws IncompleteCommandException If the deadline task command is incomplete.
     * @throws InvalidDeadlineFormatException If the deadline format is invalid.
     */
    public static void handleDeadlineTasks(String userInput, ArrayList<Task> listTasks, int insertIndex, boolean hideInput)
            throws IncompleteCommandException, InvalidDeadlineFormatException {
        if (!userInput.contains("/by")) {
            throw new IncompleteCommandException();
        }
        else {
            Parser parser = new Parser(userInput);
            String deadlineItem = parser.getDeadlineItem();
            String deadlineDueBy = parser.getDeadlineDueBy();

            try {
                LocalDate deadlineDate = LocalDate.parse(deadlineDueBy);
                String newDeadLineDateFormat = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

                listTasks.add(insertIndex, new Deadline(deadlineItem, newDeadLineDateFormat));
                if (!hideInput) {
                    Ui.printTaskIsAddedMessage(listTasks, insertIndex);
                }
                insertIndex += 1;
                if (!hideInput) {
                    Ui.printNumberOfTasks(insertIndex);
                }
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineFormatException();
            }
        }
    }

    /**
     * Handles to-do tasks specified in the user input and adds them to the task list.
     *
     * @param userInput The user input containing the to-do task details.
     * @param listTasks The list of tasks.
     * @param insertIndex The index to insert the to-do task.
     * @param hideInput Flag indicating whether to hide the input message.
     * @throws MissingDescriptionException If the to-do task description is missing.
     */
    public static void handleToDoTasks(String userInput, ArrayList<Task> listTasks, int insertIndex, boolean hideInput)
            throws MissingDescriptionException {
        if (userInput.trim().length() == 4){
            throw new MissingDescriptionException();
        } else {
            Parser parser = new Parser(userInput);
            String toDoItem = parser.getToDoItem();

            listTasks.add(insertIndex, new ToDo(toDoItem));
            if (!hideInput) {
                Ui.printTaskIsAddedMessage(listTasks, insertIndex);
            }
            insertIndex += 1;
            if (!hideInput) {
                Ui.printNumberOfTasks(insertIndex);
            }
        }
    }

    /**
     * Handles tasks' markings as done/not done based on user input.
     *
     * @param userInput The user input containing the command to mark/unmark tasks.
     * @param listTasks The list of tasks.
     * @param hideInput Flag indicating whether to hide the input message.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public static void handleTasksMarkings(String userInput, ArrayList<Task> listTasks, boolean hideInput)
            throws InvalidTaskIndexException {
        if (userInput.startsWith("mark ")) {
            int indexToMark = Parser.parseIndex(userInput);
            if (isValidMarking(listTasks, indexToMark)) {
                listTasks.get(indexToMark - 1).markAsDone();
                if (!hideInput) {
                    Ui.printTaskIsMarkedMessage(listTasks, indexToMark);
                }
            } else {
                throw new InvalidTaskIndexException();
            }
        } else {
            int indexToUnmark = Parser.parseIndex(userInput);
            if (isValidMarking(listTasks, indexToUnmark)) {
                listTasks.get(indexToUnmark - 1).unmarkAsDone();
                if (!hideInput) {
                    Ui.printTaskIsUnmarkedMessage(listTasks, indexToUnmark);
                }
            } else {
                throw new InvalidTaskIndexException();
            }
        }
    }

    private static boolean isValidMarking(ArrayList<Task> listTasks, int indexToMark) {
        return indexToMark >= 1 && indexToMark <= listTasks.size() && listTasks.get(indexToMark - 1) != null;
    }

    /**
     * Finds and prints tasks matching the search query from the task list.
     *
     * @param userInput The user input containing the search query.
     * @param listTasks The list of tasks.
     * @throws EmptyFindException If the search query is empty.
     */
    public static void findUserTasks(String userInput, ArrayList<Task> listTasks) throws EmptyFindException {
        if (userInput.trim().equals("find")) {
            throw new EmptyFindException();
        }
        String taskToFind = Parser.parseTaskToFind(userInput);
        Ui.printMatchingListOfTasks (taskToFind, listTasks);
    }
}
