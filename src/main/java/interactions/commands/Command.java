package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import interactions.Ui;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command that contains the input line, first word and description.
 * Acts as parent class for other command classes, such as commands to add task,
 * delete task, rename chatbot etc.
 */
public abstract class Command {
    protected static final String INDENT = "      ";
    protected String firstWord;
    protected String line;
    protected String taskDescription;

    public String getTaskDescription() {
        return taskDescription;
    }

    /** Assigns input description to this class's description object. */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /** Assigns input first word to this class's first word object, such as delete, mark and deadline.*/
    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
    /** Assign entire input prompt line by user, which is a combination of description and first word */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Returns the task description or dates from specific keywords after separating them. Used to further
     * separates the dates from deadline and event from the task description itself.
     *
     * @param line Initial input prompt line by user.
     * @param keyword Word used to search and separate itself from the description or date.
     * @return Task description or date.
     * @throws IncompletePromptException If an expected "next" word for the keyword does not show up.
     */
    protected String extractTaskOrDate(String line, String keyword) throws IncompletePromptException {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord; // Used for any commands that require a 'next' word
        switch (keyword) {
        case "event":
            nextWord = " from ";
            break;
        case "deadline":
            nextWord = " by ";
            break;
        case "from":
            nextWord = " to ";
            break;
        default:
            nextWord = null;
        }
        if (nextWord != null) {
            int nextIndex = line.indexOf(nextWord, index);
            if (nextIndex != -1) {
                return line.substring(index, nextIndex).trim();
            } else {
                throw new IncompletePromptException(true);
            }
        }
        return line.substring(index).trim();
    }

    /**
     * Executes the command, such as adding or deleting, given the task list and storage handler.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param ui UI that records every task description chatbot session.
     * @param storage Storage handler that saves to file.
     * @throws IncompletePromptException If the information given from the line is incomplete.
     * @throws UnknownPromptException If the prompt line inputted does not match the commands.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IncompletePromptException, UnknownPromptException;
    public Command(){

    }
}