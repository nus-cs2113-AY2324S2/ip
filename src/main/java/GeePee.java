import java.util.Scanner;

public class GeePee {

    public static void greetUser() {
        System.out.println("    ________________________________________________");
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        System.out.println("    ________________________________________________\n");
    }

    public static void echoInput(String input) {
        System.out.println("    ________________________________________________");
        if (input.equals("bye")) {
            System.out.println("    Bye! Hope to see you again soon!");
        } else {
            System.out.println("    " + input);
        }
        System.out.println("    ________________________________________________\n");
    }

    public static void main(String[] args) {
        greetUser();
        String line = "";
        Scanner in = new Scanner(System.in);
        while (!(line.equals("bye"))) {
            line = in.nextLine();
            if (line.trim().equals("")) continue;
            echoInput(line);
        }
    }
}
