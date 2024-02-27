package artemis.processing;

import artemis.tasks.*;
import artemis.errors.Errors;

import java.util.ArrayList;

public class TaskHandler {
    public ArrayList<Task> taskList;

    public TaskHandler(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ToDo createToDo(String userInput) throws Errors.InvalidTodoException {
        String taskName = Parser.parseToDo(userInput);

        return new ToDo(taskName);
    }

    public Deadline createDeadline(String userInput) throws Errors.InvalidDeadlineException {
        String[] deadlineDetails = Parser.parseDeadline(userInput);

        return new Deadline(deadlineDetails[0], deadlineDetails[1]);
    }

    public Event createEvent(String userInput) throws Errors.InvalidEventException {
        String[] eventDetails = Parser.parseEvent(userInput);

        return new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
    }

    public void markUnmarkItem(int markItem, boolean isMarked) {
        taskList.get(markItem).markAsDone(isMarked);

        System.out.printf("alright! i have set \"%s\" as %s%s",
                taskList.get(markItem).getTaskName(), isMarked ? "complete" : "incomplete", System.lineSeparator());
    }

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
