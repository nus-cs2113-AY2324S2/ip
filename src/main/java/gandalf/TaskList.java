package gandalf;

import action.Deadline;
import action.Event;
import action.Task;
import action.ToDo;

import exception.IncompleteCommandException;
import exception.InvalidKeywordException;
import exception.InvalidTaskDeletionException;
import exception.InvalidTaskIndexException;
import exception.MissingDescriptionException;

import java.util.ArrayList;

public class TaskList {

    public static int insertIndex = 0;

    public static ArrayList<Task> initializeTasks() {
        return new ArrayList<>();
    }

    public static void deleteUserTasks(String userInput, ArrayList<Task> listTasks)
            throws InvalidTaskDeletionException {
        if (listTasks.isEmpty() || userInput.trim().length() == 6) {
            throw new InvalidTaskDeletionException();
        }
        
        int indexToDelete = Integer.parseInt(userInput.substring(6).trim());

        if (indexToDelete > listTasks.size()) {
            throw new InvalidTaskDeletionException();
        }

        Ui.printTaskIsDeletedMessage(listTasks, indexToDelete);
        listTasks.remove(indexToDelete - 1);
        insertIndex -= 1;
        Ui.printNumberOfTasks(insertIndex);
    }

    public static void insertUserTasks (String userInput, ArrayList<Task> listTasks)
            throws InvalidKeywordException, MissingDescriptionException,
            InvalidTaskIndexException, IncompleteCommandException {
        if (userInput.startsWith("todo")) {
            handleToDoTasks(userInput, listTasks, insertIndex);
        } else if (userInput.startsWith("deadline")) {
            handleDeadlineTasks(userInput, listTasks, insertIndex);
        } else if (userInput.startsWith("event")) {
            handleEventTasks(userInput, listTasks, insertIndex);
        } else {
            throw new InvalidKeywordException();
        }
        insertIndex += 1;
    }

    public static void handleEventTasks(String userInput, ArrayList<Task> listTasks, int insertIndex)
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
            Ui.printTaskIsAddedMessage(listTasks, insertIndex);
            insertIndex += 1;
            Ui.printNumberOfTasks(insertIndex);
        }
    }

    public static void handleDeadlineTasks(String userInput, ArrayList<Task> listTasks, int insertIndex)
            throws IncompleteCommandException {
        if (!userInput.contains("/by")) {
            throw new IncompleteCommandException();
        }
        else {
            Parser parser = new Parser(userInput);
            String deadlineItem = parser.getDeadlineItem();
            String deadlineDueBy = parser.getDeadlineDueBy();

            listTasks.add(insertIndex, new Deadline(deadlineItem, deadlineDueBy));
            Ui.printTaskIsAddedMessage(listTasks, insertIndex);
            insertIndex += 1;
            Ui.printNumberOfTasks(insertIndex);
        }
    }

    public static void handleToDoTasks(String userInput, ArrayList<Task> listTasks, int insertIndex)
            throws MissingDescriptionException {
        if (userInput.trim().length() == 4){
            throw new MissingDescriptionException();
        } else {
            Parser parser = new Parser(userInput);
            String toDoItem = parser.getToDoItem();

            listTasks.add(insertIndex, new ToDo(toDoItem));
            Ui.printTaskIsAddedMessage(listTasks, insertIndex);
            insertIndex += 1;
            Ui.printNumberOfTasks(insertIndex);
        }
    }

    public static void handleTasksMarkings(String userInput, ArrayList<Task> listTasks)
            throws InvalidTaskIndexException {
        if (userInput.startsWith("mark ")) {
            int indexToMark = Integer.parseInt(userInput.substring(5).trim());
            if (indexToMark >= 1 && indexToMark <= listTasks.size() && listTasks.get(indexToMark - 1) != null) {
                listTasks.get(indexToMark - 1).markAsDone();
                Ui.printTaskIsMarkedMessage(listTasks, indexToMark);
            } else {
                throw new InvalidTaskIndexException();
            }
        } else {
            int indexToUnmark = Integer.parseInt(userInput.substring(7).trim());
            if (indexToUnmark >= 1 && indexToUnmark <= listTasks.size() && listTasks.get(indexToUnmark - 1) != null) {
                listTasks.get(indexToUnmark - 1).unmarkAsDone();
                Ui.printTaskIsUnmarkedMessage(listTasks, indexToUnmark);
            } else {
                throw new InvalidTaskIndexException();
            }
        }
    }

}
