package Tony.command;

import Tony.FileManager.FileSaver;
import Tony.task.Task;
import Tony.utility.Parser;
import Tony.utility.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class CommandChecker implements Command{
    private final Parser parser;
    public CommandChecker(Parser parser) {
        this.parser = parser;
    }
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, FileSaver fileSaver) throws IOException {
        parser.checkFirstCommandWord();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
