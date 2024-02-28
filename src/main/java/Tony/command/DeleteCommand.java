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

    public DeleteCommand(String line, Parser parser) {
        this.USER_INPUT = line;
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException, TonyException {
        this.tasks = tasks;
        this.ui = ui;
        this.fileSaver = fileSaver;
        deleteTaskCommand(USER_INPUT);
    }

    private void deleteTaskCommand(String line) throws IOException {
        try {
            String[] subCommand = line.split(" ");
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

    private void deleteATask(String subCommand, int num) throws IOException {
        ui.printAddOrDeleteTask(subCommand, num - 1);
        tasks.remove(num - 1);
        fileSaver.updateFile();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
