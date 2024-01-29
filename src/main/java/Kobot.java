import java.util.Locale;
import java.util.Scanner;

public class Kobot {
    public static void printDashes() {
        System.out.println("-------------------------------------------");
    }
    public static void printHelloMessage() {
        String logo = "#########################################\n"
                + "##     _   __      _           _       ##\n"
                + "##    | | / /     | |         | |      ##\n"
                + "##    | |/ /  ___ | |__   ___ | |_     ##\n"
                + "##    |    \\ / _ \\| '_ \\ / _ \\| __|    ##\n"
                + "##    | |\\  \\ (_) | |_) | (_) | |_     ##\n"
                + "##    \\_| \\_/\\___/|_.__/ \\___/ \\__|    ##\n"
                + "##                                     ##\n"
                + "#########################################\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Kobot. How may I assist you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye, hope to see you again!");
    }

    public static String receiveInput(Scanner in) {
        printDashes();
        System.out.print("> ");
        String input = in.nextLine();
        printDashes();
        return input;
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);

        printHelloMessage();

        String command = receiveInput(in);
        while (!command.toLowerCase().matches("bye")) {
            if (command.toLowerCase().matches("list")) {
                taskList.printTaskList();
            } else {
                taskList.addTask(command);
            }

            command = receiveInput(in);
        }

        printGoodbyeMessage();
    }
}
