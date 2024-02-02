import java.util.Scanner;

public class GeePee {

    public static void greetUser() {
        printHorizontalLine();
        System.out.println("    Hello! I'm GeePee, your friendly chatbot assistant!");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }

    public static void exitMessage() {
        printHorizontalLine();
        System.out.println("    Bye! Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void loop() {
        List list = new List();
        String line = "";
        Scanner in = new Scanner(System.in);
        while (!(line.equals("bye"))) {
            line = in.nextLine().trim();
            if (line.equals("") || line.equals("bye")) {
                continue;
            } else if (line.startsWith("list")) {
                list.printList();
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int number = Integer.parseInt(words[1]);
                if (number >= 0 && number <= list.getSize()) {
                    list.changeTaskStatus(number - 1, (words[0].equals("mark") ? true : false));
                }
            } else {
                list.addTask(line);
            }
        }
    }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }

    public static void main(String[] args) {
        greetUser();
        loop();
        exitMessage();
    }
}
