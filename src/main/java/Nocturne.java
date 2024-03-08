import exceptions.NocturneException;
import util.Parser;
import util.TaskList;
import util.Ui;

public class Nocturne {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Ui.greetingMessage();
        Parser.getInput(list);
        Ui.farewellMessage();
    }
}