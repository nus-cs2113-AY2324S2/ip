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
                list.printAllTasks();
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int number = Integer.parseInt(words[1]);
                if (number >= 0 && number <= list.getSize()) {
                    list.changeTaskStatus(number - 1, (words[0].equals("mark") ? true : false));
                }
            } else if (line.startsWith("todo")) {
                String todoName = line.substring(5).trim();
                list.addTodo(todoName);
            } else if (line.startsWith("deadline")) {
                int byIndex = line.indexOf("/");
                String by = line.substring(byIndex + 4).trim();
                String deadlineName = line.substring(9, byIndex).trim();
                list.addDeadline(deadlineName, by);
            } else if (line.startsWith("event")) {
                int fromIndex = line.indexOf("/");
                int toIndex = line.indexOf("/", fromIndex + 1);
                String from = line.substring(fromIndex + 6, toIndex).trim();
                String to = line.substring(toIndex + 4).trim();
                String eventName = line.substring(6, fromIndex).trim();
                list.addEvent(eventName, from, to);
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
