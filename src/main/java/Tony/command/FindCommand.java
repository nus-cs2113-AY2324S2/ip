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
    private ArrayList<Task> tasks;
    private Ui ui;
    private final Parser parser;
    public FindCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException, TonyException {
        this.tasks = tasks;
        this.ui = ui;
        String[] findTask = USER_INPUT.split("find");
        try {
            parser.checkArrayLength(findTask);
            ui.findCommand(findTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
