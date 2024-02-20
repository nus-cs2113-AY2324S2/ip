package Xavier;

import java.util.Scanner;

public class Xavier {
    public static final String LINE = "_________________________________________________________________";
    public static final String FILEPATH = "/Users/jasonlienardi/Documents/CS2113/ip/src/main/java/Xavier/toDoList.txt";
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        printWelcomeMessage();
        taskManager.readFile(FILEPATH);
        while (true) {
            String command = input.nextLine();
            System.out.println(LINE);
            if (command.equals("bye")) {
                exit();
                return;
            }
            else {
                taskManager.handleCommand(command);
            }
            System.out.println(LINE);
        }
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        input.close();
    }
}

