import java.util.Scanner;

public class Aragorn {

    private static final String LINE =  "    __________________________________________________________\n";
    private static final String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
            "    the heir of Isildur Elendil's son of Gondor.\n" +
            "    What can I do for you?\n";
    private static final String EXIT = "    Bye. Hope to see you again soon!\n";
    private static final String TAB = "    ";

    public static void main(String[] args) {
        Task[] list = new Task[100];
        int listLength = 0;
        int remainingTasks = 0;
        System.out.println(LINE + GREET + LINE);
        int index;
        String icon;
        String[] splitInput;
        String[] splitDeadline;
        String[] splitEvent;
        String[] splitStart;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput = in.nextLine();
            String commandType = commandIdentifier(userInput);

            if (userInput.equals("bye")) {
                System.out.println(LINE + TAB + EXIT + LINE);
                return;
            }

            if (userInput.equals("list")) {
                System.out.println(LINE);
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 0; i < listLength; i += 1) {
                    System.out.println(TAB + (i + 1) + ". " + list[i].taskString());
                }
                System.out.println("\n    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
                continue;
            }

            if (userInput.startsWith("unmark ")) {
                index = Integer.parseInt(userInput.substring(7)) - 1;
                icon = list[index].getStatusIcon();
                if (icon.equals(" ")) {
                    System.out.println(LINE + "    This task has already been unmarked.\n" + LINE);
                    continue;
                }
                list[index].markAsUndone();
                remainingTasks += 1;
                System.out.println(LINE + TAB + "OK, I've marked this task as not done yet:\n" + TAB +
                        "   " + list[index].taskString() +"\n");
                System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
                continue;
            }

            if (userInput.startsWith("mark ")) {
                index = Integer.parseInt(userInput.substring(5)) - 1;
                icon = list[index].getStatusIcon();
                if (icon.equals("X")) {
                    System.out.println(LINE + "    This task has already been marked.\n" + LINE);
                    continue;
                }
                list[index].markAsDone();
                remainingTasks -= 1;
                System.out.println(LINE + TAB + "Nice! I've marked this task as done:\n" + TAB +
                        "   " + list[index].taskString() + "\n");
                System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
                continue;
            }

            if (userInput.startsWith("todo ")) {
                list[listLength] = new ToDo(userInput);
                System.out.println(LINE + "    Got it. I've added this task:");
                System.out.println(TAB + list[listLength].taskString() + "\n");
                listLength += 1;
                remainingTasks += 1;
                System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
                continue;
            }

            if (userInput.startsWith("deadline ")) {
                splitInput = userInput.split("deadline ");
                splitDeadline = splitInput[1].split("/by");
                list[listLength] = new Deadline(splitDeadline[0].trim(), splitDeadline[1].trim());
                System.out.println(LINE + "    Got it. I've added this task:\n" + TAB + list[listLength].taskString() + "\n");
                listLength += 1;
                remainingTasks += 1;
                System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
                continue;
            }

            if (userInput.startsWith("event ")) {
                splitInput = userInput.split("event ");
                splitEvent = splitInput[1].split("/from ");
                splitStart = splitEvent[1].split("/to ");
                list[listLength] = new Event(splitEvent[0].trim(), splitStart[0].trim(), splitStart[1].trim());
                System.out.println(LINE + "    Got it. I've added this task:\n" + TAB + list[listLength].taskString() + "\n");
                listLength += 1;
                remainingTasks += 1;
                System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
            }

            else {
                System.out.println(LINE + "    Sorry! Please the correct format.");
                System.out.println("    list for viewing list of tasks, \n" +
                                    "    'todo [activity]' to input a todo task, \n" +
                                    "    'deadline [activity] /by [submission day]' to input deadline task" +
                                    "\n    or 'event [activity] /from [start period] /to [end period] to input a event task\n" + LINE);
            }
        }
    }

    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.equals("list")) {
            commandType = "LIST";
        } else if (userInput.startsWith("unmark ")) {
            commandType = "UNMARK";
        } else if (userInput.startsWith("mark ")) {
            commandType = "MARK";
        } else if (userInput.startsWith("todo ")) {
            commandType = "TODO";
        } else if (userInput.startsWith("deadline ")) {
            commandType = "DEADLINE";
        } else if (userInput.startsWith("event ")) {
            commandType = "EVENT";
        } else if (userInput.equals("/help")) {
            commandType = "HELP";
        } else {
            commandType = "INVALID";
        }

        return commandType;
    }

    public String[] inputParser(String userInput, String commandType) {
        String[] output = new String[3];
        String[] splitInput;
        String[] splitEvent;
        int index = 0;
        switch (commandType) {
            case "MARK":
                output[0] = String.valueOf(Integer.parseInt(userInput.substring(5)) - 1);
                output[1] = null;
                output[2] = null;
                break;

            case "UNMARK":
                output[0] = String.valueOf(Integer.parseInt(userInput.substring(7)) - 1);
                output[1] = null;
                output[2] = null;
                break;

            case "DEADLINE":
                splitInput = userInput.split(" /by ", 2);
                output[0] = splitInput[0].substring(9);
                output[1] = splitInput[1];
                output[2] = null;
                break;

            case "EVENT":
                splitInput = userInput.split(" /from ", 2);
                splitEvent = splitInput[1].split(" /to ", 2);
                output[0] = splitInput[0].substring(6);
                output[1] = splitEvent[0];
                output[2] = splitEvent[1];
                break;

        }

        return output;
    }
}
