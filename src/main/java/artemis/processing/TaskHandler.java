package artemis.processing;

import artemis.tasks.*;
import artemis.errors.Errors;

import java.util.ArrayList;

/**
 * Handles the task list
 */
public class TaskHandler {
    public ArrayList<Task> taskList;

    /**
     * Initializes the TaskHandler class
     *
     * @param taskList The ArrayList of Tasks to be initialized
     */
    public TaskHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates and returns a to do class
     *
     * @param userInput Raw user input to be parsed
     * @return Created to do class
     * @throws Errors.InvalidTodoException If the given input does not contain the proper parameters for the to do command
     */
    public ToDo createToDo(String userInput) throws Errors.InvalidTodoException {
        String taskName = Parser.parseToDo(userInput);

        return new ToDo(taskName);
    }

    /**
     * Creates and returns a Deadline class
     *
     * @param userInput Raw user input to be parsed
     * @return Created Deadline class
     * @throws Errors.InvalidDeadlineException If the given input does not contain the proper parameters for the deadline command
     */
    public Deadline createDeadline(String userInput) throws Errors.InvalidDeadlineException {
        String[] deadlineDetails = Parser.parseDeadline(userInput);

        return new Deadline(deadlineDetails[0], deadlineDetails[1]);
    }

    /**
     * Creates and returns an Event class
     *
     * @param userInput Raw user input to be parsed
     * @return Created Event class
     * @throws Errors.InvalidEventException If the given input does not contain the proper parameters for the event command
     */
    public Event createEvent(String userInput) throws Errors.InvalidEventException {
        String[] eventDetails = Parser.parseEvent(userInput);

        return new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
    }

    /**
     * Marks or unmarks a given item on the list
     *
     * @param markItem Integer index of the item to be marked or unmarked
     * @param isMarked True if to be marked, false if to be unmarked
     */
    public void markUnmarkItem(int markItem, boolean isMarked) {
        taskList.get(markItem).markAsDone(isMarked);

        System.out.printf("alright! i have set \"%s\" as %s%s",
                taskList.get(markItem).getTaskName(), isMarked ? "complete" : "incomplete", System.lineSeparator());
    }

    /**
     * Deletes a task with the given index on the list
     *
     * @param deleteItem Integer index of the item to be deleted
     * @throws Errors.TaskNotFoundException If the index given exceeds the number of tasks on the list
     */
    public void deleteTask(int deleteItem) throws Errors.TaskNotFoundException {
        try {
            String taskName = taskList.get(deleteItem).getTaskName();
            taskList.remove(deleteItem);
            System.out.printf("the task \"%s\" has been successfully deleted%s", taskName, System.lineSeparator());
        } catch (IndexOutOfBoundsException e) {
            throw new Errors.TaskNotFoundException();
        }
    }
}
