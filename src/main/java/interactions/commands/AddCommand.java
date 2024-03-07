package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.TaskList;
import tasks.*;

import java.io.IOException;

public class AddCommand extends Command {
    public AddCommand() {

    }
    private Task newTask;
    public void setNewTask() throws IncompletePromptException, UnknownPromptException {
        this.newTask = parseTaskFromCommand();
    }
    public Task getNewTask() {
        return newTask;
    }
    /*public AddCommand() throws IncompletePromptException, UnknownPromptException {
        this.newTask = parseTaskFromCommand();
    }*/
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
        //String taskDescription = extractTaskOrDate(line, firstWord);
        if (taskDescription.isEmpty()) {
            throw new IncompletePromptException();
        }
        setNewTask();
        taskList.addToList(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.print(INDENT);
        newTask.print();
        taskList.countTasks();
    }

    /**
     *
     * Executes command of adding task to task list.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param storage Storage handler that saves to file.
     * @throws IncompletePromptException
     * @throws UnknownPromptException
     */
    @Override
    public void execute(TaskList taskList, Storage storage)
            throws IncompletePromptException, UnknownPromptException {
        addNewTask(taskList);
        setLastCommand(this);
        try {
            storage.saveToFile("data/list.txt", taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
