import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private static List<String> commandHistory = new ArrayList<>();

    public static void storeCommand(String command) {
        commandHistory.add(command);
    }

    public static void printHistory() {
        int cnt = 0;
        for (String command: commandHistory) {
            cnt += 1;
            System.out.printf(cnt + ". " + command + "%n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String botName = "Huan";
        System.out.println("Hello! I'm " + botName + ", a chat bot");
        while(true) {
            System.out.println("-------------------------");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! See ya!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printHistory();
            } else {
                storeCommand(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
