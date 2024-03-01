package logic;

import exceptions.IllegalNumberOfArguments;
import exceptions.Confusion;
import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import exceptions.InvalidTaskIndex;
import tasks.Deadline;
import tasks.Event;
import tasks.ListKeeper;
import tasks.ToDo;
import ui.Keywords;
import ui.UserInterface;

import java.util.Arrays;

public class LogicManager {
    private final ListKeeper listKeeper;
    private final UserInterface userInterface;

    public LogicManager(ListKeeper listKeeper, UserInterface userInterface) {
        this.listKeeper = listKeeper;
        this.userInterface = userInterface;
    }

    private void executeList() {
        this.listKeeper.printList();
    }

    /**
     * Parses the input to get the task index.
     * @param taskIndexString the input to be parsed
     * @return the task index
     * @throws InvalidTaskIndex if the input is not an integer
     */
    private int getTaskIndex (String taskIndexString) throws InvalidTaskIndex {
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndex("You must specify an integer as the task index");
        }
    }

    private void executeMark (String[] words) throws InvalidTaskIndex {
        String taskIndexString = words[1];
        int taskIndex = getTaskIndex(taskIndexString);
        boolean isCompleted = words[0].equals(Keywords.MARK);
        this.listKeeper.processMark(taskIndex, isCompleted);
    }

    private void executeDelete (String[] words) throws InvalidTaskIndex {
        String taskIndexString = words[1];
        int taskIndex = getTaskIndex(taskIndexString);
        this.listKeeper.deleteTask(taskIndex);
    }

    private void executeToDo(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        ToDo todo = ToDo.getTask(currentInput);
        this.listKeeper.addToList(todo);
    }

    private void executeDeadline(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        Deadline deadline = Deadline.getTask(currentInput);
        this.listKeeper.addToList(deadline);
    }

    private void executeEvent(String currentInput) throws EmptyTaskDescription, InvalidTaskArguments {
        Event event = Event.getTask(currentInput);
        this.listKeeper.addToList(event);
    }

    private void executeFind(String[] words) {
        String[] keywordsToFind = Arrays.copyOfRange(words, 1, words.length);
        this.listKeeper.printMatchingTasks(keywordsToFind);
    }

    private void executeCommand (String currentInput)
        throws IllegalNumberOfArguments, InvalidTaskIndex,
            EmptyTaskDescription, InvalidTaskArguments, Confusion {

        String[] words = currentInput.split(" ");
        Keywords.verifyInputSize(words);

        String commandType = words[0];
        switch (commandType) {
        case Keywords.LIST:
            executeList();
            break;

        case Keywords.MARK:
        case Keywords.UNMARK:
            executeMark(words);
            break;

        case Keywords.DELETE:
            executeDelete(words);
            break;

        case Keywords.TODO:
            executeToDo(currentInput);
            break;

        case Keywords.DEADLINE:
            executeDeadline(currentInput);
            break;

        case Keywords.EVENT:
            executeEvent(currentInput);
            break;

        case Keywords.FIND:
            executeFind(words);
            break;

        default:
            throw new Confusion();
        }
    }

    /**
     * Processes the input command and executes the corresponding action
     * Prints error messages if the input is invalid
     * @param currentInput the input command
     */
    public void processCommand(String currentInput) {
        if (currentInput.isEmpty()) {
            return;
        }
        try {
            executeCommand(currentInput);
        } catch (IllegalNumberOfArguments | InvalidTaskIndex |
                 EmptyTaskDescription | InvalidTaskArguments | Confusion e) {
            this.userInterface.print(e.getMessage());
        }
    }
}
