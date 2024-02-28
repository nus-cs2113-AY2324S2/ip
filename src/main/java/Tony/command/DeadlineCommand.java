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

    public DeadlineCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException, TonyException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        String[] deadlineTask = USER_INPUT.split("deadline");
        try {
            parser.checkArrayLength(deadlineTask);
            addDeadlineCommand(deadlineTask);
        } catch (TonyException e) {
            System.out.println("OOPS!! The description of '" + USER_INPUT
                    + "' cannot be empty." + System.lineSeparator());
        }
    }

    private void addDeadlineCommand(String[] deadlineTask) throws IOException {
        String[] description = deadlineTask[1].split("/by");
        Deadline deadline = new Deadline(description[0], description[1]);
        tasks.add(deadline);
        ui.printAddOrDeleteTask(description[0], tasks.indexOf(deadline));
        String deadlineLine = fileSaver.saveDeadline(deadline);
        fileSaver.saveData(deadlineLine, true);
    }
}
