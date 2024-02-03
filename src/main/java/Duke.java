import java.util.Scanner;

public class Duke {
    public static void printItems(String[] items, int itemCount) {
        int itemNumber = 1;
        for (int i = 0; i < itemCount; i++) {
            System.out.println(itemNumber + ". " + items[i]);
            itemNumber++;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");

        String userCommand;
        String[] items = new String[100];
        int itemCount = 0;
        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();

        while (!(userCommand.equals("bye"))) {
            if (userCommand.equals("list")) {
                printItems(items, itemCount);
                userCommand = in.nextLine();
                continue;
            }

            items[itemCount] = userCommand;
            itemCount++;

            System.out.println("added: " + userCommand);
            userCommand = in.nextLine();
        }

        System.out.println("Bye! Hope to see you again :)");
    }
}
