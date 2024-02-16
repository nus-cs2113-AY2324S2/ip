package burger;

import java.util.Scanner;

import burger.list.List;

public class Burger {
    static final String CHATBOT_NAME = "Burger";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) {
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
                    myList.printTaskList();
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
     * Prints error message when user provides unknown input
     */
    private static void wakeUp() {
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