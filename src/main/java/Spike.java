import java.util.Scanner;

public class Spike {
    private static final String CHATBOT = "Spike";
    public static final String DIVIDER = "_________________________________________________";

    public static void main(String[] args) {
        displayWelcomeMsg();

        Task[] inputList = new Task[100];
        int iter = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();
            if (input.contentEquals("list")) {
                displayList(inputList);
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                inputList[index].setDone(true);
                displayMarkMsg(index, inputList);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                inputList[index].setDone(false);
                displayUnmarkMsg(index, inputList);
            } else if (input.contentEquals("bye")) {
                break;
            } else {
                displayAddedMsg(input);
                inputList[iter] = new Task(input);
            }
            iter += 1;
        }
        displayByeMsg();
    }

    private static void displayAddedMsg(String input) {
        System.out.println(DIVIDER);
        System.out.println("added: " + input);
        System.out.println(DIVIDER);
    }

    private static void displayList(Task[] inputList) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputList.length; i++) {
            Task value = inputList[i];
            if (value == null) {
                break;
            }
            System.out.println((i + 1) + ".[" + value.getStatusIcon() + "] " + value.description);
        }
        System.out.println(DIVIDER);
    }

    private static void displayUnmarkMsg(int index, Task[] inputList) {
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println((index + 1) + ".[ ] " + inputList[index].description);
        System.out.println(DIVIDER);
    }

    private static void displayMarkMsg(int index, Task[] inputList) {
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println((index + 1) + ".[X] " + inputList[index].description);
        System.out.println(DIVIDER);
    }

    private static void displayByeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static void displayWelcomeMsg() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm " + CHATBOT);
        System.out.println("What can I do for you?\n");
        System.out.println(DIVIDER);
    }
}
