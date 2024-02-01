import java.util.Scanner;
public class Nehsik {
    public static void main(String[] args) {
        displayGreetings();
        String command;
        Scanner in = new Scanner(System.in);
        String[] commandList = new String[100];
        int commandIndex = 0;
        while (true) {
            command = in.nextLine();
            if (command.equals("list")) {
                printLine();
                for (int i = 0; i < commandIndex; i++) {
                    System.out.println((i + 1) + ". " + commandList[i]);
                }
                printLine();
            } else if (command.equals("bye")) {
                break;
            } else {
                commandList[commandIndex] = command;
                printLine();
                System.out.println("added: " + command);
                printLine();
                commandIndex++;
            }
        }
        displayExitMessage();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void displayGreetings() {
        printLine();
        System.out.println("Hello! I'm Nehsik");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void displayExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
