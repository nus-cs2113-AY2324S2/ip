package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.*;

/**
 * Represents a command that contains the input line, first word and description.
 * Acts as parent class for other command classes, such as commands to add task,
 * delete task, rename chatbot etc.
 */
public abstract class Command {
    protected static Command lastCommand;
    public void printLastCommand() {
        System.out.println("Last Command: " + lastCommand.firstWord);
    }
    public Command getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(Command lastCommand) {
        this.lastCommand = lastCommand;
    }
    protected static final String INDENT = "      ";
    protected String firstWord, taskDescription;

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getFirstWord() {
        return firstWord;
    }

    /** Assigns input description to this class's description object. */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /** Assigns input first word to this class's first word object, such as delete, mark and deadline. */
    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
    protected String extractTaskOrDate(String line, String keyword) throws IncompletePromptException {
        int index = line.indexOf(keyword) + keyword.length();
        String nextWord; // Used for any commands that require a 'next' word
        switch (keyword) {
        case "event":
            nextWord = "from";
            break;
        case "deadline":
            nextWord = "by";
            break;
        case "from":
            nextWord = "to";
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
    public Task parseTaskFromCommand() throws IncompletePromptException, UnknownPromptException {
        //String firstWord = command.getFirstWord();
        //String taskDescription = command.getTaskDescription();
        Task newTask;
        switch (firstWord) {
        case "todo":
            newTask = new ToDo(taskDescription);
            ((ToDo)newTask).setHaveToDo(true);
            newTask.setTaskType("T");
            break;
        case "deadline":
            newTask = new Deadline(taskDescription);
            String deadline = extractTaskOrDate(taskDescription, "by");
            if (deadline.isEmpty()) {
                throw new IncompletePromptException();
            }
            ((Deadline)newTask).setDeadline(deadline);
            newTask.setTaskType("D");
            break;
        case "event":
            newTask = new Event(taskDescription);
            String dateFrom = extractTaskOrDate(taskDescription, "from");
            String dateTo = extractTaskOrDate(dateFrom, "to");
            if (dateFrom.isEmpty() || dateTo.isEmpty()) {
                throw new IncompletePromptException();
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
        return newTask;
    }
    public abstract void execute(TaskList taskList, Storage storage)
            throws IncompletePromptException, UnknownPromptException;
    public Command(){

    }
}
