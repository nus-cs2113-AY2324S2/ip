package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.util.ArrayList;

public class CommandChecker implements Command{
    private final Parser parser;

    /**
     * Represents {@code CommandChecker} object to check if user input the correct command.
     * @param parser is used to parse the user input for processing.
     */
    public CommandChecker(Parser parser) {
        this.parser = parser;
    }

    /**
     * Executes the <code>checkFirstCommandWord</code> method.
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        parser.checkFirstCommandWord();
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
