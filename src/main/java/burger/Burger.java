package burger;

import java.io.IOException;
import java.util.Scanner;

import burger.list.List;

import static burger.BurgerFileClass.*;

public class Burger {
    static final String CHATBOT_NAME = "Burger";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) throws IOException {
        welcomeMessage();
        List myList = getSaveFile();
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
        goodbye(myList);
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

    public static void goodbye(List currList) throws IOException {
        System.out.print("Saving file");
        setSaveFile(PATHNAME, currList);
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}