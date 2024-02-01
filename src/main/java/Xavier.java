import java.util.Scanner;

public class Xavier {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] itemList = new String[100];
        int numberOfItems = 0;

        System.out.println("---------------------------------------------------------------------");
        System.out.println("Hello! I'm: " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------");

        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                System.out.println("---------------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------------------------------");
                return;
            }
            else if (command.equals("list")) {
                System.out.println("---------------------------------------------------------------------");
                for (int i = 0; i < numberOfItems; i++) {
                    System.out.println(i+1 + ". " + itemList[i]);
                }
                System.out.println("---------------------------------------------------------------------");
            }
            else {
                itemList[numberOfItems] = command;
                numberOfItems += 1;
                System.out.println("---------------------------------------------------------------------");
                System.out.println("added: " + command);
                System.out.println("---------------------------------------------------------------------");
            }
        }
    }
}

