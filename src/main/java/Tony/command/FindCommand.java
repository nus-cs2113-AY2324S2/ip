package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand implements Command {
    private final String USER_INPUT;
    private final Parser parser;

    /**
     * Represents {@code FindCommand} object to handle find task command.
     * @param line is the String input by user
     * @param parser to help with processing the String input.
     */
    public FindCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>findCommand</code> method to find task from list.
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        String[] findTask = USER_INPUT.split("find");
        try {
            parser.checkArrayLength(findTask);
            ui.findCommand(findTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty.");
        }
    }

    /**
     * Returns <code>false</code> if command not entered <code>bye</code>
     * @return <code>false</code> and does not exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
