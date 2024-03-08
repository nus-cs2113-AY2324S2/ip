import storage.Storage;
import tasklist.TaskList;
import tasklist.todo.Todo;
import ui.Ui;
import parser.Parser;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final String PATHNAME = "src/data/prevData";

    /**
     * Runs the Doraemon chatbot programme.
     * @param args Input from user
     * @throws IOException If Scanner is unable to find file
     */
    public static void main(String[] args) {
        ArrayList<Todo> list = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage(PATHNAME);
        int taskNum = 0;
        try {
            taskNum = storage.loadData(list);
        } catch (IOException e) {
            ui.showIOException();
        }
        TaskList tasklist = new TaskList(list, taskNum);
        Parser parser = new Parser(list, taskNum);
        ui.printGreetings();
        try {
            parser.runParser(PATHNAME, tasklist);
        } catch (IOException e) {
            ui.showIOException();
        }
        ui.printExit();
    }
}
