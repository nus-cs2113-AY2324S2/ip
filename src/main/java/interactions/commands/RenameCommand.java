package interactions.commands;

import customexceptions.IncompletePromptException;
import customexceptions.UnknownPromptException;
import interactions.Storage;
import interactions.Ui;
import tasks.TaskList;

public class RenameCommand extends Command {
    Ui ui;

    /**
     * Sets the UI object to the name of the inputted UI's name.
     *
     * @param ui Chatbot UI object with name.
     */
    public RenameCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Changes the name of the chatbot UI to a new name.
     *
     * @param taskList List of tasks containing ToDo's, Events and Deadlines.
     * @param storage Storage handler that saves to file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        String oldName = ui.getName().toUpperCase();
        String newName = taskDescription.toUpperCase();
        if (oldName.equalsIgnoreCase(newName)) {
            System.out.println("My name is already " + newName + ". Please choose a new name.");
            return;
        }
        ui.setName(newName);
        System.out.println("Renamed chatbot from " + oldName + " to " + newName);
    }
}
