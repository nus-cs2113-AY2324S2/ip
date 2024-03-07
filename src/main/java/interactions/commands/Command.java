package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public abstract class Command {
    protected static final String INDENT = "      ";
    protected String firstWord, line, taskDescription;

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    public void countTasks(ArrayList<Task> list) {
        int currSize = list.size();
        System.out.println(INDENT + "Now you have " + currSize + " task" + (currSize > 1 ? "s " : " ") + "in the list");
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

    public abstract void execute(TaskList list, Storage storage) throws IncompletePromptException, UnknownPromptException;
    public Command(){

    }
}
