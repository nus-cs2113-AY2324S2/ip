package burger;

import java.util.Scanner;

import burger.list.List;

public class Burger {
    static final String CHATBOT_NAME = "Burger";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) throws BurgerException {
        welcomeMessage();
        Scanner input = new Scanner(System.in);
        List myList = new List();
        boolean isPolling = true;
        while (isPolling) {
            String text = input.nextLine();
            String[] textArray = text.split(" ");
            if (!myList.isValidCommand(textArray)) {
                switch (text.trim().toLowerCase()) {
                case "bye":
                    isPolling = false;
                    break;
                case "list":
                    myList.printTodoList();
                    break;
                default:
                    wakeUp();
                    break;
                }
            }
        }
        goodbye();
    }

    private static void welcomeMessage() {
        printLine();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
    /**
     * Prints error message when user provides invalid input
     */
    private static void wakeUp() {
        try {
            throw new BurgerException();
        } catch (BurgerException e) {
            printLine();
            System.out.println("Wake Up!!! Key in something that makes sense!");
            printLine();
        }

    }

    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}