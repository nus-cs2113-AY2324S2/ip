import java.util.Scanner;

public class Sayo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] items = new Task[100];
        int itemsCount = 0;

        System.out.println("Hello! I'm Sayo and it's great to see you! \n" + "What can I do for you today? \n");

        String input = " ";

        do {
            input = scanner.nextLine().trim();
            if (input.equals("list")) {
                for (int i = 0; i < itemsCount; i++) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
            } else if (input.startsWith("mark")) {
                System.out.println("test");
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < itemsCount) {
                    items[index].markAsDone();
                    System.out.println("Awesome! I've marked this task as done: ");
                    System.out.println(items[index]);
                }
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < itemsCount) {
                    items[index].unmarkAsDone();
                    System.out.println("Awesome! I've unmarked this task: ");
                    System.out.println(items[index]);
                }
            } else if (!input.equals("bye") && !input.equals("list")) {
                items[itemsCount] = new Task(input);
                itemsCount++;
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
