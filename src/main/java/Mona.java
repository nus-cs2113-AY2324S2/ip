import java.util.Scanner;
public class Mona {
    public static void main(String[] args) {
        String logo = "___  ___                  \n"
                + "|  \\/  |                  \n"
                + "| .  . | ___  _ __   __ _ \n"
                + "| |\\/| |/ _ \\| '_ \\ / _` |\n"
                + "| |  | | (_) | | | | (_| |\n"
                + "\\_|  |_/\\___/|_| |_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);

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
