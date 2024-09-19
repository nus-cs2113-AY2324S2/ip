package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import interactions.Ui;
import tasks.TaskList;
import tasks.*;

import java.io.IOException;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    /**
     *
     * Adds a new task to the task list based on the input command prompt.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @throws IncompletePromptException If the prompt only contains the first word but has
     *                                   not enough information in the description.
     * @throws UnknownPromptException If the first word of the prompt is not a valid command.
     */
    public void addNewTask(TaskList taskList)
            throws IncompletePromptException, UnknownPromptException {
        String taskDescription = extractTaskOrDate(line, firstWord);
        if (taskDescription.isEmpty()) {
            throw new IncompletePromptException(true);
        }
        Task newTask;
        switch (firstWord) {
        case "todo":
            newTask = new ToDo(taskDescription);
            ((ToDo)newTask).setHaveToDo(true);
            newTask.setTaskType("T");
            break;
        case "deadline":
            newTask = new Deadline(taskDescription);
            String deadline = extractTaskOrDate(line, "by");
            if (deadline.isEmpty()) {
                throw new IncompletePromptException(true);
            }
            ((Deadline)newTask).setDeadline(deadline);
            newTask.setTaskType("D");
            break;
        case "event":
            newTask = new Event(taskDescription);
            String dateFrom = extractTaskOrDate(line, "from");
            String dateTo = extractTaskOrDate(line, "to");
            if (dateFrom.isEmpty() || dateTo.isEmpty()) {
                throw new IncompletePromptException(true);
            } else {
                ((Event)newTask).setEventFrom(dateFrom);
                ((Event)newTask).setEventTo(dateTo);
                ((Event)newTask).setEvent(true);
                newTask.setTaskType("E");
            }
            break;
        default:
            throw new UnknownPromptException();
        }
        taskList.addToList(newTask);
        newTask.print();
        taskList.countTasks();
    }

    /**
     * Executes command of adding task to task list. Saves to file by writing the task to file.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param ui UI that records every task description chatbot session.
     * @param storage Storage handler that saves to file.
     * @throws IncompletePromptException If prompt is lacking required information.
     * @throws UnknownPromptException If inputted prompt does not match commands
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws IncompletePromptException, UnknownPromptException {
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        addNewTask(taskList);
        try {
            storage.saveToFile("list.txt", taskList);
            if (!ui.isTextFileSavedMentioned()) {
                ui.setTextFileSavedMentionedTrue();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when adding task: " + e.getMessage());
        }
    }
}