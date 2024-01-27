import java.util.Scanner;

public class GeePee {

    public static void greetUser() {
        System.out.println("    ________________________________________________");
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        System.out.println("    ________________________________________________\n");
    }

    public static void exitMessage() {
        System.out.println("    ________________________________________________");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ________________________________________________\n");
    }

    public static void loop() {
        List list = new List();
        String line = "";
        Scanner in = new Scanner(System.in);
        while (!(line.equals("bye"))) {
            line = in.nextLine().trim();
            if (line.equals("") || line.equals("bye")) {
                continue;
            } else if (line.equals("list")) {
                list.printList();
            } else {
                list.addItem(line);
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        loop();
        exitMessage();
    }
}
