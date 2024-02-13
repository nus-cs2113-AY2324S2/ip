import java.util.Scanner;

public class Sayo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] items = new Task[100];
        int itemsCount = 0;

        System.out.println("Hello! I'm Sayo and it's great to see you! \n" + "What can I do for you today? \n");

        String input = " ";

        do {
            try {
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
                } else if (input.startsWith("todo")) {
                    itemsCount = addToDo(input, items, itemsCount);
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    String description = input.substring(9, byIndex).trim();
                    String by = input.substring(byIndex + 4).trim();
                    items[itemsCount] = new Deadline(description, by);
                    itemsCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + items[itemsCount - 1]);
                    System.out.println("Now you have " + itemsCount + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    String description = input.substring(6, fromIndex).trim();
                    String start = input.substring(fromIndex + 6, toIndex).trim();
                    String end = input.substring(toIndex + 4).trim();
                    items[itemsCount] = new Event(description, start, end);
                    itemsCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + items[itemsCount - 1]);
                    System.out.println("Now you have " + itemsCount + " tasks in the list.");
                } else if (!input.equals("bye")) {
                    throw new SayoException("Oh no! Apologies, but I don't know what that means :-( Please retry.");
                }
            } catch (SayoException e) {
                System.out.println(e.getMessage());
            }
             
        } while (!input.equals("bye"));


        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static int addToDo(String input, Task[] items, int itemsCount) throws SayoException {
        if (input.length() <= 5) {
            throw new SayoException("Please focus! The description of a todo cannot be empty. Please retry...");
        }
        String description = input.substring(5);
        items[itemsCount] = new ToDo(description);
        itemsCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + items[itemsCount-1]);
        System.out.println("Now you have " + itemsCount + " tasks in the list.");
        return itemsCount;
    }


    
}
