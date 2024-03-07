package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public class UndoCommand extends Command {
    public UndoCommand() {

    }
    Command commandToUndo;
    public int findIndexOfTask(Task task, TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        return list.indexOf(task);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IncompletePromptException, UnknownPromptException {
        Command lastCommand = getLastCommand();
        String firstWord = lastCommand.getFirstWord();
        String lastDescription = lastCommand.getTaskDescription();
        if (firstWord.contains("delete")) {
            String taskType = firstWord.substring(6);
            commandToUndo.setFirstWord(taskType);
            commandToUndo.setTaskDescription(lastDescription);
        }
        switch (firstWord) {
        case "todo":
        case "deadline":
        case "event":
            commandToUndo = new DeleteCommand();
            commandToUndo.setFirstWord("delete");
            Task lastTask = ((AddCommand)lastCommand).getNewTask();
            int lastTaskIndex = findIndexOfTask(lastTask, taskList);
            commandToUndo.setTaskDescription(Integer.toString(lastTaskIndex));
            break;
        case "mark":
            commandToUndo = new MarkCommand();
            commandToUndo.setFirstWord("unmark");
            commandToUndo.setTaskDescription(lastDescription);
            break;
        case "unmark":
            commandToUndo = new MarkCommand();
            commandToUndo.setFirstWord("mark");
            commandToUndo.setTaskDescription(lastDescription);
            break;
        case "delete":
            break;
        default:
            commandToUndo = null;
        }
        if (commandToUndo == null) {
            System.out.println("Sorry, I am unable to undo the last action.");
        } else {
            commandToUndo.execute(taskList, storage);
        }
    }
}
