package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.TonyException;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand implements Command{
    private final String USER_INPUT;
    private ArrayList<Task> tasks;
    private FileSaver fileSaver;
    private final Parser parser;

    /**
     * Represent {@code MarkCommand} object to mark tasks specified by user.
     * @param line is String input by user.
     * @param parser to help with processing the String input
     */
    public MarkCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }

    /**
     * Executes the <code>markTaskCommand</code> method to mark task when it is done,
     * also checks if user has empty input after mark command
     * @param tasks is the current list of <code>tasks</code> to save to the file.
     * @param ui is the user interface of that prints texts on program.
     * @param fileSaver is object used to save data into the file.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) {
        this.tasks = tasks;
        this.fileSaver = fileSaver;
        try {
            String[] subCommand = USER_INPUT.split(" ");
            parser.checkArrayLength(subCommand);
            int num = Integer.parseInt(subCommand[1]);
            parser.checkNumberWithinRange(num);
            markTaskCommand(subCommand, num);
        } catch (NumberFormatException nfe) {
            System.out.println("Suggest a number after 'mark'!");
        } catch (TonyException | IOException e) {
            System.out.println("To mark a task, suggest a number available in the list!");
        }
    }

    /**
     * Marks a task if it is done
     * @param subCommand is String array that contains mark/unmark command
     * @param num is the task index specified by user
     * @throws IOException If there is error in saving data into file.
     */
    public void markTaskCommand(String[] subCommand, int num) throws IOException {

        if (subCommand[0].equals("mark")) {
            tasks.get(num - 1).markDone();
            System.out.println("\tNice! I've marked this task as done:"
                    + System.lineSeparator() + "\t " + tasks.get(num - 1));
        } else {
            tasks.get(num - 1).markNotDone();
            System.out.println("\tOK, I've marked this task as not done yet:"
                    + System.lineSeparator() + "\t " + tasks.get(num - 1));
        }
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
