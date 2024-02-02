import java.util.Scanner;

public class Xavier {
    public static final String LINE = "_________________________________________________________________";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] itemList = new Task[100];
        int numberOfItems = 0;

        System.out.println(LINE);
        System.out.println("Hello! I'm " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        while (true) {
            String command = input.nextLine();
            String[] stringArray = command.split(" ");
            System.out.println(LINE);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            }
            else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfItems; i++) {
                    System.out.println(i+1 + "." + "[" + itemList[i].getStatusIcon() + "] " + itemList[i].description);
                }
            }
            else if (stringArray[0].equals("mark")) {
                int index = Integer.parseInt(stringArray[1]) - 1;
                itemList[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + itemList[index].getStatusIcon() + "] " + itemList[index].description);
            }
            else if (stringArray[0].equals("unmark")) {
                int index = Integer.parseInt(stringArray[1]) - 1;
                itemList[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not yet done:");
                System.out.println("[" + itemList[index].getStatusIcon() + "] " + itemList[index].description);
            }
            else {
                Task t = new Task(command);
                itemList[numberOfItems] = t;
                numberOfItems += 1;
                System.out.println("added: " + command);
            }
            System.out.println(LINE);
        }
    }
}

