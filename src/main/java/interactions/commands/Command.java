package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
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

    public String getFirstWord() {
        return firstWord;
    }

    /** Assigns input first word to this class's first word object, such as delete, mark and deadline.*/
    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

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
                throw new IncompletePromptException();
            }
        }
        return line.substring(index).trim();
    }

    public abstract void execute(TaskList taskList, Storage storage) throws IncompletePromptException, UnknownPromptException;
    public Command(){

    }
}