package gandalf;

import action.Deadline;
import action.Event;
import action.Task;
import action.ToDo;

import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static int insertIndex = 0;

    public static void deleteUserTasks(String userInput, ArrayList<Task> listTasks)
            throws InvalidTaskDeletionException {
        if (listTasks.isEmpty() || userInput.trim().length() == 6) {
            throw new InvalidTaskDeletionException();
        }
        
        int indexToDelete = Parser.parseIndex(userInput);

        if (indexToDelete > listTasks.size()) {
            throw new InvalidTaskDeletionException();
        }

        Ui.printTaskIsDeletedMessage(listTasks, indexToDelete);
        listTasks.remove(indexToDelete - 1);
        insertIndex -= 1;
        Ui.printNumberOfTasks(insertIndex);
    }

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

}
