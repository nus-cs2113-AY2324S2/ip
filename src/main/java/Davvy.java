import java.util.Scanner;
public class Davvy {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printStatement(String statementType) {
        printLine();
        if (statementType.equals("greetings")) {
            System.out.println(" Hello! I'm Davvy\n" + " What can I do for you?");
        } else if (statementType.equals("goodbye")) {
            System.out.println(" Bye. Hope to see you again soon!");
        }
        printLine();
    }

    private static void echoUserInput(String userInput) {
        printLine();
        System.out.println(" " + userInput);
        printLine();
    }

    public static void main(String[] args) {
        printStatement("greetings");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            echoUserInput(userInput);
            userInput = in.nextLine();
        }

        printStatement("goodbye");
    }
}
