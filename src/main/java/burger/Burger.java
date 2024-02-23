package burger;

import java.util.Scanner;

import burger.list.List;

public class Burger {
    static final String CHATBOT_NAME = "Burger";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) {
        welcomeMessage();
        List myList = new List();
        Scanner input = new Scanner(System.in);
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
                    printLine();
                    myList.printTaskList();
                    break;
                default:
                    printUnknownInputError();
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
     * Prints error message when user provides unknown input
     */
    private static void printUnknownInputError() {
        printLine();
        System.out.println("Wake Up!!! Key in something that makes sense!");
        printLine();
    }

    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}