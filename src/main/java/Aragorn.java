import java.util.Scanner;

public class Aragorn {
    public static void main(String[] args) {
        String LINE =  "    __________________________________________________________\n";
        String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
                "    the heir of Isildur Elendil's son of Gondor.\n" +
                "    What can I do for you?\n";
        String EXIT = "    Bye. Hope to see you again soon!\n";

        System.out.println(LINE + GREET + LINE);

        String echo;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput = in.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(LINE + "    " + EXIT + LINE);
                return;
            }

            echo = userInput;
            System.out.println(LINE + "    " + echo + "\n" + LINE);
        }
    }
}
