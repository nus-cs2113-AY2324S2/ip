import java.util.Scanner;

public class Aragorn {

    public static void main(String[] args) {
        String LINE =  "    __________________________________________________________\n";
        String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
                "    the heir of Isildur Elendil's son of Gondor.\n" +
                "    What can I do for you?\n";
        String EXIT = "    Bye. Hope to see you again soon!\n";
        String TAB = "    ";
        Task[] list = new Task[100];
        int listLength = 0;
        System.out.println(LINE + GREET + LINE);
        int index;
        String echo;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput = in.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(LINE + TAB + EXIT + LINE);
                return;
            }

            if (userInput.equals("list")) {
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < listLength; i += 1) {
                    System.out.println(TAB + "[" + list[i].getStatusIcon() + "] " + (i + 1) + ". " + list[i].getDescription());
                }
                System.out.println(LINE);
                continue;
            }

            if (userInput.contains("unmark")) {
                index = Integer.parseInt(userInput.substring(7)) - 1;
                list[index].markAsUndone();
                System.out.println(LINE + TAB + "OK, I've marked this task as not done yet:\n" + TAB +
                        "   [ ] " + list[index].getDescription() +"\n" + LINE);
                continue;
            }

            else if (userInput.contains("mark")) {
                index = Integer.parseInt(userInput.substring(5)) - 1;
                list[index].markAsDone();
                System.out.println(LINE + TAB + "Nice! I've marked this task as done:\n" + TAB +
                        "   [X] " + list[index].getDescription() +"\n" + LINE);
                continue;
            }

            list[listLength] = new Task(userInput);
            listLength += 1;
            echo = userInput;
            System.out.println(LINE + TAB + "added: " + echo + "\n" + LINE);
        }
    }
}
