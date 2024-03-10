import java.util.ArrayList;
import java.util.Scanner;

public class Nehsik {

    public static void main(String[] args) {
        Ui.displayGreetings();

        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        Storage.loadTasksFromFile(taskList);

        while (Parser.isContinue) {
            try {
                String command = in.nextLine().trim();
                Parser.parseCommand(command, taskList);
            } catch (NehsikException e) {
                Ui.displayErrorMessage(e.getMessage());
            }
            Storage.saveTasksToFile(taskList);
        }

        Ui.displayExitMessage();
        in.close();
    }
}
