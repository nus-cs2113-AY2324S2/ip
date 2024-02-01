import java.util.Scanner;

public class Spike {
    public static void main(String[] args) {
        String chatbot = "Spike";
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?\n");

        Task[] inputList = new Task[100];
        int iter = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.contentEquals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputList.length; i++) {
                    Task value = inputList[i];
                    if (value == null) {
                        break;
                    }

                    System.out.println((i + 1) + ".[" + value.getStatusIcon() + "] " + value.description);
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                inputList[index].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((index + 1) + ".[X] " + inputList[index].description);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                inputList[index].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println((index + 1) + ".[ ] " + inputList[index].description);
            } else if (input.contentEquals("bye")) {
                break;
            } else {
                System.out.println("added: " + input);
                inputList[iter] = new Task(input);
            }
            iter += 1;
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
