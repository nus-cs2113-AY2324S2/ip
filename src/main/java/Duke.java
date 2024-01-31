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
        String[] list;
        int listLength = 0;
        list = new String[100];
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon! ");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < listLength; i += 1) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                System.out.println("added:" + input);
                list[listLength] = input;
                listLength += 1;
            }
        }


    }
}
