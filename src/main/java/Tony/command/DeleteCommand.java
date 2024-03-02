package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private Ui ui;
    private FileSaver fileSaver;
    private final Parser parser;

    /**
     * Represents {@code DeleteCommand} object to handle delete command.
     * @param line is the String input by user
     * @param parser to help with processing the String input.
     */

    public DeleteCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>deleteTaskCommand</code> method to remove task from list.
     * @param tasks is the current list of <code>tasks</code> to save from the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     * @throws IOException If there is error in saving data into file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        deleteTaskCommand();
    }

    /**
     * Splits the String <code>line</code> by white space and checks its length
     * then ensures only number input after delete command
     * and checks if number supplied is found within list range
     * @throws IOException If there is error in saving data into file.
     */
    private void deleteTaskCommand() throws IOException {
        try {
            String[] subCommand = USER_INPUT.split(" ");
            parser.checkArrayLength(subCommand);
            int num = Integer.parseInt(subCommand[1]);
            parser.checkNumberWithinRange(num);
            deleteATask(subCommand[0], num);
        } catch (NumberFormatException nfe) {
            System.out.println("Suggest only number after 'delete'!");
        } catch (TonyException e) {
            System.out.println("To delete a task, suggest a number available in the list!");
        }
    }

    /**
     * Removes the specified task from the <code>tasks</code> list
     * @param subCommand contains the String 'delete' from the delete command
     * @param num the index of the task given by user
     * @throws IOException If there is error in saving data into file.
     */

    private void deleteATask(String subCommand, int num) throws IOException {
        ui.printAddOrDeleteTask(subCommand, num - 1);
        tasks.remove(num - 1);
        fileSaver.updateFile();
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
