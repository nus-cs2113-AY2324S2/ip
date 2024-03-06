import java.util.ArrayList;
import ChelleCommands.Task;
import Common.Messages;
import Parser.Parser;
import Storage.ChelleStorage;
import UI.ChelleUI;

public class ChelleMain {

    /**
     * Takes in user inputs and processes them accordingly
     *
     * @param args the user's input
     */
    public static void main(String[] args) {

        ChelleUI ui = new ChelleUI();
        ChelleStorage storage = new ChelleStorage();
        ArrayList<Task> tasks = storage.loadTasks();
        Parser parser = new Parser();

        while (!parser.isByeCommandDetected) {
            System.out.print(Messages.MESSAGE_INPUT_PREFIX);
            String userInput = ui.getUserInput();
            parser.parseInput(tasks, userInput, storage, ui);
        }
    }
}