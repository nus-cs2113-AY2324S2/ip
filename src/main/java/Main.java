import tasklist.TaskList;
import ui.Ui;
import parser.Parser;
import java.io.IOException;

public class Main {
    public static final String PATHNAME = "src/data/prevData";
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasklist = new TaskList();
        Parser parser = new Parser(tasklist.getList(), tasklist.taskNum);
        ui.printGreetings();
        try {
            parser.runParser(PATHNAME);
        } catch (IOException e) {
            ui.showIOException();
        }
        ui.printExit();
    }
}
