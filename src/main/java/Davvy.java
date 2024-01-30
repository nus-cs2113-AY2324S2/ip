import java.util.Scanner;
public class Davvy {
    private static String[] inputList = new String[100];
    private static int i = 0; // counter variable to help iterate through array as input is stored
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printStatement(String statementType) {
        printLine();
        switch (statementType) {
        case "greetings":
            System.out.println(" Hello! I'm Davvy\n" + " What can I do for you?");
            break;
        case "goodbye":
            System.out.println(" Bye. Hope to see you again soon!");
            break;
        case "add":
            System.out.println(" added: " + inputList[i]);
            break;
        case "list":
            for (int x = 0 ; x < i ; x++) {
                System.out.println(" " + (x+1) + ". " + inputList[x]);
            }
            break;
        }
        printLine();
    }

    public static void main(String[] args) {
        printStatement("greetings");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            inputList[i] = userInput;
            if (userInput.equals("list")) {
                printStatement("list");
            } else {
                printStatement("add");
                i++;
            }
            userInput = in.nextLine();
        }

        printStatement("goodbye");
    }
}
