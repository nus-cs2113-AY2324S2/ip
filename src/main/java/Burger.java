import java.util.Scanner;

public class Burger {
    static final String CHATBOT_NAME = "Burger";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printLine();
        Scanner input = new Scanner(System.in);
        Todo myTodoList = new Todo();
        boolean isPolling = true;
        while (isPolling) {
            String text = input.nextLine();
            String[] textArray = text.split(" ");
            if (text.startsWith("mark")) { // assume inputs for mark / unmark is correct
                int idx = Integer.parseInt(textArray[1]) - 1;
                myTodoList.getMark(idx);
            } else if (text.startsWith("unmark")) {
                int idx = Integer.parseInt(textArray[1]) - 1;
                myTodoList.getUnmark(idx);
            } else {
                switch (text.trim().toLowerCase()) {
                case "bye":
                    isPolling = false;
                    break;
                case "list":
                    myTodoList.printTodoList();
                    break;
                case "":
                    wakeUp();
                    break;
                default:
                    myTodoList.addTodo(text);
                    break;
                }
            }
        }
        goodbye();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private static void wakeUp() {
        printLine();
        System.out.println("Wake Up! Key in something!");
        printLine();
    }


    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}