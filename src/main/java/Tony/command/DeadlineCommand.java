package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Deadline;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeadlineCommand implements Command {
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private FileSaver fileSaver;
    private Ui ui;
    private final Parser parser;

    /**
     * Represents {@code DeadlineCommand} object to handle deadline task type.
     * @param line is the String input by user
     * @param parser to help with processing the String input.
     */
    public DeadlineCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>addDeadlineCommand</code> method to add Deadline task into list.
     * checks if there are no words after deadline keyword
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     * @throws IOException If there is error in saving data into file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        String[] deadlineTask = USER_INPUT.split("deadline");
        try {
            parser.checkArrayLength(deadlineTask);
            addDeadlineCommand(deadlineTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty.");
        }
    }

    /**
     * Processes the <code>deadlineTask</code> String by splitting the String at '/by'
     * and saves into current <code>tasks</code> list.
     * @param deadlineTask array String after deadline keyword.
     * @throws IOException If there is error in saving data into file.
     */

    private void addDeadlineCommand(String[] deadlineTask) throws IOException, TonyException {
        try {
            parser.checkContainsByWord(deadlineTask);
            String[] description = deadlineTask[1].split("/by");
            Deadline deadline = new Deadline(description[0], description[1]);
            tasks.add(deadline);
            ui.printAddOrDeleteTask(description[0], tasks.indexOf(deadline));
            String deadlineLine = fileSaver.saveDeadline(deadline);
            fileSaver.saveData(deadlineLine, true);
        } catch (TonyException e) {
            System.out.println("Your deadline command should have '/by' after task description."
                    + System.lineSeparator()
                    + "\tFormat: deadline <description> /by <DATE>");
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
