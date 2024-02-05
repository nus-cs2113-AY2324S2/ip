import java.util.Locale;
import java.util.Scanner;

public class Kobot {
    public static String receiveInput(Scanner in, Ui ui) {
        ui.printLineDivider();
        System.out.print("> ");
        String input = in.nextLine();
        ui.printLineDivider();
        return input;
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Scanner in = new Scanner(System.in);
        Command command = new Command();

        ui.printHelloMessage();

        while (!command.getIsExit()) {
            command.parseCommand(receiveInput(in, ui));
            command.executeCommand(taskList);
        }

        ui.printGoodbyeMessage();
    }
}
