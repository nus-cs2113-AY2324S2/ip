import java.util.Scanner;
public class Mona {
    public static void main(String[] args) {

        ConsolePrint.greet();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        TaskManager taskManager = new TaskManager();

        while (true) {
            InputParser input = new InputParser(line);
            taskManager.executeCommand(input.commandTypeAndParams);

            if (input.commandTypeAndParams[0].equals("bye")) {
                ConsolePrint.exit();
                break;
            }
            line = in.nextLine();
        }
    }
}
