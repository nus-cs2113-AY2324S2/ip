/*public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}*/
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String chatbotName = "Horizon";
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you? ");

        String input;
        Task[] list;
        int listLength = 0;
        int selectedItem = 0;
        list = new Task[100];
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! ");
                break;
            } else if (input.startsWith("list")) {
                System.out.println("Here are the tasks in your list: ");

                for (int i = 0; i < listLength; i += 1) {
                    if (list[i].isDone()) {
                        System.out.println((i + 1) + ". [X] " + list[i].getDescription());
                    } else {
                        System.out.println((i + 1) + ". [ ] " + list[i].getDescription());
                    }
                }
            } else if (input.startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet: ");
                selectedItem = new Scanner(input).useDelimiter("\\D+").nextInt() - 1;
                System.out.println("[ ] " + list[selectedItem].getDescription());
                list[selectedItem].markAsNotDone();

            } else if (input.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done: ");
                selectedItem = new Scanner(input).useDelimiter("\\D+").nextInt() - 1;
                System.out.println("[X] " + list[selectedItem].getDescription());
                list[selectedItem].markAsDone();

            } else {
                System.out.println("added: " + input);
                list[listLength] = new Task(input, false);
                listLength += 1;
            }
        }


    }
}
