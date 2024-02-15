import java.util.Scanner;

public class Misty {
    public static void main(String[] args) {
        List itemList = new List();
        Parser.printWelcomeMessage();

        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            Parser.printMessageBorder();
            switch (input) {
            case "list":
                itemList.listAll();
                break;
            case "bye":
                Parser.printByeMessage();
                Parser.printMessageBorder();
                System.exit(0);
            default:
                if (input.startsWith("unmark")) {
                    int index;
                    String indexString;

                    if (!input.contains(" ")) {
                        Parser.printErrorNoId();
                        Parser.printUnmarkUsage();
                        break;
                    }

                    indexString = input.substring(input.indexOf(" ")).trim();

                    if (indexString.equals((""))) {
                        Parser.printErrorNoId();
                        Parser.printUnmarkUsage();
                        break;
                    }

                    index = Integer.parseInt(indexString);

                    if (index <= 0 || index > itemList.getItemCount()) {
                        Parser.printErrorInvalidId();
                        Parser.printUnmarkUsage();
                        break;
                    }

                    itemList.unmarkTask(index);
                } else if (input.startsWith("mark")) {
                    int index;
                    String indexString;

                    if (!input.contains(" ")) {
                        Parser.printErrorNoId();
                        Parser.printMarkUsage();
                        break;
                    }

                    indexString = input.substring(input.indexOf(" ")).trim();

                    if (indexString.equals((""))) {
                        Parser.printErrorNoId();
                        Parser.printMarkUsage();
                        break;
                    }

                    index = Integer.parseInt(indexString);

                    if (index <= 0 || index > itemList.getItemCount()) {
                        Parser.printErrorInvalidId();
                        Parser.printMarkUsage();
                        break;
                    }

                    itemList.markTask(index);
                } else if (input.startsWith("todo")) {
                    if (!input.contains(" "))
                    {
                        Parser.printErrorNoTaskName();
                        Parser.printTodoUsage();
                        break;
                    }

                    String description = input.substring(input.indexOf(" ")).trim();

                    if (description.equals("")) {
                        Parser.printErrorNoTaskName();
                        Parser.printTodoUsage();
                        break;
                    }

                    itemList.addTodo(description);
                } else if (input.startsWith("deadline")) {

                    if (!input.contains(" ") || !input.contains(" /by ")) {
                        Parser.printErrorInvalidFormat();
                        Parser.printDeadlineUsage();
                        break;
                    }

                    String description = input.substring(input.indexOf(" "), input.indexOf("/by")).trim();
                    String by = input.substring(input.indexOf("/by") + 3).trim();

                    if (description.equals("")) {
                        Parser.printErrorNoTaskName();
                        Parser.printDeadlineUsage();
                        break;
                    } else if (by.equals("")) {
                        Parser.printErrorNoBy();
                        Parser.printDeadlineUsage();
                        break;
                    }

                    itemList.addDeadline(description, by);
                } else if (input.startsWith("event")) {
                    if (!input.contains(" ") || !input.contains(" /from ") || !input.contains(" /to ") ) {
                        Parser.printErrorInvalidFormat();
                        Parser.printEventUsage();
                        break;
                    }

                    String description = input.substring(input.indexOf(" "), input.indexOf("/from")).trim();
                    String from = input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim();
                    String to = input.substring(input.indexOf("/to") + 3).trim();

                    if (description.equals("")) {
                        Parser.printErrorNoTaskName();
                        Parser.printEventUsage();
                        break;
                    } else if (from.equals("")) {
                        Parser.printErrorNoFrom();
                        Parser.printEventUsage();
                        break;
                    } else if (to.equals("")) {
                        Parser.printErrorNoTo();
                        Parser.printEventUsage();
                        break;
                    }

                    itemList.addEvent(description, from, to);
                } else {
                    Parser.printUnknownCommandMessage();
                }
            }

            Parser.printMessageBorder();
        }
    }
}
