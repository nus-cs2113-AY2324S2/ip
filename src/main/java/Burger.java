import java.util.Scanner;

public class Burger {
    static final String CHATBOT_NAME = "忍野 忍";
    static final String HORIZONTAL_LINE = "---------------------------------";

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printLine();
        Scanner input = new Scanner(System.in);
        Todo myTodoList = new Todo();
        boolean poll = true;
        while (poll) {
            String text = input.nextLine();
            switch (text.trim().toLowerCase()){
            case "bye": poll = false;
                break;
            case "list": myTodoList.printTodo();
                break;
            case "": break;
            default: myTodoList.addList(text);
                printAddTask(text);
            }
        }
        goodbye();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printAddTask(String text) {
        printLine();
        System.out.println("added: " + text);
        printLine();
    }

    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}
