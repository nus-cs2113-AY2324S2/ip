import static Parser.Parser.chat;

import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.FileNotFoundException;

public class PeeKay {

    private TaskList tasks;

    public PeeKay (String filepath) {
        try {
            tasks = new TaskList(Storage.load(filepath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            Ui.printLoadingErrorMessage();
        }
        Parser parser = new Parser(filepath,tasks);
    }
    public void run() {
        Ui.showLine();
        Ui.sayHi();
        Ui.showLine();

        chat();
    }
    public static void main(String[] args) {
        new PeeKay("src/data/peekay.txt").run();
    }
}