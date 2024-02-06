import java.util.Scanner;
import java.util.Arrays;

public class Aragorn {

    public static void main(String[] args) {
        String LINE =  "    __________________________________________________________\n";
        String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
                "    the heir of Isildur Elendil's son of Gondor.\n" +
                "    What can I do for you?\n";
        String EXIT = "    Bye. Hope to see you again soon!\n";
        String TAB = "    ";
        String[] list;
        list = new String[100];
        int listLength = 0;
        System.out.println(LINE + GREET + LINE);

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
                for (int i = 0; i < listLength; i += 1) {
                    System.out.println(TAB + (i + 1) + ". " + list[i]);
                }
                System.out.println(LINE);
                continue;
            }

            list[listLength] = userInput;
            listLength += 1;
            echo = userInput;
            System.out.println(LINE + TAB + "added: " + echo + "\n" + LINE);
        }
    }
}
