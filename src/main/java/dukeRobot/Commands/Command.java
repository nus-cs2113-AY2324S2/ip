package dukeRobot.Commands;
import dukeRobot.Tasks.Task;
import dukeRobot.Tools.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
public abstract class Command{
    protected String keyWord;
    public Command(Parser parser) {
        this.keyWord = parser.getKeyWord();
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.keyWord.equals("bye");
    }
}
