import java.util.Scanner;

public class Misty {
    public static void main(String[] args) {
        List itemList = new List();
        Parser.printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();
            switch (input) {
            case "list":
                Parser.printMessageBorder();
                itemList.listAll();
                Parser.printMessageBorder();
                break;
            case "bye":
                break;
            default:
                Parser.printMessageBorder();
                if (input.startsWith("unmark")) {
                    int index;
                    index = Integer.parseInt(input.substring(7));

                    itemList.unmarkTask(index);
                } else if (input.startsWith("mark")) {
                    int index;
                    index = Integer.parseInt(input.substring(5));

                    itemList.markTask(index);
                } else if (input.startsWith("todo")) {
                    String description = input.substring(input.indexOf(" ")).trim();

                    itemList.addTodo(description);
                } else if (input.startsWith("deadline")) {
                    String description = input.substring(input.indexOf(" "), input.indexOf("/by")).trim();
                    String by = input.substring(input.indexOf("/by") + 4);

                    itemList.addDeadline(description, by);
                } else if (input.startsWith("event")) {
                    String description = input.substring(input.indexOf(" "), input.indexOf("/from")).trim();
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                    String to = input.substring(input.indexOf("/to") + 3).trim();

                    itemList.addEvent(description, from, to);
                } else {
                    Parser.printUnknownCommandMessage();
                }
                Parser.printMessageBorder();
            }

            if (input.equals("bye")) {
                Parser.printByeMessage();
                break;
            }
        }
    }
}
