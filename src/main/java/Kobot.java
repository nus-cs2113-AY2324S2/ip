import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Kobot {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Scanner in = new Scanner(System.in);
        Command command = new Command();

        ui.printHelloMessage();

        while (!command.getIsExit()) {
            String input = "";
            input = ui.receiveInput(in);
            command.parseCommand(input);
            command.executeCommand(taskList, ui);
        }

        ui.printGoodbyeMessage();
    }
}
