import java.util.Scanner;

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
            String command = textArray[0];
            switch (command) { // assume inputs for commands are correct
            case "mark":
            case "unmark":
                int idx = Integer.parseInt(textArray[1]) - 1;
                myList.getMark(idx, command);
                break;
            case "todo":
                myList.addTodo(textArray);
                break;
            case "deadline":
                myList.addDeadline(textArray);
                break;
            case "event":
                myList.addEvent(textArray);
                break;
            default:
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
                break;
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

    private static void wakeUp() {
        printLine();
        System.out.println("Wake Up! Key in something that makes sense!");
        printLine();
    }


    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}